package nova.pantheon.item.crests;

import nova.pantheon.Pantheon;
import nova.pantheon.component.PantheonComponent;

public class StarCrest extends CrestItem {

    public StarCrest(Settings settings) {
        super(settings, Pantheon.STAR);
    }

    @Override
    void setPantheon(PantheonComponent component) {
        component.setPantheon(Pantheon.STAR);
        component.setMaxStarlight(5);
        System.out.println(component.getMaxStarlight());
        component.setStarlight(component.getMaxStarlight());
    }
}
