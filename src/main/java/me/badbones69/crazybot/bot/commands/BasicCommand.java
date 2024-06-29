package me.badbones69.crazybot.bot.commands;

import me.badbones69.crazybot.api.discord.command.Command;
import me.badbones69.crazybot.api.discord.command.CommandContext;

public class BasicCommand extends Command {

    public BasicCommand() {
        super(true, "basic");
    }

    @Override
    public void execute(CommandContext context) {
        context.getTextChannel().sendMessage("Beep!").queue();
    }
}