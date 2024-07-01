package me.badbones69.crazybot;

import com.ryderbelserion.vital.core.Vital;
import me.badbones69.crazybot.api.discord.util.files.FileManager;
import me.badbones69.crazybot.bot.CrazyBot;
import me.badbones69.crazybot.bot.util.config.ConfigFile;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class Main extends Vital {

    private static final File dataFolder = new File("vital");
    private static final FileManager fileManager = new FileManager(dataFolder);

    public static void main(final String[] args) {
        dataFolder.mkdir();

        fileManager.addFile("config.yml").init();

        new CrazyBot(
                ConfigFile.getToken(),
                ConfigFile.getPrefix(),
                List.of(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.DIRECT_MESSAGES,

                        GatewayIntent.MESSAGE_CONTENT
                ),
                List.of(
                        CacheFlag.ACTIVITY
                )
        ).start();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    @Override
    public @NotNull File getDirectory() {
        return dataFolder;
    }

    @Override
    public @NotNull Logger getLogger() {
        return null;
    }

    @Override
    public boolean isLogging() {
        return false;
    }
}