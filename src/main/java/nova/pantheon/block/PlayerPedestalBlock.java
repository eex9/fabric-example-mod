package nova.pantheon.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class PlayerPedestalBlock extends Block {
    protected static VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(1, 0, 1, 15, 2, 15),
            Block.createCuboidShape(2, 0, 2, 14, 3, 14));

    public PlayerPedestalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public boolean isMultiblock(BlockPos pos) {
        return true;
    }
}
