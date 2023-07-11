package nova.pantheon.init;

import static nova.pantheon.Pantheon.MODID;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import nova.pantheon.item.CrestItem;

public class PantheonItems {
    public static Item STAR_CREST;
    public static Item DEATH_CREST;
    public static Item BLOOD_CREST;

    public static void init() {
        STAR_CREST = registerItem(new CrestItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1),
                                                Pantheons.PANTHEON_STAR, new Identifier("minecraft", "end/elytra"),
                                                Items.NETHER_STAR), MODID);
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, MODID + ":" + name, item);
        return item;
    }
}