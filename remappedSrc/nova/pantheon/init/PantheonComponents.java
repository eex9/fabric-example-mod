package nova.pantheon.init;

import static nova.pantheon.Pantheon.MODID;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import nova.pantheon.component.PantheonComponent;
import nova.pantheon.component.PlayerPantheon;

public class PantheonComponents implements EntityComponentInitializer{
    public static final ComponentKey<PantheonComponent> PlayerPantheonComponent = 
    ComponentRegistry.getOrCreate(new Identifier(MODID, "pantheon_component"), PantheonComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, PlayerPantheonComponent).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(PlayerPantheon::new);
    }
}
