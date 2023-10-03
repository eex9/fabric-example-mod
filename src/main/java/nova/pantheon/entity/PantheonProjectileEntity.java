package nova.pantheon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class PantheonProjectileEntity extends ProjectileEntity {
    protected int maxAge = 60;

    public PantheonProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setProperties(Entity player, float pitch, float yaw, float roll, float modifierZ, float modifierXYZ) {
        super.setProperties(player, pitch, yaw, roll, modifierZ, modifierXYZ);
        this.setVelocity(this.getVelocity().subtract(0, player.isOnGround() ? 0.0D : player.getVelocity().y, 0));

        // Offset so it's not in your face
        Vec3d newPos = this.getPos().add(this.getVelocity().multiply(0.5));
        this.setPos(newPos.x, newPos.y, newPos.z);
    }

    @Override
    public void tick() {
        super.tick();

        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        if (hitResult.getType() != HitResult.Type.MISS) {
            this.onCollision(hitResult);
        }

        if (age > maxAge)
            this.remove(RemovalReason.KILLED);

        Vec3d newPos = this.getPos().add(this.getVelocity());
        this.setPosition(newPos.getX(), newPos.getY(), newPos.getZ());
    }
}
