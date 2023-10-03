package nova.pantheon.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PedestalBlock extends Block implements BlockEntityProvider {
    protected static final VoxelShape MAIN = VoxelShapes.union(
            Block.createCuboidShape(1, 0, 1, 15, 1, 15), VoxelShapes.union(
            Block.createCuboidShape(2, 1, 2, 14, 15, 14), 
            Block.createCuboidShape(1, 15, 1, 15, 16, 15)));
    protected static final VoxelShape DECO = VoxelShapes.union(
            Block.createCuboidShape(1, 0, 1, 3, 2, 3), VoxelShapes.union(
            Block.createCuboidShape(13, 0, 1, 15, 2, 3), VoxelShapes.union(
            Block.createCuboidShape(13, 0, 13, 15, 2, 15), VoxelShapes.union(
            Block.createCuboidShape(1, 0, 13, 3, 2, 15), VoxelShapes.union(

            Block.createCuboidShape(1, 14, 1, 3 ,16, 3), VoxelShapes.union(
            Block.createCuboidShape(13, 14, 1, 15, 16, 3),  VoxelShapes.union(
            Block.createCuboidShape(13, 14, 13, 15, 16, 15), 
            Block.createCuboidShape(1, 14, 13, 3, 16, 15))))))));

    protected static final VoxelShape SHAPE = VoxelShapes.union(MAIN, DECO);

    public PedestalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof PedestalBlockEntity pedestalBlockEntity) {
            pedestalBlockEntity.swapItems(world, pos, player, hand, player.getStackInHand(hand), pedestalBlockEntity);
        }
        return ActionResult.SUCCESS;
    }
}
