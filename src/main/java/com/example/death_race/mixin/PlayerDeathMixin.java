package com.example.death_race.mixin;

import com.example.death_race.death_event.PlayerDeathCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class PlayerDeathMixin {

    protected PlayerDeathMixin() {
        super(); // 需要提供构造参数，但 Mixin 不会实际调用它
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onPlayerDeath(DamageSource source, CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayerEntity player) {
            PlayerDeathCallback.EVENT.invoker().kill(player, source);
        }
    }
}