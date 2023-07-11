package nova.pantheon.item;

import java.util.Hashtable;

import de.dafuqs.revelationary.api.revelations.*;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import nova.pantheon.pantheons.PantheonClass;

public class CrestItem extends Item implements RevelationAware{
    PantheonClass itemPantheon;
    Identifier cloakAdvancementIdentifier;
    Item cloakItem;

    public CrestItem(Settings settings, PantheonClass _class, Identifier unlock, Item cloak) {
        super(settings);
        this.itemPantheon = _class;
		this.cloakAdvancementIdentifier = unlock;
		this.cloakItem = cloak;
    }

    @Override
	public Identifier getCloakAdvancementIdentifier() {
		return cloakAdvancementIdentifier;
	}
	
	@Override
	public Hashtable<BlockState, BlockState> getBlockStateCloaks() {
		return new Hashtable<>();
	}
	
	@Override
	public Pair<Item, Item> getItemCloak() {
		return new Pair<>(this, cloakItem);
	}
}
