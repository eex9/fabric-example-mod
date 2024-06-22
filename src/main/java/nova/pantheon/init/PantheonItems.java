package nova.pantheon.init;

import static nova.pantheon.Pantheon.MODID;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.render.model.json.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import nova.pantheon.item.*;

public class PantheonItems {
    public static ModelTransformationMode currentItemRenderMode;

    public static Item CREATIVE_ID;
    public static ItemGroup PANTHEON_ITEMGROUP;
    public static RegistryKey<ItemGroup> ITEMGROUP_KEY;

    // Crests
    public static Item STAR_CREST;
    public static Item DEATH_CREST;
    public static Item BLOOD_CREST;
    public static Item DEBUG_CREST;

    public static Item RITUAL_KNIFE;

    // Other
    public static Item DOWSING_ROD;

    // Weapons
    public static Item REAPER_SCYTHE; // UNFINISHED
    public static Item SUPERNOVA_SWORD;

    public static Item ICHOR_GREATSWORD;
    public static Item ICHOR_HALFSWORD;
    public static Item ICHOR_GREATAXE;
    public static Item ICHOR_PICKAXE;
    public static Item ICHOR_SPADE;

    public static Item WARRIOR_SCYTHE; // UNFINISHED
    public static Item WARRIOR_SPEAR; // UNFINISHED
    public static Item ENCRUSTED_TRIDENT; // UNFINISHED

    // Divinity
    public static Item DIVINITY_INGOT;
    public static Item DIVINITY_SHARD;

    // Crafting
    public static Item CLOTH;
    public static Item IRON_CLASP;
    public static Item VEIL;

    public static Item STAR_FRAGMENT;
    public static Item FALLEN_STAR;
    public static Item STARSTEEL_INGOT;
    public static Item STAR_CLASP;
    public static Item CROWN_SHARD;
    public static Item MIDNIGHT_FABRIC;
    public static Item ANGEL_FEATHER;
    public static Item REINFORCED_STICK;

    public static Item DIVINE_ICHOR;
    public static Item HARDENED_ICHOR;
    public static Item BLAZING_ICHOR;

    public static Item ENDER_FLUORITE;
    public static Item NETHER_CINNABAR;
    public static Item TOTEM_FRAGMENT;

    public static Item CLOTH_MANTLE;
    public static Item CHIPPED_CROWN;
    public static Item FADED_VEIL;

    public static void init() {
        ITEMGROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MODID, "pantheon"));
        CREATIVE_ID = registerItem(new Item(new FabricItemSettings()), "creative_id");
        PANTHEON_ITEMGROUP = FabricItemGroup.builder().displayName(Text.translatable("itemgroup.pantheon.name")).icon(
                () -> new ItemStack(CREATIVE_ID)).build();
        Registry.register(Registries.ITEM_GROUP, ITEMGROUP_KEY, PANTHEON_ITEMGROUP);

        DOWSING_ROD = registerItem(
                new DowsingRodItem(new FabricItemSettings().maxCount(1)),
                "dowsing_rod");

        RITUAL_KNIFE = registerItem(new Item(new FabricItemSettings().maxCount(1)), "ritual_knife");
    }

    public static void initClient() {
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registries.ITEM, MODID + ":" + name, item);
        ItemGroupEvents.modifyEntriesEvent(ITEMGROUP_KEY).register(content -> {
			content.add(item);
		});
        return item;
    }
}