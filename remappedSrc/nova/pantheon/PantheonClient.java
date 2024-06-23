package nova.pantheon;

import net.fabricmc.api.ClientModInitializer;
import nova.pantheon.init.PantheonItems;

public class PantheonClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PantheonItems.initClient();
    }
}
