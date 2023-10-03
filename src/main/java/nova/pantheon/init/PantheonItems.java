package nova.pantheon.init;

import static nova.pantheon.Pantheon.MODID;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.model.json.*;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import nova.pantheon.item.*;
import nova.pantheon.item.crests.*;
import nova.pantheon.item.ichor_tools.IchorGreatswordItem;
import nova.pantheon.item.ichor_tools.IchorHalfswordItem;
import nova.pantheon.item.ichor_tools.IchorPickaxe;
import nova.pantheon.item.material.*;

public class PantheonItems {
    public static ModelTransformationMode currentItemRenderMode;

    public static Item CREATIVE_ID;
    public static ItemGroup PANTHEON_ITEMGROUP;
    public static RegistryKey<ItemGroup> ITEMGROUP_KEY;

    // Materials
    public static final StarSteel STAR_STEEL_MATERIAL = new StarSteel();
    public static final IchorMaterial ICHOR_MATERIAL = new IchorMaterial();

    // Crests
    public static Item STAR_CREST;
    public static Item DEATH_CREST;
    public static Item BLOOD_CREST;
    public static Item DEBUG_CREST;

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

        // Register Crests
        STAR_CREST = registerItem(new StarCrest(new FabricItemSettings().maxCount(1)),
                "crest_star");
        DEATH_CREST = registerItem(
                new DeathCrest(new FabricItemSettings().maxCount(1)),
                "crest_death");
        BLOOD_CREST = registerItem(
                new BloodCrest(new FabricItemSettings().maxCount(1)), "crest_blood");
        DEBUG_CREST = registerItem(new EmptyCrest(new FabricItemSettings().maxCount(1)), "crest_debug");

        DOWSING_ROD = registerItem(
                new DowsingRodItem(new FabricItemSettings().maxCount(1)),
                "dowsing_rod");

        // Divinity
        DIVINITY_INGOT = registerItem(new Item(new FabricItemSettings()), "divinity_ingot");
        DIVINITY_SHARD = registerItem(new Item(new FabricItemSettings()), "divinity_shard");

        // Register Custom Weapons
        REAPER_SCYTHE = registerItem(
                new Item(new FabricItemSettings().maxCount(1)),
                "reaper_scythe");

        SUPERNOVA_SWORD = registerItem(new SuperNovaeSword(STAR_STEEL_MATERIAL, 6, 0.6f,
                new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof()
                        ),
                "supernova_sword");

        ICHOR_GREATSWORD = registerItem( // WHY is the attack speed added to 4. thats dumb.
                new IchorGreatswordItem(ICHOR_MATERIAL, 9, -3.2f,
                        new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof()),
                "ichor_greatsword");

        ICHOR_HALFSWORD = registerItem(
                new IchorHalfswordItem(ICHOR_MATERIAL, 4, -1.0f,
                        new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof()),
                "ichor_halfsword");

        ICHOR_GREATAXE = registerItem(
                new AxeItem(ICHOR_MATERIAL, 8, -3.3f,
                        new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof()),
                "ichor_greataxe");

        ICHOR_PICKAXE = registerItem(
                new IchorPickaxe(ICHOR_MATERIAL, 4, -2.8f,
                        new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof()),
                "ichor_pickaxe");

        ICHOR_SPADE = registerItem(
                new ShovelItem(ICHOR_MATERIAL, 4, -3.0f,
                        new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof()),
                "ichor_spade");

        // Register Crafting Ingredients
        CLOTH = registerItem(new Item(new FabricItemSettings().maxCount(64)),
                "cloth");
        MIDNIGHT_FABRIC = registerItem(
                new Item(new FabricItemSettings().maxCount(64)),
                "midnight_fabric");
        IRON_CLASP = registerItem(new Item(new FabricItemSettings().maxCount(1)),
                "iron_clasp");
        VEIL = registerItem(new Item(new FabricItemSettings().maxCount(64)), "veil");

        STAR_FRAGMENT = registerItem(new Item(new FabricItemSettings().maxCount(64)),
                "star_fragment");
        FALLEN_STAR = registerItem(new Item(new FabricItemSettings().maxCount(64)),
                "fallen_star");
        CROWN_SHARD = registerItem(new Item(new FabricItemSettings().maxCount(16)),
                "crown_shard");
        STARSTEEL_INGOT = registerItem(
                new Item(new FabricItemSettings().maxCount(64)),
                "starsteel_ingot");
        STAR_CLASP = registerItem(new Item(new FabricItemSettings()), "star_clasp");

        ENDER_FLUORITE = registerItem(new Item(new FabricItemSettings().maxCount(64)),
                "ender_fluorite");
        NETHER_CINNABAR = registerItem(new Item(new FabricItemSettings().maxCount(64)),
                "nether_cinnabar");
        TOTEM_FRAGMENT = registerItem(new Item(new FabricItemSettings().maxCount(64)),
                "totem_fragment");

        CLOTH_MANTLE = registerItem(new Item(new FabricItemSettings().maxCount(16)),
                "cloth_mantle");
        CHIPPED_CROWN = registerItem(
                new Item(new FabricItemSettings().maxCount(1)),
                "chipped_crown");
        FADED_VEIL = registerItem(
                new Item(new FabricItemSettings().maxCount(16)),
                "faded_veil");
        ANGEL_FEATHER = registerItem(new Item(new FabricItemSettings()), "angel_feather");
        REINFORCED_STICK = registerItem(new Item(new FabricItemSettings()),
                "reinforced_stick");
        HARDENED_ICHOR = registerItem(new Item(new FabricItemSettings()), "hardened_ichor");
    }

    public static void initClient() {
        registerOversizedItemPredicate(SUPERNOVA_SWORD);

        registerOversizedItemPredicate(ICHOR_GREATSWORD);
        registerOversizedItemPredicate(ICHOR_HALFSWORD);
        registerOversizedItemPredicate(ICHOR_PICKAXE);
        registerOversizedItemPredicate(ICHOR_GREATAXE);
        registerOversizedItemPredicate(ICHOR_SPADE);
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registries.ITEM, MODID + ":" + name, item);
        ItemGroupEvents.modifyEntriesEvent(ITEMGROUP_KEY).register(content -> {
			content.add(item);
		});
        return item;
    }

    private static void registerOversizedItemPredicate(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("in_world"),
                (itemStack, world, livingEntity, i) -> {
                    if (world == null && livingEntity == null && i == 0) { // REIs 'fast batch' render mode. Without
                                                                           // mixin' into REI
                                                                           // there is no better way to catch this, I'm
                                                                           // afraid
                        return 0.0F;
                    }
                    return ((currentItemRenderMode == ModelTransformationMode.GUI)
                            || (currentItemRenderMode == ModelTransformationMode.GROUND)
                            || (currentItemRenderMode == ModelTransformationMode.FIXED))
                                    ? 0.0F
                                    : 1.0F;
                });
    }
}