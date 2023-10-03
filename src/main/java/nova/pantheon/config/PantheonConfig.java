package nova.pantheon.config;

import me.shedaniel.autoconfig.*;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.*;

@Config(name = "pantheon")
public class PantheonConfig implements ConfigData{

    @Comment("The amount of starlight you gain when picking the Star pantheon - Default 100")
    public int DEFAULT_MAX_STARLIGHT = 5;

    @Comment("The amount of starlight used while flying - Default 1\nBy default you can fly for 5 seconds before falling.")
    public float FLYING_STARLIGHT_USE_AMOUNT = 0.05f;

    @Comment("The amount of starlight regained while in bright light and not flying.")
	public float PASSIVE_STARLIGHT_REGEN_AMOUNT = 0.01f;

    @Comment("If the player has starlight, where it should be rendered on screen. Default: Over the hunger bar")
    public int StarlightOffsetY = 0;
    public int StarlightOffsetX = 0;
	public int StarlightOffsetUnderwater = -10;

    @Comment("The multiplier for the damage of the projectile the Sword of the Supernova shoots.")
    public float SupernovaSwordProjectileDamageMultiplier = 0.7f;
    @Comment("The cooldown for the projectile of the Sword of Supernovae")
    public int SupernovaSwordSecondaryCooldown = 30;
}
