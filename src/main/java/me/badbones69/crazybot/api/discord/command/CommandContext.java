package me.badbones69.crazybot.api.discord.command;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class CommandContext {

    private final TextChannel channel;
    private final String command;
    private final String[] args;

    public CommandContext(final String command, final TextChannel channel, final String[] args) {
        this.command = command;
        this.channel = channel;
        this.args = args;
    }

    public final TextChannel getTextChannel() {
        return this.channel;
    }

    public final String getCommand() {
        return this.command.replaceFirst("!", "");
    }

    public final String[] getArgs() {
        return this.args;
    }
}