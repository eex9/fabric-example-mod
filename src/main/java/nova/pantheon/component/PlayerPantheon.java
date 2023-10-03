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

    private int maxStarlight;
    private float starlight;

    private boolean cheatedDeath;


    public PlayerPantheon(PlayerEntity _player) {
        this.player = _player;
        this.pantheon = Pantheon.EMPTY;

        this.maxStarlight = -1;
        this.starlight = -1;

        this.cheatedDeath = false;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.pantheon = tag.getString("pantheon");

        this.maxStarlight = tag.getInt("max_starlight");
        this.starlight = tag.getInt("starlight");

        this.cheatedDeath = tag.getBoolean("cheated_death");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString("pantheon", this.pantheon);

        tag.putFloat("max_starlight", this.maxStarlight);
        tag.putFloat("starlight", this.starlight);

        tag.putBoolean("cheated_death", cheatedDeath);
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

    @Override
    public boolean isDeath() {
        return (this.getPantheon().equals(Pantheon.DEATH));
    }

    @Override
    public boolean hasCheatedDeath() {
        return this.cheatedDeath;
    }

    @Override
    public void setCheatedDeath(boolean set) {
        this.cheatedDeath = set;
    }

    @Override
    public void cheatDeath() {
        this.cheatedDeath = true;
    }

    @Override
    public void recharge() {
        this.cheatedDeath = false;
    }

    @Override
    public boolean isStar(PlayerEntity player) {
        return (this.getPantheon().equals(Pantheon.STAR));
    }

    @Override
    public int getMaxStarlight() {
        return this.maxStarlight;
    }

    @Override
    public float getStarlight() {
        return this.starlight;
    }

    @Override
    public void setMaxStarlight(int newMax) {
        this.maxStarlight = newMax;
        this.setStarlight(newMax);
    }

    @Override
    public void setStarlight(float newStar) {
        this.starlight = newStar;
    }

    @Override
    public void incMaxStarlight(int amount) {
        this.maxStarlight += amount;
        this.gainStarlight(amount);
    }

    @Override
    public void gainStarlight(float amount) {
        this.starlight += amount;
        this.starlight = Math.min(this.starlight, this.maxStarlight);
    }

    @Override
    public void useStarlight(float amount) {
        this.starlight -= amount;
        this.starlight = Math.max(this.starlight, 0);
    }
}
