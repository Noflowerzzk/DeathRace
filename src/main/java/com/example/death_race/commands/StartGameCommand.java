package com.example.death_race.commands;

import com.example.death_race.death_event.PlayerDeathCallback;
import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.server.world.ServerWorld;
import com.example.death_race.game.ScoreboardHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.block.Blocks;

import java.util.ArrayList;
import java.util.Random;

public class StartGameCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("startGame") // 创建 `/startGame` 命令
                    .executes(context -> startGame(context.getSource()))
            );
            dispatcher.register(CommandManager.literal("endGame")
                    .executes(context -> endGame(context.getSource()))
            );
            dispatcher.register(CommandManager.literal("randomTP")
                    .executes(context -> randomTP(context.getSource()))
            );
        });
    }

    public static Boolean isStarted = false;
    private static final ArrayList<String> gotDeath = new ArrayList<>();
    private static final Random random = new Random();

    public static int startGame(ServerCommandSource _source) {
        // 给所有玩家发送消息
        _source.getServer().getPlayerManager().broadcast(Text.of("游戏开始了！"), false);
        if(isStarted) return 0;
        isStarted = true;

//        ArrayList<String> gotDeath = new ArrayList<>();

        PlayerDeathCallback.EVENT.register((player, source) -> {
                System.out.println("玩家 " + player.getName().getString() + " 死亡，原因：" + source.getName());
//			System.out.println(source.getDeathMessage(player).getString());

                String deathMessage = source.getDeathMessage(player).getString();
                deathMessage = deathMessage.replaceFirst("^" + player.getName().getString(), "").trim();

                Team team = player.getScoreboardTeam();
                MinecraftServer server = player.getServer();
                assert server != null;

                if (gotDeath.contains((deathMessage))) {
                    server.getPlayerManager().broadcast(Text.literal(player.getName().getString() + " 死于老一套：" + deathMessage + "！").formatted(Formatting.BLUE), false);
                } else {
                    gotDeath.add(deathMessage);
                    server.getPlayerManager().broadcast(Text.literal(player.getName().getString() + " 死于新花样：" + deathMessage + "！").formatted(Formatting.GREEN), false);
//                    Scoreboard playerScoreboard = player.getScoreboard();
                    ScoreboardHelper.addPlayerScore(player, "score");
                }
            });

        // 返回 1 表示命令成功执行
        return Command.SINGLE_SUCCESS;
    }

    public static int endGame(ServerCommandSource _source) {
        if (!isStarted) return 0; // 游戏未开始则无操作
        gotDeath.clear(); // ✅ 清空死亡记录

        _source.getServer().getPlayerManager().broadcast(Text.of("游戏结束！数据已重置！"), false);
        return Command.SINGLE_SUCCESS;
    }

    public static int randomTP(ServerCommandSource _source) {
        MinecraftServer server = _source.getServer();

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            teleportPlayerToFarLand(player);
        }

        return Command.SINGLE_SUCCESS;
    }

    private static void teleportPlayerToFarLand(ServerPlayerEntity player) {
        ServerWorld world = player.getServerWorld();

        // 生成目标 x, z 坐标
        int x = (random.nextBoolean() ? 1 : -1) * (50000 + random.nextInt(50000));
        int z = (random.nextBoolean() ? 1 : -1) * (50000 + random.nextInt(50000));

        // 获取该位置的最高非空气方块
        int y = world.getHeight();

        // 确保 Y 位置安全
        BlockPos targetPos = new BlockPos(x, y, z);
        while (world.getBlockState(targetPos).isAir() && y > 0) {
            y--; // 向下查找合适的落地点
            targetPos = new BlockPos(x, y, z);
        }

        int i = 1;
        while (world.getBlockState(targetPos).getBlock() == Blocks.LAVA || world.getBlockState(targetPos).getBlock() == Blocks.WATER) {
            System.out.println("Generate " + i + " Failed!");
            // 生成目标 x, z 坐标
            x = (random.nextBoolean() ? 1 : -1) * (50000 + random.nextInt(50000));
            z = (random.nextBoolean() ? 1 : -1) * (50000 + random.nextInt(50000));

            // 获取该位置的最高非空气方块
            y = world.getHeight();

            // 确保 Y 位置安全
            targetPos = new BlockPos(x, y, z);
            while (world.getBlockState(targetPos).isAir() && y > 0) {
                y--; // 向下查找合适的落地点
                targetPos = new BlockPos(x, y, z);
            }
            i ++;
        }

        System.out.println("Generate " + i + " Successful!");

        // 传送玩家并设置新重生点
        player.teleport(world, x + 0.5, y + 3, z + 0.5, player.getYaw(), player.getPitch());
        player.setSpawnPoint(world.getRegistryKey(), new BlockPos(x, y + 3, z), 0.0f, true, false);

        player.sendMessage(Text.literal("你被传送到新位置: " + x + ", " + y + ", " + z).formatted(Formatting.AQUA), false);
    }
}
