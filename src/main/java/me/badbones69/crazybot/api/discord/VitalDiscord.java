package me.badbones69.crazybot.api.discord;

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

public abstract class VitalDiscord {

    protected final Properties properties;
    protected final JDA jda;

    public VitalDiscord(final String token, final List<GatewayIntent> intents, final List<CacheFlag> flags) {
        final Properties properties = new Properties();

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            properties.load(loader.getResourceAsStream("build.properties"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.properties = properties;

        this.jda = JDABuilder.createDefault(token, intents).enableCache(flags).addEventListeners(new GenericListener(this)).build();
    }

    public void ready(final Guild guild) {}

    public void start() {}

    public void ready() {}

    public void stop() {}

    public static <T extends VitalDiscord> T getInstance(@NotNull final Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();

            constructor.setAccessible(true);

            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error creating instance of " + clazz, e);
        }
    }
}