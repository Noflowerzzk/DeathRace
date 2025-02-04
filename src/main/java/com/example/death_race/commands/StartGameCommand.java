package com.example.death_race.commands;

import com.example.death_race.death_event.PlayerDeathCallback;
import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import com.example.death_race.game.ScoreboardHelper;

import java.util.ArrayList;

public class StartGameCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("startGame") // 创建 `/startGame` 命令
                    .executes(context -> startGame(context.getSource()))
            );
        });
    }

    public static Boolean isStarted = false;

    private static int startGame(ServerCommandSource _source) {
        // 给所有玩家发送消息
        _source.getServer().getPlayerManager().broadcast(Text.of("游戏开始了！"), false);
        if(isStarted) return 0;
        isStarted = true;

        ArrayList<String> gotDeath = new ArrayList<>();

        PlayerDeathCallback.EVENT.register((player, source) -> {
                System.out.println("玩家 " + player.getName().getString() + " 死亡，原因：" + source.getName());
//			System.out.println(source.getDeathMessage(player).getString());

                String deathMessage = source.getDeathMessage(player).getString();
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
}
