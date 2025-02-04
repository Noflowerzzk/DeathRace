package com.example.death_race.game;

import com.example.death_race.death_event.PlayerDeathCallback;
import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.scoreboard.ScoreHolder;
import net.minecraft.scoreboard.Team;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import com.example.death_race.game.ScoreboardHelper;
import net.minecraft.scoreboard.ScoreboardObjective;

import java.util.Map;
import java.util.HashMap;

public class TeamScoreCounter {
    public static Map<String, Integer> getTeamScores(MinecraftServer server, String objectiveName) {
        ServerScoreboard scoreboard = server.getScoreboard();
        ScoreboardObjective objective = scoreboard.getNullableObjective(objectiveName);
        Map<String, Integer> teamScores = new HashMap<>();

        if (objective == null) {
            return teamScores; // 目标不存在
        }

        for (Team team : scoreboard.getTeams()) {
            int totalScore = 0;
            for (String playerName : team.getPlayerList()) {
                totalScore += scoreboard.getOrCreateScore(ScoreHolder.fromName(playerName), objective).getScore();
            }
            teamScores.put(team.getName(), totalScore);
        }
        return teamScores;
    }
}
