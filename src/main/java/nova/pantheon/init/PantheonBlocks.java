package nova.pantheon.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import static nova.pantheon.Pantheon.MODID;
import static nova.pantheon.init.PantheonItems.PANTHEON_ITEMGROUP;

public class PantheonBlocks {

    public static void init() {
    }

    private static FabricItemSettings settings(int maxCount) {
        return new FabricItemSettings().group(PANTHEON_ITEMGROUP).maxCount(maxCount);
    }

    public static Block registerBlock(Block block, String name) {
        Registry.register(Registry.BLOCK, MODID + ":" + name, block);
        return block;
    }

    public static BlockItem registerBlockItem(BlockItem blockItem, String name) {
        Registry.register(Registry.ITEM, MODID + ":" + name, blockItem);
        return blockItem;
    }

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(BlockEntityType<T> blockEntity,
            String name) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MODID, name), blockEntity);
        return blockEntity;
    }
}
