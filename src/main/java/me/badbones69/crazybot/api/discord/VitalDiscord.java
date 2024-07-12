package me.badbones69.crazybot.api.discord;

import me.badbones69.crazybot.api.discord.command.CommandHandler;
import me.badbones69.crazybot.api.discord.listeners.GenericListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public abstract class VitalDiscord {

    protected final Properties properties;
    protected final CommandHandler commandHandler;
    protected final JDA jda;

    public static String prefix = "";

    public VitalDiscord(final String token, final String prefix, final List<GatewayIntent> intents, final List<CacheFlag> flags) {
        final Properties properties = new Properties();

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            properties.load(loader.getResourceAsStream("build.properties"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.properties = properties;

        this.commandHandler = new CommandHandler(this.jda = JDABuilder.createDefault(token, intents).enableCache(flags).addEventListeners(new GenericListener(this)).build());

        setPrefix(prefix);
    }

    public static void setPrefix(final String prefix) {
        VitalDiscord.prefix = prefix;
    }

    public void ready(final Guild guild) {}

    public void start() {}

    public void ready() {}

    public void stop() {}

    public final CommandHandler getCommandMap() {
        return this.commandHandler;
    }
}