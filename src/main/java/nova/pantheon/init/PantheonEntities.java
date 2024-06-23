package nova.pantheon.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

import static nova.pantheon.Pantheon.MODID;

public class PantheonEntities {
    public static void init() {
    }

    public static <X extends Entity> EntityType<X> register(String name, int trackingDistance, int updateIntervalTicks,
            boolean alwaysUpdateVelocity, EntityDimensions size, boolean fireImmune,
            EntityType.EntityFactory<X> factory) {
        FabricEntityTypeBuilder<X> builder = FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory)
                .trackRangeChunks(trackingDistance).trackedUpdateRate(updateIntervalTicks)
                .forceTrackedVelocityUpdates(alwaysUpdateVelocity).dimensions(size);
        if (fireImmune) {
            builder.fireImmune();
        }
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(MODID, name), builder.build());
    }

    // private static DefaultParticleType registerParticle (Identifier id, DefaultParticleType particle){
    //     return Registry.register(Registry.PARTICLE_TYPE, id, particle);
    // }
}
