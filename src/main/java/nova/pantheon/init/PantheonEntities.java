package nova.pantheon.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nova.pantheon.entity.*;

import static nova.pantheon.Pantheon.MODID;

public class PantheonEntities {
    public static EntityType<StarlightStrikeEntity> STARLIGHT_STRIKE_ENTITY;

    public static void init() {

        STARLIGHT_STRIKE_ENTITY = register("starlight_strike", 4, 10, true,
                EntityDimensions.changing(1.5F, 0.5F), true, StarlightStrikeEntity::new);
    }

    public static <X extends Entity> EntityType<X> register(String name, int trackingDistance, int updateIntervalTicks,
            boolean alwaysUpdateVelocity, EntityDimensions size, boolean fireImmune,
            EntityType.EntityFactory<X> factory) {
        FabricEntityTypeBuilder<X> builder = FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory)
                .trackRangeChunks(trackingDistance).updateIntervalTicks(updateIntervalTicks)
                .alwaysUpdateVelocity(alwaysUpdateVelocity).setDimensions(size).fireImmune(fireImmune);
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(MODID, name), builder.build());
    }

    // private static DefaultParticleType registerParticle (Identifier id, DefaultParticleType particle){
    //     return Registry.register(Registry.PARTICLE_TYPE, id, particle);
    // }
}
