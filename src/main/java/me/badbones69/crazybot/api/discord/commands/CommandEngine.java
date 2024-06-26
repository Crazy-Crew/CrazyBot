package me.badbones69.crazybot.api.discord.commands;

import me.badbones69.crazybot.api.discord.util.StringUtil;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public abstract class CommandEngine extends ListenerAdapter {

    private final boolean isSlashCommand;
    private final Permission permission;
    private final String description;
    private final String name;

    public CommandEngine(final String name, final String description, final Permission permission, boolean isSlashCommand) {
        this.isSlashCommand = isSlashCommand;
        this.description = description;
        this.permission = permission;
        this.name = name;
    }

    protected abstract void perform(final CommandContext context);

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!this.isSlashCommand || !event.getName().equals(this.name)) return;

        CommandContext context = new CommandContext(event);

        perform(context);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (this.isSlashCommand) return;

        final Message message = event.getMessage();

        if (!message.getContentRaw().startsWith(this.name)) return;

        final CommandContext context = new CommandContext(event, StringUtil.getArguments(message.getContentRaw()));

        if (!context.checkRequirements(this.permission, false)) return;

        perform(context);
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final Permission getPermission() {
        return this.permission;
    }
}