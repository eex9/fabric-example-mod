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
import static nova.pantheon.Pantheon.MODID;
import static nova.pantheon.init.PantheonItems.ITEMGROUP_KEY;

public class PantheonBlocks {

    public static void init() {
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
