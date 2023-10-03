package nova.pantheon.init;

import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nova.pantheon.block.ChannelBlock;
import nova.pantheon.block.PedestalBlock;
import nova.pantheon.block.PedestalBlockEntity;
import nova.pantheon.block.PlayerPedestalBlock;

import static nova.pantheon.Pantheon.MODID;

public class PantheonBlocks {
    public static Block ITEM_PEDESTAL;
    public static BlockItem ITEM_PEDESTAL_BLOCKITEM;
    public static BlockEntityType<PedestalBlockEntity> ITEM_PEDESTAL_BLOCKENTITY;

    public static Block STONE_BRICK_CHANNEL;
    public static BlockItem STONE_BRICK_CHANNEL_BLOCKITEM;

    public static Block DIVINITY;
    public static BlockItem DIVINITY_BLOCKITEM;

    public static Block GENESIS;
    public static BlockItem GENESIS_BLOCKITEM;

    public static Block STONE_BRICK_PULPIT;
    public static BlockItem STONE_BRICK_PULPIT_BLOCKITEM;

    public static void init() {
        ITEM_PEDESTAL = registerBlock(
                new PedestalBlock(QuiltBlockSettings.copyOf(Blocks.STONE_BRICKS).strength(5f, 5)),
                "stone_brick_pedestal");
        ITEM_PEDESTAL_BLOCKITEM = registerBlockItem(
                new BlockItem(ITEM_PEDESTAL,
                        new QuiltItemSettings().group(PantheonItems.PANTHEON_ITEMGROUP)),
                "stone_brick_pedestal");
        ITEM_PEDESTAL_BLOCKENTITY = registerBlockEntity(
                QuiltBlockEntityTypeBuilder.create(PedestalBlockEntity::new, ITEM_PEDESTAL).build(),
                "stone_brick_pedestal");

        STONE_BRICK_CHANNEL = registerBlock(new ChannelBlock(QuiltBlockSettings.copyOf(Blocks.STONE_BRICKS)),
                "stone_brick_channel");
        STONE_BRICK_CHANNEL_BLOCKITEM = registerBlockItem(
                new BlockItem(STONE_BRICK_CHANNEL, new QuiltItemSettings().group(PantheonItems.PANTHEON_ITEMGROUP)),
                "stone_brick_channel");

        STONE_BRICK_PULPIT = registerBlock(new PlayerPedestalBlock(QuiltBlockSettings.copyOf(Blocks.STONE_BRICKS)),
                "stone_brick_pulpit");
        STONE_BRICK_PULPIT_BLOCKITEM = registerBlockItem(
                new BlockItem(STONE_BRICK_PULPIT, new QuiltItemSettings().group(PantheonItems.PANTHEON_ITEMGROUP)),
                "stone_brick_pulpit");

        DIVINITY = registerBlock(new Block(QuiltBlockSettings.copyOf(Blocks.IRON_BLOCK).luminance(12)),
                "divinity_block");
        DIVINITY_BLOCKITEM = registerBlockItem(
                new BlockItem(DIVINITY, new QuiltItemSettings().group(PantheonItems.PANTHEON_ITEMGROUP)),
                "divinity_block");

        GENESIS = registerBlock(new Block(QuiltBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS)), "genesis_matter");
        GENESIS_BLOCKITEM = registerBlockItem(
                new BlockItem(GENESIS, new QuiltItemSettings().group(PantheonItems.PANTHEON_ITEMGROUP)),
                "genesis_matter");
    }

    public static Block registerBlock(Block block, String name) {
        Registries.register(Registries.BLOCK, MODID + ":" + name, block);
        return block;
    }

    public static BlockItem registerBlockItem(BlockItem blockItem, String name) {
        Registries.register(Registries.ITEM, MODID + ":" + name, blockItem);
        return blockItem;
    }

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(BlockEntityType<T> blockEntity,
            String name) {
        Registries.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MODID, name), blockEntity);
        return blockEntity;
    }
}
