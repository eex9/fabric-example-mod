package nova.pantheon.item.ichor_tools;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

public interface IchorTool {
    public static final String RUNE_KEY = "Runes";
    public static final String PRIMARY_KEY = "Primary";
    public static final String SECONDARY_KEY = "Secondary";

    static List<Text> getTooltip(ItemStack stack) {
        List<Text> tooltip = List.of();
        NbtCompound rune_nbt = stack.getOrCreateSubNbt(RUNE_KEY);
        if (rune_nbt.isEmpty()) return tooltip;
        NbtCompound primary = rune_nbt.getCompound(PRIMARY_KEY);
        if (primary.isEmpty()) return tooltip;
        else {
            String level = "●".repeat(Math.max(0, primary.getInt("level")));
            Text primary_rune = Text.translatable("rune.pantheon." + primary.getString("id")).append(Text.of(": " + level));
            tooltip.add(primary_rune);
        }
        NbtCompound secondary = rune_nbt.getCompound(SECONDARY_KEY);
        if (secondary.isEmpty()) return tooltip;
        else {
            String level = "●".repeat(Math.max(0, secondary.getInt("level")));
            Text secondary_rune = Text.translatable("rune.pantheon." + secondary.getString("id")).append(Text.of(": " + level));
            tooltip.add(secondary_rune);
        }
        return tooltip;
    } 
}
