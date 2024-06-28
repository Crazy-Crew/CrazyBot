package me.badbones69.crazybot.bot.util.config;

import me.badbones69.crazybot.bot.util.enums.Files;
import org.simpleyaml.configuration.file.FileConfiguration;

public class ConfigFile {

    private static final FileConfiguration config = Files.config.getConfiguration();

    public static String getToken() {
        return config.getString("Settings.Token", "");
    }

    public static String getPrefix() {
        return config.getString("Settings.Prefix", "!");
    }
}