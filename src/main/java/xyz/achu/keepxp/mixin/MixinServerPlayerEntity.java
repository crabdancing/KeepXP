package xyz.achu.keepxp.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.container.ContainerListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class MixinServerPlayerEntity extends PlayerEntity implements ContainerListener {

    public MixinServerPlayerEntity(World world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "copyFrom", at = @At("RETURN"))
    public void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
            this.experienceLevel = oldPlayer.experienceLevel;
            this.totalExperience = oldPlayer.totalExperience;
            this.experienceProgress = oldPlayer.experienceProgress;
            this.setScore(oldPlayer.getScore());
        }
    }

    /* do NOT drop XP! */
    @Override
    protected void dropXp() {}

}
