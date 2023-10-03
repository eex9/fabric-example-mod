package nova.pantheon.item.crests;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nova.pantheon.component.PantheonComponent;
import nova.pantheon.init.PantheonBlocks;

import static nova.pantheon.init.PantheonComponents.PlayerPantheonComponent;

public class CrestItem extends Item{
    private String itemPantheon;

    public CrestItem(Settings settings, String _class) {
        super(settings);
        this.itemPantheon = _class;
    }

    public String getItemPantheon() {
        return itemPantheon;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        PantheonComponent component = PlayerPantheonComponent.get(player);
        BlockPos standingOn = player.getBlockPos();
        if ((world.getBlockState(standingOn).getBlock().equals(PantheonBlocks.DIVINITY) || player.getAbilities().creativeMode)) { //  && (component.getPantheon() == Pantheon.EMPTY)
            setPantheon(component);
            return TypedActionResult.success(player.getStackInHand(hand));
        }
        return TypedActionResult.fail(player.getStackInHand(hand));
    }

    void setPantheon(PantheonComponent component) {
    }
}
