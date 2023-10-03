package nova.pantheon.item.ichor_tools;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nova.pantheon.init.PantheonItems;

public class IchorHalfswordItem extends SwordItem {

    public IchorHalfswordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!(player.getStackInHand(hand).isOf(PantheonItems.ICHOR_HALFSWORD))) {
            return TypedActionResult.fail(player.getStackInHand(hand));
        }
        NbtCompound oldNBT = player.getStackInHand(hand).getNbt();
        System.out.println(oldNBT);

        player.getInventory().setStack(player.getInventory().selectedSlot,
                new ItemStack(PantheonItems.ICHOR_GREATSWORD));
        player.getStackInHand(hand).setNbt(oldNBT);
        System.out.println(player.getStackInHand(hand).getNbt());

        player.getItemCooldownManager().set(PantheonItems.ICHOR_GREATSWORD, 5);
        player.getItemCooldownManager().set(PantheonItems.ICHOR_HALFSWORD, 5);

        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
