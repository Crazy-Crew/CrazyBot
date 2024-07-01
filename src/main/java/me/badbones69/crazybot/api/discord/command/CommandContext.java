package me.badbones69.crazybot.api.discord.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandContext {

    private final String command;

    private SlashCommandInteractionEvent slashEvent;

    public CommandContext(final SlashCommandInteractionEvent event, final String command) {
        this.slashEvent = event;

        this.command = command;
    }

    private MessageReceivedEvent receiveEvent;

    private String[] args;

    public CommandContext(final MessageReceivedEvent event, final String command, final String[] args) {
        this.receiveEvent = event;
        this.command = command;
        this.args = args;
    }

    public final boolean isSlashActive() {
        return this.slashEvent != null;
    }

    public final boolean isMessageActive() {
        return this.receiveEvent != null;
    }

    public final MessageChannelUnion getTextChannel() {
        if (this.slashEvent != null) {
            return this.slashEvent.getChannel();
        }

        return this.receiveEvent.getChannel();
    }

    public void reply(final String message, final boolean ephemeral) {
        if (this.slashEvent != null) {
            this.slashEvent.reply(message).setEphemeral(ephemeral).queue();

            return;
        }

        getTextChannel().sendMessage(message).queue();
    }

    public void reply(final MessageEmbed message, final boolean ephemeral) {
        if (this.slashEvent != null) {
            this.slashEvent.replyEmbeds(message).setEphemeral(ephemeral).queue();

            return;
        }

        getTextChannel().sendMessageEmbeds(message).queue();
    }

    public void reply(final String message) {
        reply(message, false);
    }

    public void reply(final MessageEmbed message) {
        reply(message, false);
    }

    public final CommandContext defer(final boolean ephemeral) {
        if (this.slashEvent != null) {
            this.slashEvent.deferReply(ephemeral).queue();

            return this;
        }

        return this;
    }

    public final String getCommand() {
        return this.command.replaceFirst("!", "");
    }

    public final String[] getArgs() {
        return this.args;
    }

    public final User getAuthor() {
        return this.slashEvent != null ? this.slashEvent.getUser() : this.receiveEvent.getAuthor();
    }

    public final Guild getGuild() {
        return this.slashEvent != null ? this.slashEvent.getGuild() : this.receiveEvent.getGuild();
    }

    public final SelfUser getBot() {
        return getJDA().getSelfUser();
    }

    public final JDA getJDA() {
        return this.slashEvent != null ? this.slashEvent.getJDA() : this.receiveEvent.getJDA();
    }
}