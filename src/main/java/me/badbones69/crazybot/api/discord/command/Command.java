package me.badbones69.crazybot.api.discord.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.Nullable;

public abstract class Command {

    protected final Permission permission;
    protected final String description;
    protected final String command;

    public Command(final Permission permission, final String description, final String command) {
        this.description = description;
        this.permission = permission;
        this.command = command;
    }

    public void execute(final CommandContext context) {}

    @Nullable
    public CommandData getCommandData() {
        return null;
    }

    public final boolean isSlashCommand() {
        return getCommandData() != null;
    }

    public final Permission getPermission() {
        return this.permission;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getCommand() {
        return this.command;
    }
}