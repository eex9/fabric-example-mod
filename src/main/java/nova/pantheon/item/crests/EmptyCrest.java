package nova.pantheon.item.crests;

import nova.pantheon.Pantheon;
import nova.pantheon.component.PantheonComponent;

public class EmptyCrest extends CrestItem {
    public EmptyCrest(Settings settings) {
        super(settings, Pantheon.EMPTY);
    }

    @Override
    void setPantheon(PantheonComponent component) {
        component.setPantheon(Pantheon.EMPTY);
        component.setStarlight(0);
        component.setMaxStarlight(0);
        component.setCheatedDeath(false);
    }
}
