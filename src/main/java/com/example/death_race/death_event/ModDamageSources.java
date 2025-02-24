package com.example.death_race.death_event;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.server.world.ServerWorld;

public class ModDamageSources {
    public static DamageSource IN_FIRE;
    public static DamageSource LIGHTNING_BOLT;
    public static DamageSource ON_FIRE;
    public static DamageSource LAVA;
    public static DamageSource HOT_FLOOR;
    public static DamageSource IN_WALL;
    public static DamageSource CRAMMING;
    public static DamageSource DROWN;
    public static DamageSource STARVE;
    public static DamageSource CACTUS;
    public static DamageSource FALL;
    public static DamageSource FLY_INTO_WALL;
    public static DamageSource OUT_OF_WORLD;
    public static DamageSource GENERIC;
    public static DamageSource MAGIC;
    public static DamageSource WITHER;
    public static DamageSource DRAGON_BREATH;
    public static DamageSource DRY_OUT;
    public static DamageSource SWEET_BERRY_BUSH;
    public static DamageSource FREEZE;
    public static DamageSource STALAGMITE;
    public static DamageSource FALLING_BLOCK;
    public static DamageSource FALLING_ANVIL;
    public static DamageSource FALLING_STALACTITE;
    public static DamageSource STING;
    public static DamageSource MOB_ATTACK;
    public static DamageSource MOB_ATTACK_NO_AGGRO;
    public static DamageSource PLAYER_ATTACK;
    public static DamageSource ARROW;
    public static DamageSource TRIDENT;
    public static DamageSource MOB_PROJECTILE;
    public static DamageSource FIREWORKS;
    public static DamageSource FIREBALL;
    public static DamageSource UNATTRIBUTED_FIREBALL;
    public static DamageSource WITHER_SKULL;
    public static DamageSource THROWN;
    public static DamageSource INDIRECT_MAGIC;
    public static DamageSource THORNS;
    public static DamageSource EXPLOSION;
    public static DamageSource PLAYER_EXPLOSION;
    public static DamageSource SONIC_BOOM;
    public static DamageSource BAD_RESPAWN_POINT;
    public static DamageSource OUTSIDE_BORDER;
    public static DamageSource GENERIC_KILL;

    public static void initialize(ServerWorld world) {
        DamageSources damageSources = new DamageSources(world.getRegistryManager());

        IN_FIRE = damageSources.create(DamageTypes.IN_FIRE);
        LIGHTNING_BOLT = damageSources.create(DamageTypes.LIGHTNING_BOLT);
        ON_FIRE = damageSources.create(DamageTypes.ON_FIRE);
        LAVA = damageSources.create(DamageTypes.LAVA);
        HOT_FLOOR = damageSources.create(DamageTypes.HOT_FLOOR);
        IN_WALL = damageSources.create(DamageTypes.IN_WALL);
        CRAMMING = damageSources.create(DamageTypes.CRAMMING);
        DROWN = damageSources.create(DamageTypes.DROWN);
        STARVE = damageSources.create(DamageTypes.STARVE);
        CACTUS = damageSources.create(DamageTypes.CACTUS);
        FALL = damageSources.create(DamageTypes.FALL);
        FLY_INTO_WALL = damageSources.create(DamageTypes.FLY_INTO_WALL);
        OUT_OF_WORLD = damageSources.create(DamageTypes.OUT_OF_WORLD);
        GENERIC = damageSources.create(DamageTypes.GENERIC);
        MAGIC = damageSources.create(DamageTypes.MAGIC);
        WITHER = damageSources.create(DamageTypes.WITHER);
        DRAGON_BREATH = damageSources.create(DamageTypes.DRAGON_BREATH);
        DRY_OUT = damageSources.create(DamageTypes.DRY_OUT);
        SWEET_BERRY_BUSH = damageSources.create(DamageTypes.SWEET_BERRY_BUSH);
        FREEZE = damageSources.create(DamageTypes.FREEZE);
        STALAGMITE = damageSources.create(DamageTypes.STALAGMITE);
        FALLING_BLOCK = damageSources.create(DamageTypes.FALLING_BLOCK);
        FALLING_ANVIL = damageSources.create(DamageTypes.FALLING_ANVIL);
        FALLING_STALACTITE = damageSources.create(DamageTypes.FALLING_STALACTITE);
        STING = damageSources.create(DamageTypes.STING);
        MOB_ATTACK = damageSources.create(DamageTypes.MOB_ATTACK);
        MOB_ATTACK_NO_AGGRO = damageSources.create(DamageTypes.MOB_ATTACK_NO_AGGRO);
        PLAYER_ATTACK = damageSources.create(DamageTypes.PLAYER_ATTACK);
        ARROW = damageSources.create(DamageTypes.ARROW);
        TRIDENT = damageSources.create(DamageTypes.TRIDENT);
        MOB_PROJECTILE = damageSources.create(DamageTypes.MOB_PROJECTILE);
        FIREWORKS = damageSources.create(DamageTypes.FIREWORKS);
        FIREBALL = damageSources.create(DamageTypes.FIREBALL);
        UNATTRIBUTED_FIREBALL = damageSources.create(DamageTypes.UNATTRIBUTED_FIREBALL);
        WITHER_SKULL = damageSources.create(DamageTypes.WITHER_SKULL);
        THROWN = damageSources.create(DamageTypes.THROWN);
        INDIRECT_MAGIC = damageSources.create(DamageTypes.INDIRECT_MAGIC);
        THORNS = damageSources.create(DamageTypes.THORNS);
        EXPLOSION = damageSources.create(DamageTypes.EXPLOSION);
        PLAYER_EXPLOSION = damageSources.create(DamageTypes.PLAYER_EXPLOSION);
        SONIC_BOOM = damageSources.create(DamageTypes.SONIC_BOOM);
        BAD_RESPAWN_POINT = damageSources.create(DamageTypes.BAD_RESPAWN_POINT);
        OUTSIDE_BORDER = damageSources.create(DamageTypes.OUTSIDE_BORDER);
        GENERIC_KILL = damageSources.create(DamageTypes.GENERIC_KILL);
    }
}
