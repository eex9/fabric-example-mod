package nova.pantheon.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import static nova.pantheon.Pantheon.MODID;

public class StarlightStrikeEntityRenderer extends EntityRenderer<StarlightStrikeEntity> {
    private Identifier texture = new Identifier(MODID, "textures/particle/starlight_strike.png");
    private DefaultParticleType particle = ParticleTypes.NAUTILUS;

    public StarlightStrikeEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(StarlightStrikeEntity entity, float yaw, float tickDelta, MatrixStack matrices,
        VertexConsumerProvider vertexConsumers, int light) {
        World world = MinecraftClient.getInstance().world;

        double lastX = entity.lastRenderX + world.random.nextDouble() * 0.3 - 0.15;
        double lastY = entity.lastRenderY + world.random.nextDouble() * 0.3 - 0.15;
        double lastZ = entity.lastRenderZ + world.random.nextDouble() * 0.3 - 0.15;

        double targetX = entity.getX();
        double targetY = entity.getY();
        double targetZ = entity.getZ();

        Vec3d last = new Vec3d(lastX, lastY, lastZ);
        Vec3d current = new Vec3d(targetX, targetY, targetZ);

        Vec3d direction = current.subtract(last);
        Vec3d increment = direction.multiply(1 / (float) Math.round(direction.length() * 4));

        Vec3d currentRenderPosition = last.add(increment);
        
        Vec2f lookAngle2d = new Vec2f(1/(float)(Math.cos(entity.getPitch()) - Math.sin(entity.getPitch())), 1/(float)(Math.cos(entity.getPitch()) + Math.sin(entity.getPitch()))).normalize();
        
        for (int j = 0; j < Math.round(direction.length() * 4); j++) {
            for (int i = -2; i <= 2; i++) {
                world.addParticle(this.particle, currentRenderPosition.getX() + lookAngle2d.x * i/5, currentRenderPosition.getY(),
                        currentRenderPosition.getZ() + lookAngle2d.y * i/5, 0, 0, 0);
                        //TODO fix time of particles
                        //TODO custom particle - sprite alredy finished
            }
            currentRenderPosition = currentRenderPosition.add(increment);
        }
    }

    @Override
    public Identifier getTexture(StarlightStrikeEntity entity) {
        return texture;
    }
}
