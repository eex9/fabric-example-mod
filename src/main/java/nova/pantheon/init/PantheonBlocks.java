package nova.pantheon.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nova.pantheon.block.ChannelBlock;
import nova.pantheon.block.PedestalBlock;
import nova.pantheon.block.PedestalBlockEntity;
import nova.pantheon.block.PlayerPedestalBlock;

import static nova.pantheon.Pantheon.MODID;
import static nova.pantheon.init.PantheonItems.ITEMGROUP_KEY;

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
                new PedestalBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS).strength(5f, 5)),
                "stone_brick_pedestal");
        ITEM_PEDESTAL_BLOCKITEM = registerBlockItem(
                new BlockItem(ITEM_PEDESTAL,
                        new FabricItemSettings()),
                "stone_brick_pedestal");
        ITEM_PEDESTAL_BLOCKENTITY = registerBlockEntity(
                FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, ITEM_PEDESTAL).build(),
                "stone_brick_pedestal");

        STONE_BRICK_CHANNEL = registerBlock(new ChannelBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)),
                "stone_brick_channel");
        STONE_BRICK_CHANNEL_BLOCKITEM = registerBlockItem(
                new BlockItem(STONE_BRICK_CHANNEL, new FabricItemSettings()),
                "stone_brick_channel");

        STONE_BRICK_PULPIT = registerBlock(new PlayerPedestalBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)),
                "stone_brick_pulpit");
        STONE_BRICK_PULPIT_BLOCKITEM = registerBlockItem(
                new BlockItem(STONE_BRICK_PULPIT, new FabricItemSettings()),
                "stone_brick_pulpit");

        DIVINITY = registerBlock(new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).luminance(12)),
                "divinity_block");
        DIVINITY_BLOCKITEM = registerBlockItem(
                new BlockItem(DIVINITY, new FabricItemSettings()),
                "divinity_block");

        GENESIS = registerBlock(new Block(FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS)), "genesis_matter");
        GENESIS_BLOCKITEM = registerBlockItem(
                new BlockItem(GENESIS, new FabricItemSettings()),
                "genesis_matter");
    }

    public static Block registerBlock(Block block, String name) {
        Registry.register(Registries.BLOCK, MODID + ":" + name, block);
        return block;
    }

    public static BlockItem registerBlockItem(BlockItem blockItem, String name) {
        Registry.register(Registries.ITEM, MODID + ":" + name, blockItem);
        ItemGroupEvents.modifyEntriesEvent(ITEMGROUP_KEY).register(content -> {
			content.add(blockItem);
		});
        return blockItem;
    }

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(BlockEntityType<T> blockEntity,
            String name) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MODID, name), blockEntity);
        return blockEntity;
    }
}
