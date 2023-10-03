package nova.pantheon.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import nova.pantheon.Pantheon;
import nova.pantheon.component.PantheonComponent;
import nova.pantheon.init.PantheonComponents;
import nova.pantheon.init.PantheonItems;

@Mixin(LivingEntity.class)
public abstract class TotemMixin {
    @Shadow
    public abstract ItemStack getStackInHand(Hand hand);

    @Shadow
    public abstract void setHealth(float health);

    @Shadow
    public abstract boolean clearStatusEffects();

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    public void revive(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (damageSource.isOutOfWorld()) {
            return;
        }
        if (!(_this() instanceof PlayerEntity player)) {
            return;
        }
        boolean returnValue = false;
        PantheonComponent pantheonComponent = PantheonComponents.PlayerPantheonComponent.get(player);
        if ((pantheonComponent.getPantheon() == Pantheon.DEATH) && (!pantheonComponent.hasCheatedDeath())) {
            setHealth(1.0F);
            clearStatusEffects();
            addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
            addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
            addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
            pantheonComponent.cheatDeath();
            _this().world.sendEntityStatus(_this(), (byte)35);
            returnValue = true;
        } else if (getStackInHand(Hand.MAIN_HAND).isOf(Items.TOTEM_OF_UNDYING)
                || getStackInHand(Hand.OFF_HAND).isOf(Items.TOTEM_OF_UNDYING)) {
            player.getInventory().offerOrDrop(new ItemStack(PantheonItems.TOTEM_FRAGMENT, 1));
        }
        cir.setReturnValue(returnValue);
        cir.cancel();
    }

    private LivingEntity _this() {
        return (LivingEntity) (Object) this;
    }
}
