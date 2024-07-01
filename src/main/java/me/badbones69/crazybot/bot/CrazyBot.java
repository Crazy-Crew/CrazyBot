package me.badbones69.crazybot.bot;

import me.badbones69.crazybot.api.discord.VitalDiscord;
import me.badbones69.crazybot.api.discord.command.CommandMap;
import me.badbones69.crazybot.bot.commands.AboutCommand;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CrazyBot extends VitalDiscord {

    public CrazyBot(@NotNull final String token, @NotNull final String prefix, @NotNull final List<GatewayIntent> keys, @NotNull final List<CacheFlag> flags) {
        super(token, keys, flags);
    }

    @Override
    public void ready() {
        CommandMap map = new CommandMap(this.jda);

        List.of(
                new AboutCommand(this.properties.getProperty("version"))
        ).forEach(map::registerCommand);
    }
}