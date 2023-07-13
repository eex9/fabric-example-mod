package nova.pantheon.item;

import de.dafuqs.revelationary.api.revelations.CloakedItem;
import de.dafuqs.revelationary.ClientRevelationHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nova.pantheon.component.PantheonComponent;

import static nova.pantheon.init.PantheonComponents.PlayerPantheonComponent;

public class CrestItem extends CloakedItem{
    String itemPantheon;

    public CrestItem(Settings settings, String _class, Identifier unlock, Item cloak) {
        super(settings, unlock, cloak);
        this.itemPantheon = _class;
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        boolean isRevealed = ClientRevelationHolder.isCloaked(stack.getItem());
        if (!isRevealed) {
            PantheonComponent component = PlayerPantheonComponent.get(player);
            component.setPantheon(itemPantheon);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
