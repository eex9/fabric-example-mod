package nova.pantheon.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import nova.pantheon.Pantheon;
import nova.pantheon.init.PantheonComponents;

public class PlayerPantheon implements PantheonComponent, AutoSyncedComponent{
    private String pantheon;
    private PlayerEntity player;

    public PlayerPantheon(PlayerEntity _player) {
        this.player = _player;
        this.pantheon = Pantheon.EMPTY;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.pantheon = tag.getString("pantheon");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString("pantheon", this.pantheon);
    }

    @Override
    public String getPantheon() {
        return this.pantheon;
    }

    @Override
    public Text getTranslatable() {
        return Text.of("pantheons.pantheon." + this.getPantheon().toLowerCase(null));
    } 

    @Override
    public void setPantheon(String pan) {
        this.pantheon = pan;
        PantheonComponents.PlayerPantheonComponent.sync(this.player);
    }
}
