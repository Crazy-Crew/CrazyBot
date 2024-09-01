package me.badbones69.crazybot.api.discord;

import com.ryderbelserion.vital.common.VitalAPI;
import com.ryderbelserion.vital.common.managers.files.FileManager;
import me.badbones69.crazybot.api.discord.command.CommandMap;
import me.badbones69.crazybot.api.discord.listeners.GenericListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public abstract class JavaBot implements VitalAPI {

    private final FileManager fileManager;
    private final ComponentLogger logger;
    private final Properties properties;
    private final File directory;

    public JavaBot() {
        VitalAPI.super.start();

        final Properties properties = new Properties();

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            properties.load(loader.getResourceAsStream("build.properties"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.directory = new File(getPluginName());
        this.fileManager = new FileManager();
        this.logger = ComponentLogger.logger(getPluginName());
        this.properties = properties;
    }

    private CommandMap commandMap;
    private JDA jda;

    public abstract List<GatewayIntent> getIntents();

    public abstract List<CacheFlag> getFlags();

    public abstract String getToken();

    public abstract String getPrefix();

    public void ready(final Guild guild) {}

    public void start() {
        this.commandMap = new CommandMap(this.jda = JDABuilder.createDefault(getToken(), getIntents()).enableCache(getFlags()).addEventListeners(new GenericListener(this)).build());
        this.commandMap.setPrefix(getPrefix());
    }

    public void ready() {}

    public void stop() {}

    public final CommandMap getCommandMap() {
        return this.commandMap;
    }

    public final Properties getProperties() {
        return this.properties;
    }

    public final JDA getJda() {
        return this.jda;
    }

    @Override
    public final FileManager getFileManager() {
        return this.fileManager;
    }

    @Override
    public final File getDirectory() {
        return this.directory;
    }

    @Override
    public final ComponentLogger getComponentLogger() {
        return this.logger;
    }
}