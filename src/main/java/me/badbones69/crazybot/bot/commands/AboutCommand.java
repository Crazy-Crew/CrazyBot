package me.badbones69.crazybot.bot.commands;

import com.ryderbelserion.vital.core.util.StringUtil;
import me.badbones69.crazybot.api.discord.command.Command;
import me.badbones69.crazybot.api.discord.command.CommandContext;
import me.badbones69.crazybot.api.discord.embed.Embed;
import me.badbones69.crazybot.api.discord.embed.EmbedColor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import java.util.List;

public class AboutCommand extends Command {

    private final String version;

    public AboutCommand(final String version) {
        super(true, Permission.MESSAGE_SEND, "Shows information about the discord bot.", "about");

        this.version = version;
    }

    @Override
    public void execute(CommandContext context) {
        final User author = context.getAuthor();
        final SelfUser bot = context.getBot();

        final Embed embed = new Embed()
                .description(
                        StringUtil.convertList(
                                List.of(
                                        String.format("Hello, My name is %s at your service.", bot.getAsMention()),
                                        "If you need to know how to use me, please type !help for a list of commands."
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

        embed.addField("Version", String.format("`%s`", this.version));

        context.reply(embed.build(), true);
    }
}