package me.badbones69.crazybot.api.discord.embed;

import me.badbones69.crazybot.api.discord.util.ColorUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class Embed {

    private final EmbedBuilder builder = new EmbedBuilder();
    private final EmbedField field = new EmbedField(this.builder);

    /**
     * Sets the title of the embed.
     *
     * @param title the text in the title
     * @return the embed class with updated information
     */
    public final Embed title(final String title) {
        this.builder.setTitle(title);

        return this;
    }

    /**
     * Sets the footer using text/icon
     *
     * @param text the text in the footer
     * @param icon the icon in the footer
     * @return the embed class with updated information
     */
    public final Embed footer(final String text, final String icon) {
        this.builder.setFooter(text, icon);

        return this;
    }

    /**
     * Sets the footer using the user object.
     *
     * @param user the user in the footer
     * @return the embed class with updated information
     */
    public final Embed footer(final User user) {
        this.builder.setFooter("Requested by " + user.getAsMention(), user.getEffectiveAvatarUrl());

        return this;
    }

    /**
     * Set the footer using the user object.
     *
     * @param user the member in question
     */
    public final Embed footer(final User user, final Guild guild) {
        final Member member = guild.getMember(user);

        if (member == null) return this;

        String avatar = member.getEffectiveAvatarUrl();

        this.builder.setFooter("Requested by " + user.getAsMention(), avatar);

        return this;
    }

    /**
     * Sets the description of the embed.
     *
     * @param text the text to use
     * @return the embed class with updated information
     */
    public final Embed description(final String text) {
        this.builder.setDescription(text);

        return this;
    }

    /**
     * Sets the thumbnail using a url.
     *
     * @param url the url to use
     * @return the embed class with updated information
     */
    public final Embed thumbnail(final String url) {
        this.builder.setThumbnail(url);

        return this;
    }

    /**
     * Sets the thumbnail using a user object.
     *
     * @param user the user to use
     * @return the embed class with updated information
     */
    public final Embed thumbnail(final User user) {
        thumbnail(user.getEffectiveAvatarUrl());

        return this;
    }

    /**
     * Set the thumbnail using the user object.
     *
     * @param user the member in question
     * @param guild fetch the member's guild avatar otherwise fetches global avatar
     */
    public final Embed thumbnail(final User user, final Guild guild) {
        final Member member = guild.getMember(user);

        if (member == null) return this;

        String avatar = member.getEffectiveAvatarUrl();

        this.builder.setThumbnail(avatar);

        return this;
    }

    /**
     * Sets the embed image using an url.
     *
     * @param url the url to use
     * @return the embed class with updated information
     */
    public final Embed image(final String url) {
        this.builder.setImage(url);

        return this;
    }

    /**
     * Sets the embed image using a user object.
     *
     * @param user the user to use
     * @return the embed class with updated information
     */
    public final Embed image(final User user) {
        this.builder.setImage(user.getEffectiveAvatarUrl());

        return this;
    }

    /**
     * Sets the thumbnail using a url.
     *
     * @param url the url to use
     * @return the embed class with updated information
     */
    public final Embed author(final String name, final String url) {
        this.builder.setAuthor(name, null, url);

        return this;
    }

    /**
     * Sets the thumbnail using a user object.
     *
     * @param user the user to use
     * @return the embed class with updated information
     */
    public final Embed author(final User user) {
        author(user.getEffectiveName(), user.getEffectiveAvatarUrl());

        return this;
    }

    /**
     * Set the thumbnail using the user object.
     *
     * @param user the member in question
     * @param guild fetch the member's guild avatar otherwise fetches global avatar
     */
    public final Embed author(final User user, final Guild guild) {
        final Member member = guild.getMember(user);

        if (member == null) return this;

        String avatar = member.getEffectiveAvatarUrl();

        author(member.getEffectiveName(), avatar);

        return this;
    }

    /**
     * Sets the color of the embed.
     *
     * @param color the color to use
     * @return the embed class with updated information
     */
    public final Embed color(final String color) {
        this.builder.setColor(ColorUtil.toColor(color));

        return this;
    }

    /**
     * Set a color using one of our pre-set colors.
     *
     * @param color - A preset enum of colors
     */
    public final Embed color(final EmbedColor color) {
        this.builder.setColor(color.getColor());

        return this;
    }

    /**
     * Sets the timezone in the embed.
     *
     * @param timezone the timezone to use for embeds
     * @return the embed class with updated information
     */
    public final Embed timestamp(final String timezone) {
        this.builder.setTimestamp(LocalDateTime.now().atZone(ZoneId.of(timezone)));

        return this;
    }

    public final Embed fields(List<MessageEmbed.Field> fields) {
        fields.forEach(this.field::field);

        return this;
    }

    /**
     * @return the built embed
     */
    public final MessageEmbed build() {
        return this.builder.build();
    }
}