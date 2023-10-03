package nova.pantheon.item.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import nova.pantheon.init.PantheonItems;

public class StarSteel implements ToolMaterial {
    private final int miningLevel = ToolMaterials.NETHERITE.getMiningLevel();
    private final int itemDurability = 2032;
    private final float miningSpeed = 8.0f;
    private final float attackDamage = 0f;
    private final int enchantability = 18;
    private final Ingredient repairIngredient = Ingredient.ofItems(PantheonItems.STARSTEEL_INGOT);

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
}
