package me.badbones69.crazybot.api.discord;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class VitalLogger extends Logger {

    public static Logger getLogger(final String prefix) {
        Logger logger = new VitalLogger(prefix);

        if (!LogManager.getLogManager().addLogger(logger)) {
            logger = LogManager.getLogManager().getLogger(prefix);
        }

        return logger;
    }

    private VitalLogger(final String prefix) {
        super(prefix, null);
    }
}