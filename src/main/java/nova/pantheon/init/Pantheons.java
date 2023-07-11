package nova.pantheon.init;

import nova.pantheon.pantheons.*;

public class Pantheons {
    public static PantheonClass PANTHEON_STAR;
    public static PantheonClass PANTHEON_DEATH;
    public static PantheonClass PANTHEON_BLOOD;

    public void init() {
        PANTHEON_STAR = new PantheonClass("Star");
        PANTHEON_DEATH = new PantheonClass("Death");
        PANTHEON_BLOOD = new PantheonClass("Blood");
    }
}
