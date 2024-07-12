package me.badbones69.crazybot.bot;

import me.badbones69.crazybot.api.discord.VitalDiscord;
import me.badbones69.crazybot.bot.commands.AboutCommand;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class CrazyBot extends VitalDiscord {

    public CrazyBot(@NotNull final String token, @NotNull final String prefix, @NotNull final List<GatewayIntent> keys, @NotNull final List<CacheFlag> flags) {
        super(token, prefix, keys, flags);
    }

    @Override
    public void ready() {
        List.of(
                new AboutCommand(this.properties.getProperty("version"))
                //new SmugCommand()
        ).forEach(this.commandHandler::registerCommand);

        new Timer().scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        switch (ThreadLocalRandom.current().nextInt(3)) {
                            case 0 -> jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.customStatus("Type /about to see my stats!"));
                            case 1 -> jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.customStatus("I can smell you!"));
                            case 2 -> jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.customStatus("Playing Wizard101"));
                        }
                    }
                },
                0,
                60000
        );
    }

    @Override
    public void ready(Guild guild) {

    }
}