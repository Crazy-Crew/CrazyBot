package me.badbones69.crazybot.api.discord.command;

import me.badbones69.crazybot.api.discord.util.MsgUtil;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public abstract class Command extends ListenerAdapter {

    protected final boolean isSlashCommand;
    protected final String command;

    public Command(final boolean isSlashCommand, final String command) {
        this.isSlashCommand = isSlashCommand;
        this.command = command;
    }

    public void execute(CommandContext context) {}

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!this.isSlashCommand || !event.getName().equals(this.command) || this.command.isBlank()) return;

        execute(new CommandContext(this.command, event.getChannel().asTextChannel(), null));
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (this.isSlashCommand || event.getAuthor().isBot() || event.isWebhookMessage()) return;

        final String message = event.getMessage().getContentStripped();

        if (!MsgUtil.isCommand("!", message)) return;

        execute(new CommandContext(MsgUtil.getCommand("!", message), event.getChannel().asTextChannel(), MsgUtil.getArguments(message)));
    }
}