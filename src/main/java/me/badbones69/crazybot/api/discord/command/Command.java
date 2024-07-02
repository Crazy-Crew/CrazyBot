package me.badbones69.crazybot.api.discord.command;

import me.badbones69.crazybot.api.discord.VitalDiscord;
import me.badbones69.crazybot.api.discord.util.MsgUtil;
import me.badbones69.crazybot.api.discord.util.RoleUtil;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public abstract class Command extends ListenerAdapter {

    protected final boolean isSlashCommand;
    protected final Permission permission;
    protected final String description;
    protected final String command;

    public Command(final boolean isSlashCommand, final Permission permission, final String description, final String command) {
        this.isSlashCommand = isSlashCommand;
        this.description = description;
        this.permission = permission;
        this.command = command;
    }

    public void execute(CommandContext context) {}

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!this.isSlashCommand || !event.getName().equals(this.command) || this.command.isBlank()) return;

        execute(new CommandContext(event, this.command));
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (this.isSlashCommand || event.getAuthor().isBot() || event.isWebhookMessage()) return;

        final String message = event.getMessage().getContentStripped();

        if (!MsgUtil.isCommand(VitalDiscord.prefix, message)) return;

        final User user = event.getAuthor();

        if (!(user instanceof Member member)) return;

        final Role role = RoleUtil.getHighestRole(member);

        final boolean hasPermission = role == null ?
                member.hasPermission(this.permission) :
                role.hasPermission(this.permission);

        if (!hasPermission) return;

        execute(new CommandContext(event, MsgUtil.getCommand(VitalDiscord.prefix, message), MsgUtil.getArguments(message)));
    }
}