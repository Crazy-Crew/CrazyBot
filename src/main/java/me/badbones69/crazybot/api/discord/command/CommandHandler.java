package me.badbones69.crazybot.api.discord.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    protected Map<String, Command> commands;

    private final JDA jda;

    public CommandHandler(final JDA jda) {
        this.commands = new HashMap<>();

        this.jda = jda;
    }

    public void registerCommand(final Command command) {
        if (command.isSlashCommand()) {
            this.jda.upsertCommand(command.getCommandData()).queue();
        }

        this.commands.put(command.command, command);
    }

    public final Command getCommand(final String command) {
        return this.commands.get(command);
    }

    public void unregisterCommand(final Command command) {
        if (command.isSlashCommand()) {
            final CommandData data = command.getCommandData();

            if (data != null) {
                this.jda.deleteCommandById(data.getName());

            }
        }

        this.commands.remove(command.command);
    }

    public final Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(this.commands);
    }
}