package me.badbones69.crazybot.bot.commands;

import com.ryderbelserion.vital.core.util.StringUtil;
import me.badbones69.crazybot.api.discord.commands.CommandContext;
import me.badbones69.crazybot.api.discord.commands.CommandEngine;
import me.badbones69.crazybot.api.discord.embed.Embed;
import me.badbones69.crazybot.api.discord.embed.EmbedColor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import java.util.List;

public class AboutCommand extends CommandEngine {

    public AboutCommand(final String name, final String description, final Permission permission) {
        super(name, description, permission, true);
    }

    @Override
    protected void perform(final CommandContext context) {
        final User author = context.getAuthor();
        final SelfUser bot = context.getBot();

        final Embed embed = new Embed()
                .description(
                        StringUtil.convertList(
                                List.of(
                                        String.format("Hello, My name is %s at your service.", bot.getAsMention()),
                                        "If you need to know how to use me, please type /help for a list of commands."
                                )
                        )
                )
                .timestamp("America/New_York")
                .color(EmbedColor.DEFAULT);

        final JDA jda = context.getJDA();

        if (context.getGuild() != null) {
            final Guild guild = context.getGuild();

            embed.addField("Statistics", StringUtil.convertList(
                    List.of(
                            String.format(":milk: Member Count: %s", guild.getMemberCount()),
                            String.format(":egg: Role Count: %s", guild.getRoles().size())
                    )
            ), false);

            embed.thumbnail(bot, guild)
                    .author(author, guild)
                    .footer(String.format("Average Ping: %s", jda.getGatewayPing()), guild.getIconUrl());
        } else {
            embed.thumbnail(bot)
                    .author(author)
                    .footer(String.format("Average Ping: %s", jda.getGatewayPing()), bot.getAvatarUrl());
        }

        embed.addField("Notice a Bug?", "[Click me to submit a bug report](https://github.com/Crazy-Crew/CrazyBot/issues)", false);

        embed.addField("Version", String.format("%1$s", ""));

        context.reply(embed.build(), true);
    }
}