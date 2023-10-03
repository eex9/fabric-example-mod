package nova.pantheon.init;

import net.minecraft.entity.damage.DamageSource;

public class PantheonDamageSources{
    public static final DamageSource MOONLIGHT_STRIKE = new PantheonDamageSource("pantheon_starlight_strike");

    public static class PantheonDamageSource extends DamageSource {
		
		private boolean dropsPlayerLoot = false;

		protected PantheonDamageSource(String name) {
			super(name);
		}

		@Override
		public PantheonDamageSource setUnblockable() {
			super.setUnblockable();
			return this;
		}
		
		@Override
		public PantheonDamageSource setBypassesArmor() {
			super.setBypassesArmor();
			return this;
		}
		
		@Override
		public PantheonDamageSource setFromFalling() {
			super.setFromFalling();
			return this;
		}
		
		public PantheonDamageSource setDropsPlayerLoot() {
			this.dropsPlayerLoot = true;
			return this;
		}
		
		public boolean dropsPlayerLoot() {
			return this.dropsPlayerLoot;
		}
		
	}
}
