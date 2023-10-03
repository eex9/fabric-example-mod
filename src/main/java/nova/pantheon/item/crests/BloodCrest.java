package nova.pantheon.item.crests;

import nova.pantheon.Pantheon;
import nova.pantheon.component.PantheonComponent;

public class BloodCrest extends CrestItem {
    public BloodCrest(Settings settings) {
        super(settings, Pantheon.BLOOD);
    }

    @Override
    void setPantheon(PantheonComponent component) {
        component.setPantheon(Pantheon.BLOOD);
    }
}
