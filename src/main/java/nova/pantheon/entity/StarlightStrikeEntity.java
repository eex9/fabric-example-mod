package nova.pantheon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import nova.pantheon.init.PantheonDamageSources;
import nova.pantheon.init.PantheonEntities;

public class StarlightStrikeEntity extends PantheonProjectileEntity {
    private float damage = 1.5f;

    public StarlightStrikeEntity(World world, LivingEntity owner) {
        super(PantheonEntities.STARLIGHT_STRIKE_ENTITY, world);
        setOwner(owner);
    }

    public StarlightStrikeEntity(EntityType<StarlightStrikeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        tag.putFloat("Damage", damage);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        damage = tag.getFloat("Damage");
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if ((entity instanceof LivingEntity livingEntity)) {
            livingEntity.damage(PantheonDamageSources.MOONLIGHT_STRIKE, damage);
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 60, 0));
        }
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}
