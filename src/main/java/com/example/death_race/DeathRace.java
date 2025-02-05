package com.example.death_race;

import net.fabricmc.api.ModInitializer;
import com.example.death_race.death_event.PlayerDeathCallback;
import com.example.death_race.death_event.ModDamageSources;
import com.example.death_race.commands.StartGameCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.MinecraftServer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.scoreboard.Team;

import java.util.ArrayList;

public class DeathRace implements ModInitializer {
	public static final String MOD_ID = "death-race";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	ArrayList<String> gotDeath = new ArrayList<>();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			ServerWorld OverWorld = server.getOverworld();
			ModDamageSources.initialize(OverWorld);
		});

		StartGameCommand.register();
//			System.out.println(source.toString());
//			System.out.println(ModDamageSources.FALL.toString());
//			System.out.println(Objects.equals(source.toString(), ModDamageSources.FALL.toString()));


//		LivingEntity.LIVING_DEATH.re

	}
}