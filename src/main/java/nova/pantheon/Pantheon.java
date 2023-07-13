package nova.pantheon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

import nova.pantheon.init.*;

public class Pantheon implements ModInitializer {
    public static final String MODID = "pantheon";

    public static final String STAR = "STAR";
    public static final String DEATH = "DEATH";
    public static final String BLOOD = "BLOOD";
    public static final String EMPTY = "EMPTY";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("hello minecraft world :3");

        PantheonItems.init();
    }
}