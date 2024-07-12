package me.badbones69.crazybot.api.discord.listeners;

import me.badbones69.crazybot.api.discord.VitalDiscord;
import me.badbones69.crazybot.api.discord.command.Command;
import me.badbones69.crazybot.api.discord.command.CommandContext;
import me.badbones69.crazybot.api.discord.util.MsgUtil;
import me.badbones69.crazybot.api.discord.util.RoleUtil;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GenericListener extends ListenerAdapter {

    private final VitalDiscord vital;

    public GenericListener(final VitalDiscord vital) {
        this.vital = vital;
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        this.vital.ready(event.getGuild());
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        this.vital.stop();
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        this.vital.ready();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage()) return;

        final String message = event.getMessage().getContentStripped();

        if (!MsgUtil.isCommand(VitalDiscord.prefix, message)) return;

        final Member member = event.getMember();

        if (member == null) return;

        final Command command = this.vital.getCommandMap().getCommand(MsgUtil.getCommandWithoutPrefix(VitalDiscord.prefix, message));

        if (command == null || hasPermission(member, command)) return;

        command.execute(new CommandContext(event, MsgUtil.getCommand(VitalDiscord.prefix, message), MsgUtil.getArguments(message)));
    }

    private boolean hasPermission(Member member, Command command) {
        final Role role = RoleUtil.getHighestRole(member);

        final Permission permission = command.getPermission();

        return role == null ? !member.hasPermission(permission) : !role.hasPermission(permission);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        final Command command = this.vital.getCommandMap().getCommand(event.getName());

        if (command == null || command.getCommandData() == null) return;

        command.execute(new CommandContext(event, event.getName()));
    }
}