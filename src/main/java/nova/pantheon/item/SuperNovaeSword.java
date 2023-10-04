package nova.pantheon.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nova.pantheon.Pantheon;
import nova.pantheon.component.PantheonComponent;
import nova.pantheon.entity.StarlightStrikeEntity;
import nova.pantheon.init.PantheonComponents;
import nova.pantheon.init.PantheonItems;

public class SuperNovaeSword extends SwordItem {

    public SuperNovaeSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            PantheonComponent pantheonComponent = PantheonComponents.PlayerPantheonComponent.get(player);
            if ((pantheonComponent.getPantheon() != Pantheon.STAR) || (pantheonComponent.getStarlight() < 0.25))  {
                return TypedActionResult.fail(player.getStackInHand(hand));
            }

            pantheonComponent.useStarlight(0.25f);

            float damage = (float) ((player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                    + EnchantmentHelper.getAttackDamage(player.getMainHandStack(), EntityGroup.DEFAULT)) * 1.5f
                    * Pantheon.PANTHEON_CONFIG.SupernovaSwordProjectileDamageMultiplier);

            StarlightStrikeEntity projectile = new StarlightStrikeEntity(world, player);
            projectile.refreshPositionAndAngles(player.getX(), player.getEyeY(), player.getZ(), 0, 0);
            projectile.setProperties(player, player.getPitch(), player.getYaw(), 0f);
            projectile.setOwner(player);
            projectile.setPitch(player.getPitch());
            projectile.setYaw(player.getYaw());
            projectile.setDamage(damage);

            world.spawnEntity(projectile);

            player.getItemCooldownManager().set(PantheonItems.SUPERNOVA_SWORD,
                    Pantheon.PANTHEON_CONFIG.SupernovaSwordSecondaryCooldown);
        }

        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
