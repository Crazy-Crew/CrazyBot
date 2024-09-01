package me.badbones69.crazybot.bot;

import me.badbones69.crazybot.api.discord.JavaBot;
import me.badbones69.crazybot.bot.commands.AboutCommand;
import me.badbones69.crazybot.bot.util.config.ConfigFile;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class CrazyBot extends JavaBot {

    public CrazyBot() {}

    @Override
    public final List<GatewayIntent> getIntents() {
        return List.of(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.DIRECT_MESSAGES,

                GatewayIntent.MESSAGE_CONTENT
        );
    }

    @Override
    public final List<CacheFlag> getFlags() {
        return List.of(
                CacheFlag.ACTIVITY
        );
    }

    @Override
    public final String getToken() {
        return ConfigFile.getToken();
    }

    @Override
    public final String getPrefix() {
        return ConfigFile.getPrefix();
    }

    @Override
    public void start() {
        getComponentLogger().warn("<red>Beans");

        //getFileManager().addFile(new File(getDirectory(), "config.yml")).init();

        //super.start();
    }

    @Override
    public void ready() {
        List.of(
                new AboutCommand(getProperties().getProperty("version"))
                //new SmugCommand()
        ).forEach(getCommandMap()::registerCommand);

        new Timer().scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        switch (ThreadLocalRandom.current().nextInt(3)) {
                            case 0 -> getJda().getPresence().setPresence(OnlineStatus.ONLINE, Activity.customStatus("Type /about to see my stats!"));
                            case 1 -> getJda().getPresence().setPresence(OnlineStatus.ONLINE, Activity.customStatus("I can smell you!"));
                            case 2 -> getJda().getPresence().setPresence(OnlineStatus.ONLINE, Activity.customStatus("Playing Wizard101"));
                        }
                    }
                },
                0,
                60000
        );
    }

    @Override
    public void ready(Guild guild) {
        super.ready(guild);
    }

    @Override
    public final String getPluginName() {
        return "CrazyBot";
    }
}