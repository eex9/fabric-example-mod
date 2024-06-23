package nova.pantheon.init;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static nova.pantheon.Pantheon.MODID;

public class PantheonDamageSources {
    public static final RegistryKey<DamageType> MOONLIGHT_STRIKE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            new Identifier(MODID, "moonlight_strike"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
