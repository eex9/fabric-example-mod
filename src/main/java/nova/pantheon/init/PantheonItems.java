package nova.pantheon.init;

import static nova.pantheon.Pantheon.MODID;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import nova.pantheon.item.*;

public class PantheonItems {
    public static Identifier ITEMGROUP_KEY;
    public static Item CREATIVE_ID;
    public static ItemGroup PANTHEON_ITEMGROUP;

    public static Item RITUAL_KNIFE;

    // Other
    public static Item DOWSING_ROD;

    public static void init() {
        ITEMGROUP_KEY = new Identifier(MODID, "pantheon");
        CREATIVE_ID = registerItem(new Item(new FabricItemSettings()), "creative_id");
        PANTHEON_ITEMGROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "pantheon")).icon(() -> new ItemStack(CREATIVE_ID)).build();

        DOWSING_ROD = registerItem(
                new DowsingRodItem(settings(1)),
                "dowsing_rod");

        RITUAL_KNIFE = registerItem(new Item(settings(1)), "ritual_knife");
    }

    public static void initClient() {
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, MODID + ":" + name, item);
        return item;
    }

    private static FabricItemSettings settings(int maxCount) {
        return new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(maxCount);
    }
}