package nova.pantheon.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nova.pantheon.init.PantheonBlocks;

public class PedestalBlockEntity extends BlockEntity implements ImplementedInventory {
    protected final int inventorySize = 1;
    protected DefaultedList<ItemStack> items = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(PantheonBlocks.ITEM_PEDESTAL_BLOCKENTITY, pos, state);
    }

    public void updateInClientWorld() {
        ((ServerWorld) world).getChunkManager().markForUpdate(pos);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        this.writeNbt(nbtCompound);
        return nbtCompound;
    }

    @Override
    public void inventoryChanged() {
        this.markDirty();
        if (world != null && !world.isClient) {
            updateInClientWorld();
        }
    }

    public void swapItems(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack handStack,
            PedestalBlockEntity blockEntity) {
        if (player.isSneaking()) { // Untested
            ItemStack pedestalItem = blockEntity.removeStack(0);
            if (!pedestalItem.isEmpty()) {
                player.getInventory().offerOrDrop(pedestalItem);
                blockEntity.markDirty();
            }
        } else {
            ItemStack currentStack = blockEntity.getStack(0);
            if (!handStack.isEmpty() && !currentStack.isEmpty()) { // Works fine
                blockEntity.setStack(0, handStack);
                player.setStackInHand(hand, currentStack);
                blockEntity.markDirty();
            } else {
                if (!handStack.isEmpty()) {
					ItemStack singleStack = handStack.split(1);
					blockEntity.setStack(0, singleStack);
				}
				if (!currentStack.isEmpty()) { 
					blockEntity.setStack(0, ItemStack.EMPTY);
					player.getInventory().offerOrDrop(currentStack);
				}
            }
        }
    }

    protected static ItemStack setOrCombineStack(Inventory inventory, int slot, ItemStack addingStack) {
        ItemStack existingStack = inventory.getStack(slot);
        if (existingStack.isEmpty()) {
            if (addingStack.getCount() > inventory.getMaxCountPerStack()) {
                int amount = Math.min(addingStack.getMaxCount(), addingStack.getCount());
                amount = Math.min(amount, inventory.getMaxCountPerStack());
                ItemStack newStack = addingStack.copy();
                newStack.setCount(amount);
                addingStack.decrement(amount);
                inventory.setStack(slot, newStack);
            } else {
                inventory.setStack(slot, addingStack);
                return ItemStack.EMPTY;
            }
        } else {
            combineStacks(existingStack, addingStack);
        }
        return addingStack;
    }

    protected static void combineStacks(ItemStack originalStack, ItemStack addingStack) {
        if (ItemStack.canCombine(originalStack, addingStack)) {
            int leftOverAmountInExistingStack = originalStack.getMaxCount() - originalStack.getCount();
            if (leftOverAmountInExistingStack > 0) {
                int addAmount = Math.min(leftOverAmountInExistingStack, addingStack.getCount());
                originalStack.increment(addAmount);
                addingStack.decrement(addAmount);
            }
        }
    }
}
