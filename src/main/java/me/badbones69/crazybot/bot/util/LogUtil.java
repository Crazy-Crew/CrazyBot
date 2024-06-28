package me.badbones69.crazybot.bot.util;

import ch.qos.logback.classic.Logger;
import me.badbones69.crazybot.bot.CrazyBot;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CrazyBot.class);

    public static void info(final String message) {
        logger.info(message);
    }

    public static void debug(final String message) {
        logger.debug(message);
    }

    public static void error(final String message) {
        logger.error(message);
    }

    public static Logger getLogger() {
        return logger;
    }
}