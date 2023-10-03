package nova.pantheon.init;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import nova.pantheon.Pantheon;

public class PantheonTags {
    public static TagKey<Item> ICHOR_TOOLS;
    public static TagKey<Item> RUNES;
    public static TagKey<Item> GEMS;

    public void init() {
        ICHOR_TOOLS = TagKey.of(RegistryKeys.ITEM, new Identifier(Pantheon.MODID, "ichor_tools"));
        RUNES = TagKey.of(RegistryKeys.ITEM, new Identifier(Pantheon.MODID, "runes"));
        GEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(Pantheon.MODID, "crown_gems"));
    }
}
