package me.badbones69.crazybot.bot;

import me.badbones69.crazybot.api.discord.VitalDiscord;
import me.badbones69.crazybot.api.discord.commands.CommandHandler;
import me.badbones69.crazybot.api.util.LogUtil;
import me.badbones69.crazybot.bot.commands.AboutCommand;
import me.badbones69.crazybot.bot.managers.StorageManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CrazyBot extends VitalDiscord {

    private final CommandHandler commandHandler;
    private final StorageManager storageManager;

    public CrazyBot(@NotNull final String token, @NotNull final List<GatewayIntent> keys, @NotNull final List<CacheFlag> flags, @NotNull final String folder) {
        super(LogUtil.getLogger(), folder, token, keys, flags);

        this.commandHandler = new CommandHandler(
                "!",
                this.jda
        );

        this.storageManager = new StorageManager(getLogger(), getDirectory());
    }

    @Override
    public void ready(@NotNull final Guild guild) {
        this.commandHandler.setGuild(guild);
    }

    @Override
    public void start() {
        getDirectory().mkdir();
    }

    @Override
    public void ready() {
        List.of(
                new AboutCommand("about", "Shows the information about the Discord Bot.", Permission.MESSAGE_SEND)
        ).forEach(this.commandHandler::addCommand);
    }

    @Override
    public void stop() {

    }

    public final StorageManager getStorageManager() {
        return this.storageManager;
    }
}