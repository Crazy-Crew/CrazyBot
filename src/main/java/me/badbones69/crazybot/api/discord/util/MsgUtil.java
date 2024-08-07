package me.badbones69.crazybot.api.discord.util;

import me.badbones69.crazybot.api.discord.embed.Embed;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

/**
 * Utilities related to sending messages.
 *
 * @version 1.8
 * @since 1.8
 */
public class MsgUtil {

    /**
     * Checks if a command is a valid command.
     *
     * @param prefix the prefix
     * @param command the command
     * @return true or false
     */
    public static boolean isCommand(final String prefix, final String command) {
        return command.toLowerCase().startsWith(prefix);
    }

    /**
     * Gets the arguments from a command.
     *
     * @param command the command
     * @return the arguments
     */
    public static String[] getArguments(final String command) {
        return command.substring(1).split(" ");
    }

    /**
     * Get the command and return it.
     *
     * @param prefix the prefix
     * @param command the command
     * @return the string
     */
    public static String getCommand(final String prefix, final String command) {
        return command.substring(prefix.length()).toLowerCase();
    }

    /**
     * Get command without prefix from message.
     *
     * @param prefix the prefix to remove
     * @param command the command to check
     * @return the string without the prefix
     */
    public static String getCommandWithoutPrefix(final String prefix, final String command) {
        return getCommand(prefix, command).replace(prefix, "");
    }

    /**
     * Send a message to a channel
     *
     * @param message the message to send
     * @param guild the guild to send in
     * @param id channel id
     * @since 1.8
     */
    public static void sendMessage(final String message, final Guild guild, final long id) {
        if (message.isEmpty() || message.isBlank()) return;

        TextChannel channel = guild.getTextChannelById(id);

        if (channel == null) return;

        channel.sendMessage(message).queue();
    }

    /**
     * Send a message to a channel.
     *
     * @param member the member to send to
     * @param guild the guild to send in
     * @param title embed title
     * @param description embed description
     * @param color embed color
     * @param id channel id
     * @since 1.8
     */
    public static void sendMessage(final Member member,
                                   final Guild guild,
                                   final String title,
                                   final String description,
                                   final String color,
                                   final long id) {
        final Embed embed = new Embed();

        embed.author(title, member.getAvatarUrl());

        if (description != null) embed.description(description);

        color(guild, id, color, embed);
    }

    /**
     * Send a message to a channel.
     *
     * @param guild the guild to send in
     * @param id channel id
     * @param color embed color
     * @param description embed description
     * @since 1.8
     */
    public static void sendMessage(final Guild guild, final long id, final String color, final String description) {
        Embed embed = new Embed();

        embed.description(description);

        color(guild, id, color, embed);
    }

    /**
     * Send a message with color.
     *
     * @param guild the guild to send in
     * @param id channel id
     * @param color embed color
     * @param embed embed object
     * @since 1.8
     */
    private static void color(final Guild guild, final long id, final String color, final Embed embed) {
        embed.color(color);

        final MessageEmbed message = embed.build();
        final TextChannel channel = guild.getTextChannelById(id);

        if (channel == null) return;

        channel.sendMessageEmbeds(message).queue();
    }
}