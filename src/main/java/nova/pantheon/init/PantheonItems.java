package nova.pantheon.init;

import static nova.pantheon.Pantheon.MODID;

import de.dafuqs.revelationary.api.revelations.CloakedItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nova.pantheon.Pantheon;
import nova.pantheon.item.CrestItem;
import nova.pantheon.item.DowsingRodItem;

public class PantheonItems {
    public static Item CREATIVE_ID;
    public static ItemGroup PANTHEON_ITEMGROUP;

    // Crests
    public static Item STAR_CREST;
    public static Item DEATH_CREST;
    public static Item BLOOD_CREST;

    // Other
    public static Item DOWSING_ROD;

    // Crafting
    public static Item CLOTH;
    public static Item IRON_CLASP;
    public static Item VEIL;

    public static Item STAR_FRAGMENT;
    public static Item FALLEN_STAR;
    public static Item STARSTEEL_INGOT;
    public static Item STAR_CLASP;
    public static Item CROWN_SHARD;

    public static Item ENDER_GEM;
    public static Item NETHER_GEM;
    public static Item TOTEM_FRAGMENT;

    public static Item CLOTH_MANTLE;
    public static Item CHIPPED_CROWN;
    public static Item FADED_VEIL;

    public static void init() {
        CREATIVE_ID = registerItem(new Item(new FabricItemSettings()), "creative_id");
        PANTHEON_ITEMGROUP = FabricItemGroupBuilder.create(new Identifier("pantheon", "pantheon")).icon(() -> new ItemStack(CREATIVE_ID)).build();

        // Register Crests
        STAR_CREST = registerItem(new CrestItem(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(1),
                                                Pantheon.STAR, new Identifier("minecraft", "end/elytra"),
                                                Items.NETHER_STAR), "crest_star");
        DEATH_CREST = registerItem(new CrestItem(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(1),
                                                Pantheon.DEATH, new Identifier("minecraft", "end/elytra"),
                                                Items.TOTEM_OF_UNDYING), "crest_death");
        BLOOD_CREST = registerItem(new CrestItem(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(1),
                                                Pantheon.BLOOD, new Identifier("minecraft", "end/elytra"),
                                                Items.GOLDEN_HELMET), "crest_blood");

        DOWSING_ROD = registerItem(new DowsingRodItem(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(1)), "dowsing_rod");
        
        // Register Crafting Ingredients
        CLOTH = registerItem(new Item(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(64)), "cloth");
        IRON_CLASP = registerItem(new Item(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(1)), "iron_clasp");
        VEIL = registerItem(new Item(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(4)), "veil");

        STAR_FRAGMENT = registerItem(new Item(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(64)), "star_fragment");
        FALLEN_STAR = registerItem(new Item(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(64)),"fallen_star");
        CROWN_SHARD = registerItem(new Item(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(1)), "crown_shard");
        STARSTEEL_INGOT = registerItem(new CloakedItem(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(1),
        new Identifier("minecraft", "end/elytra"), Items.IRON_INGOT), "starsteel_ingot");
        STAR_CLASP = registerItem(new CloakedItem(new FabricItemSettings().group(PANTHEON_ITEMGROUP),
        new Identifier("minecraft", "end/elytra"), IRON_CLASP), "star_clasp");

        CLOTH_MANTLE = registerItem(new Item(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(16)), "cloth_mantle");
        CHIPPED_CROWN = registerItem(new CloakedItem(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(1), new Identifier("minecraft", "end/elytra"), Items.GOLDEN_HELMET), "chipped_crown");
        FADED_VEIL = registerItem(new CloakedItem(new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(16),
        new Identifier("minecraft", "end/elytra"), VEIL), "faded_veil");
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, MODID + ":" + name, item);
        return item;
    }
}