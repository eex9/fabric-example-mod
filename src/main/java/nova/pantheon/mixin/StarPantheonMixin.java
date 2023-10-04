package nova.pantheon.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import nova.pantheon.Pantheon;
import nova.pantheon.component.PantheonComponent;

import static nova.pantheon.init.PantheonComponents.PlayerPantheonComponent;
import static nova.pantheon.Pantheon.PANTHEON_CONFIG;

@Mixin(PlayerEntity.class)
public abstract class StarPantheonMixin extends LivingEntity {
    @Shadow
    @Final
    private PlayerAbilities abilities;
    PantheonComponent pantheonComponent = PlayerPantheonComponent.get(this);

    public StarPantheonMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo info) {
        if (pantheonComponent.getPantheon() == Pantheon.STAR) {
            this.fallDistance = 0.0f;
            if (!this.abilities.creativeMode && !this.isSpectator()) {
                if (pantheonComponent.getStarlight() > 0) {
                    this.abilities.allowFlying = true;
                    if (this.abilities.flying) {
                        pantheonComponent.useStarlight(PANTHEON_CONFIG.FLYING_STARLIGHT_USE_AMOUNT);
                    }
                }
                if (pantheonComponent.getStarlight() == 0) {
                    this.abilities.allowFlying = false;
                    this.abilities.flying = false;
                }
            }
            if (((this.getWorld().getLightLevel(LightType.BLOCK, this.getBlockPos()) >= 7)
                || this.getWorld().getLightLevel(LightType.SKY, this.getBlockPos()) >= 7)
                && (!this.abilities.flying)) {
                pantheonComponent.gainStarlight(PANTHEON_CONFIG.PASSIVE_STARLIGHT_REGEN_AMOUNT);
            }
        } else if (!this.abilities.creativeMode && !this.isSpectator()) {
            this.abilities.allowFlying = false;
            this.abilities.flying = false;
        }
    }
}
