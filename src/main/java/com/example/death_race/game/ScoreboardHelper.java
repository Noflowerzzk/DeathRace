package com.example.death_race.game;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;

public class ScoreboardHelper {
    public static void setPlayerScore(ServerPlayerEntity player, String objectiveName, int score) {
        Scoreboard scoreboard = player.getScoreboard();
        ScoreboardObjective objective = scoreboard.getNullableObjective(objectiveName);

        if (objective != null) {
            scoreboard.getOrCreateScore(player, objective).setScore(score);
        }
    }

    public static void addPlayerScore(ServerPlayerEntity player, String objectiveName) {
        Scoreboard scoreboard = player.getScoreboard();
        ScoreboardObjective objective = scoreboard.getNullableObjective(objectiveName);

        if (objective != null) {
            int currentScore = scoreboard.getOrCreateScore(player, objective).getScore();
            scoreboard.getOrCreateScore(player, objective).setScore(currentScore + 1);
        }
    }
}
