package me.badbones69.crazybot.api.util;

import me.badbones69.crazybot.bot.CrazyBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(CrazyBot.class);

    public static void debug(final String message) {
        logger.debug(message);
    }

    public static Logger getLogger() {
        return logger;
    }
}