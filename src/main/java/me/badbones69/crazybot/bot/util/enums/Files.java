package me.badbones69.crazybot.bot.util.enums;

import me.badbones69.crazybot.Main;
import org.simpleyaml.configuration.file.FileConfiguration;

public enum Files {

    config("config.yml");

    private final String fileName;

    Files(final String fileName) {
        this.fileName = fileName;
    }

    public final FileConfiguration getConfiguration() {
        return Main.getFileManager().getFile(getFileName());
    }

    public final String getStrippedName() {
        return getFileName().replace(".yml", "");
    }

    public final String getFileName() {
        return this.fileName;
    }
}