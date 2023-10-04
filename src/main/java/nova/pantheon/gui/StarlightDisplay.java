package nova.pantheon.gui;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import nova.pantheon.Pantheon;
import nova.pantheon.component.PantheonComponent;
import nova.pantheon.init.PantheonComponents;

public class StarlightDisplay {

	public static StarlightDisplay INSTANCE;

	private static final Identifier modIcons = new Identifier("pantheon", "textures/gui/icons.png");

	public static void init() {
		INSTANCE = new StarlightDisplay();
	}

	public static void onRender(MatrixStack matrixStack, int scaledWidth, int scaledHeight, PlayerEntity player) {
		PantheonComponent pantheonComponent = PantheonComponents.PlayerPantheonComponent.get(player);
		if ((pantheonComponent.getMaxStarlight() > 0) && (pantheonComponent.getPantheon().equals(Pantheon.STAR))) {
            // System.out.println("test");
			int starlight = (int) Math.ceil(pantheonComponent.getStarlight());
			int maxStarlight = pantheonComponent.getMaxStarlight();
			int absorptionAmount = MathHelper.ceil(player.getAbsorptionAmount());

			int fullCanisters = starlight / 20;
			int emptyCanisters = (maxStarlight / 20) - fullCanisters;
			int displayedHearts = starlight % 20;
			if (displayedHearts == 0 && starlight > 0) { // if the row is full render it as full instead of wrapping over
				displayedHearts = 20;
				fullCanisters--;
				emptyCanisters++;
			}
			int renderedOutlines = emptyCanisters > 0 ? 10
					: ((maxStarlight % 20 / 2) + (maxStarlight % 2 == 0 ? 0 : 1));
			boolean renderBackRow = fullCanisters > 0;

			boolean isUnderwater = player.isSubmergedIn(FluidTags.WATER);
			RenderSystem.setShaderTexture(0, modIcons);

			int width = scaledWidth / 2; //  - 82
			int height = scaledHeight - 49;

			int y = isUnderwater
					? height + Pantheon.PANTHEON_CONFIG.StarlightOffsetUnderwater + Pantheon.PANTHEON_CONFIG.StarlightOffsetY
					: height + Pantheon.PANTHEON_CONFIG.StarlightOffsetY;
			if (absorptionAmount > 0) {
				int absorptionRows = (int) Math.ceil(absorptionAmount / 20.0F);
				int absorptionRowHeight = 10 - (absorptionRows - 2);
				y -= absorptionRows * Math.max(absorptionRowHeight, 3);
			}
			int x = width + 9 + Pantheon.PANTHEON_CONFIG.StarlightOffsetX;

			// back row
			if (renderBackRow) {
				for (int i = displayedHearts / 2; i < 10; i++) {
					InGameHud.drawTexture(matrixStack, x + i * 8, y, 36, 9, 9, 9, 256, 256); // "back row" icon
				}
			}

			// outline
			for (int i = 0; i < renderedOutlines; i++) {
				if (renderBackRow) {
					InGameHud.drawTexture(matrixStack, x + i * 8, y, 45, 9, 9, 9, 256, 256); // background
				} else {
					InGameHud.drawTexture(matrixStack, x + i * 8, y, 0, 9, 9, 9, 256, 256); // background
				}
			}

			// hearts
			for (int i = 0; i < displayedHearts; i++) {
				int q = i * 2 + 1;
				if (q < displayedHearts) {
					InGameHud.drawTexture(matrixStack, x + i * 8, y, 18, 9, 9, 9, 256, 256); // full charge icon
				} else if (q == displayedHearts) {
					InGameHud.drawTexture(matrixStack, x + i * 8, y, 27, 9, 9, 9, 256, 256); // half charge icon
				}
			}

			// canisters
			for (int i = 0; i < fullCanisters; i++) {
				InGameHud.drawTexture(matrixStack, x + i * 6, y - 9, 0, 0, 9, 9, 256, 256); // full canisters
			}
			for (int i = fullCanisters; i < fullCanisters + emptyCanisters; i++) {
				InGameHud.drawTexture(matrixStack, x + i * 6, y - 9, 9, 0, 9, 9, 256, 256); // empty canisters
			}

			RenderSystem.setShaderTexture(0, Screen.GUI_ICONS_TEXTURE);
		}

	}
}
