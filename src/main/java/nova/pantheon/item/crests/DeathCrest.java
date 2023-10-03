package nova.pantheon.item.crests;

import nova.pantheon.Pantheon;
import nova.pantheon.component.PantheonComponent;

public class DeathCrest extends CrestItem{
    public DeathCrest(Settings settings) {
        super(settings, Pantheon.DEATH);
    }

    @Override
    void setPantheon(PantheonComponent component) {
        component.setPantheon(Pantheon.DEATH);
        component.setCheatedDeath(false);
    }
}
