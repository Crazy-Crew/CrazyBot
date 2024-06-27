package me.badbones69.crazybot.api.util;

import ch.qos.logback.classic.Logger;
import me.badbones69.crazybot.bot.CrazyBot;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CrazyBot.class);

    public static void debug(final String message) {
        logger.debug(message);
    }

    public static Logger getLogger() {
        return logger;
    }
}