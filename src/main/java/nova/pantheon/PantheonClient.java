package nova.pantheon;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import nova.pantheon.block.PedestalBlockRenderer;
import nova.pantheon.entity.StarlightStrikeEntityRenderer;
import nova.pantheon.gui.StarlightDisplay;
import nova.pantheon.init.PantheonBlocks;
import nova.pantheon.init.PantheonEntities;
import nova.pantheon.init.PantheonItems;

public class PantheonClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PantheonItems.initClient();
        StarlightDisplay.init();
        EntityRendererRegistry.register(PantheonEntities.STARLIGHT_STRIKE_ENTITY, StarlightStrikeEntityRenderer::new);
        BlockEntityRendererFactories.register(PantheonBlocks.ITEM_PEDESTAL_BLOCKENTITY, PedestalBlockRenderer::new);
    }
}
