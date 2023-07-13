package nova.pantheon.component;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import nova.pantheon.init.PantheonComponents;

public interface PantheonComponent extends Component {
    String getPantheon();
    Text getTranslatable();

    void setPantheon(String pan);

    static void sync(PlayerEntity player) {
        PantheonComponents.PlayerPantheonComponent.sync(player);
    }
}
