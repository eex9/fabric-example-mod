package nova.pantheon.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import nova.pantheon.init.PantheonBlocks;

import static net.minecraft.block.ConnectingBlock.FACING_PROPERTIES;

public class ChannelBlock extends Block {
    public static BooleanProperty NORTH = BooleanProperty.of("north");
    public static BooleanProperty EAST = BooleanProperty.of("east");
    public static BooleanProperty SOUTH = BooleanProperty.of("south");
    public static BooleanProperty WEST = BooleanProperty.of("west");

    public ChannelBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false));
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (oldState.isOf(state.getBlock())) {
            return;
        }
        BlockPos posNorth = pos.north();
        BlockPos posEast = pos.east();
        BlockPos posSouth = pos.south();
        BlockPos posWest = pos.west();

        world.setBlockState(pos,
                state.with(NORTH, world.getBlockState(posNorth).isOf(PantheonBlocks.STONE_BRICK_CHANNEL))
                        .with(EAST, world.getBlockState(posEast).isOf(PantheonBlocks.STONE_BRICK_CHANNEL))
                        .with(SOUTH, world.getBlockState(posSouth).isOf(PantheonBlocks.STONE_BRICK_CHANNEL))
                        .with(WEST, world.getBlockState(posWest).isOf(PantheonBlocks.STONE_BRICK_CHANNEL)));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
            WorldAccess world, BlockPos pos, BlockPos neighborPos) {

        if (direction.getAxis().getType() == Direction.Type.HORIZONTAL) {
            return (BlockState) state.with(FACING_PROPERTIES.get(direction),
                    world.getBlockState(neighborPos).isOf(PantheonBlocks.STONE_BRICK_CHANNEL));
        }
        return state;
    }

    public void updateState(BlockState state, World world, BlockPos pos) {

    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST);
    }
}
