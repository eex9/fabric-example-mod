package nova.pantheon.component;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import nova.pantheon.init.PantheonComponents;

public interface PantheonComponent extends Component{
    String getPantheon();
    Text getTranslatable();

    void setPantheon(String pan);

    static void sync(PlayerEntity player) {
        PantheonComponents.PlayerPantheonComponent.sync(player);
    }

    boolean isStar(PlayerEntity player);

    int getMaxStarlight();
    float getStarlight();

    void setMaxStarlight(int newMax);
    void setStarlight(float newStar);

    void incMaxStarlight(int amount);
    void gainStarlight(float amount);
    void useStarlight(float amount);

    boolean isDeath();

    boolean hasCheatedDeath();
    void setCheatedDeath(boolean set);
    void cheatDeath();
    void recharge();
}
