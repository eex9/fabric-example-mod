package nova.pantheon.item.ichor_tools;

import java.util.List;

import blue.endless.jankson.annotation.Nullable;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class IchorPickaxe extends PickaxeItem implements IchorTool {
    public IchorPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context){
        for (Text line: IchorTool.getTooltip(stack)) {
            tooltip.add(line);
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
