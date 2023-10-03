package nova.pantheon.item;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class RuneItem extends Item {
    private Identifier rune;

    public RuneItem(Settings settings, Identifier rune){
        super(settings);
        this.rune = rune;
    }
    public Identifier getRune() {
        return this.rune;
    }
}
