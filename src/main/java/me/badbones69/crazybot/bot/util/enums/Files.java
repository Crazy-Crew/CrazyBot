package me.badbones69.crazybot.bot.util.enums;

import me.badbones69.crazybot.api.discord.JavaBot;
import me.badbones69.crazybot.bot.CrazyBot;
import org.simpleyaml.configuration.file.YamlFile;

public enum Files {

    config("config.yml");

    private final CrazyBot bot = JavaBot.getInstance(CrazyBot.class);

    private final String fileName;

    Files(final String fileName) {
        this.fileName = fileName;
    }

    public final YamlFile getConfiguration() {
        return this.bot.getFileManager().getFile(this.fileName).getConfiguration();
    }
}