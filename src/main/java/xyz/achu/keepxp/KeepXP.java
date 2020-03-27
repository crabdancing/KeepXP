package xyz.achu.keepxp;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeepXP implements ModInitializer {
	public static final String MODID = "keepxp";

	public static final Logger logger = LogManager.getLogger();
	public static int experienceOnDeath = -1;

	@Override
	public void onInitialize() {

	}
}
