package me.badbones69.crazybot.bot.managers;

import ch.qos.logback.classic.Logger;
import me.badbones69.crazybot.api.discord.storage.Sqlite;
import java.io.File;

public class StorageManager {

    private final Logger logger;
    private final File dataFolder;

    public StorageManager(final Logger logger, final File dataFolder) {
        this.logger = logger;
        this.dataFolder = dataFolder;
    }

    private Sqlite database;

    public void open() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception) {
            this.logger.warn("Sqlite was not found on the classpath.");
        }

        database = new Sqlite(this.logger, new File(this.dataFolder, "crazybot.db"));
    }

    public void close() {
        this.database.close();
    }

    public final Sqlite getData() {
        return this.database;
    }
}