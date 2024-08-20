package me.badbones69.crazybot.api.discord;

import com.ryderbelserion.vital.common.VitalAPI;
import me.badbones69.crazybot.api.discord.command.CommandMap;
import me.badbones69.crazybot.api.discord.listeners.GenericListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public abstract class JavaBot implements VitalAPI {

    private final Properties properties;
    private CommandMap commandMap;
    private JDA jda;

    public JavaBot() {
        final Properties properties = new Properties();

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            properties.load(loader.getResourceAsStream("build.properties"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.properties = properties;

        VitalAPI.super.start();
    }

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

    public static <T extends JavaBot> T getInstance(@NotNull Class<T> classObject) {
        try {
            Constructor<T> constructor = classObject.getDeclaredConstructor();

            constructor.setAccessible(true); // Allow access to private constructors

            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException("Error creating instance of " + classObject, exception);
        }
    }
}