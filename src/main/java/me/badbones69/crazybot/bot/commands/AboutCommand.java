package me.badbones69.crazybot.bot.commands;

import me.badbones69.crazybot.api.discord.commands.CommandContext;
import me.badbones69.crazybot.api.discord.commands.CommandEngine;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;

public class AboutCommand extends CommandEngine {

    public AboutCommand(final String name, final String description, final Permission permission) {
        super(name, description, permission, true);
    }

    @Override
    protected void perform(final CommandContext context) {
        final JDA jda = context.getJDA();
    }
}