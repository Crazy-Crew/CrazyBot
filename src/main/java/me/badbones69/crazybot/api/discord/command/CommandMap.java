package me.badbones69.crazybot.api.discord.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandMap {

    protected Map<String, Command> commands;

    private final JDA jda;

    public CommandMap(final JDA jda) {
        this.commands = new HashMap<>();

        this.jda = jda;
    }

    public void registerCommand(final Command command) {
        if (command.isSlashCommand) {
            this.jda.upsertCommand(command.command, command.description)
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.permission))
                    .queue();
        } else {
            this.commands.put(command.command, command);
        }

        this.jda.addEventListener(command);
    }

    public void unregisterCommand(final Command command) {
        if (command.isSlashCommand) {
            this.jda.deleteCommandById(command.command);
        } else {
            this.commands.remove(command.command);
        }

        this.jda.removeEventListener(command);
    }

    public final Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(this.commands);
    }
}