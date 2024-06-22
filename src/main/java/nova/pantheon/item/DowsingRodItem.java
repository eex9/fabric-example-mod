package nova.pantheon.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nova.pantheon.component.PantheonComponent;
import nova.pantheon.init.PantheonComponents;

public class DowsingRodItem extends Item {

    public DowsingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) return TypedActionResult.success(player.getStackInHand(hand));
        PantheonComponent component = PantheonComponents.PlayerPantheonComponent.get(player);
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.inGameHud.setOverlayMessage(
                (Text.translatable("gui.pantheon.dowsing_rod_message." + component.getPantheon().toLowerCase())),
                false);
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
