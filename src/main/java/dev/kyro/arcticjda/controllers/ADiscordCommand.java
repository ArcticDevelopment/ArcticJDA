package dev.kyro.arcticjda.controllers;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public abstract class ADiscordCommand {

    public String command;
    public List<String> aliases;

    public ADiscordCommand(String command, String... aliases) {
        this.command = command;
        this.aliases = Arrays.asList(aliases);
    }

    public abstract void execute(GuildMessageReceivedEvent event, List<String> args);
}
