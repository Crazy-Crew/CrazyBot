package me.badbones69.crazybot.api.discord.storage;

import ch.qos.logback.classic.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sqlite {

    private Connection connection;
    private final Logger logger;
    private File file;

    public Sqlite(final Logger logger, final File file) {
        this.logger = logger;

        start(file);

        open();
    }

    public void open() {
        try (Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite: %s", this.file))) {
            this.connection = connection;
        } catch (SQLException exception) {
            this.logger.warn("Failed to create connection!");
        }
    }

    public void close() {
        if (getConnection() == null) return;

        try {
            getConnection().close();
        } catch (SQLException e) {
            this.logger.warn("Failed to close connection!");
        }
    }

    public final boolean isOpen() {
        if (getConnection() == null) return false;

        try {
            return getConnection().isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public final Connection getConnection() {
        return this.connection;
    }

    private void start(File file) {
        if (file.exists()) {
            return;
        }

        try {
            file.createNewFile();
        } catch (IOException exception) {
            this.logger.error(String.format("Failed to create %s", file.getName()));
        } finally {
            this.file = file;
        }
    }
}