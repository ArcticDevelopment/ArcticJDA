package dev.kyro.arcticjda.controllers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ADiscordManager implements EventListener {

	public static JDABuilder BUILDER;
	public static JDA JDA;
	public static List<ADiscordCommand> commands = new ArrayList<>();
	private static final String prefix = ".";

	public ADiscordManager(String token, boolean blockThread) {

		System.out.println("Discord bot loading");
		BUILDER = JDABuilder.createDefault(token);
		try {
			BUILDER.addEventListeners(this);
			JDA = BUILDER.build();
			if(blockThread) JDA.awaitReady();
		} catch(LoginException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void onMessage(GuildMessageReceivedEvent event) {

		Message message = event.getMessage();
		if(!message.getContentRaw().startsWith(prefix)) return;

		String content = message.getContentRaw().replaceFirst(prefix, "");
		List<String> args = new ArrayList<>(Arrays.asList(content.split(" ")));
		String command = args.remove(0).toLowerCase();

		for(ADiscordCommand discordCommand : commands) {

			if(!discordCommand.command.equals(command) && !discordCommand.aliases.contains(command)) continue;

			discordCommand.execute(event, args);
			return;
		}
	}

	@Override
	public void onEvent(@NotNull GenericEvent event) {

		if (event instanceof ReadyEvent)
			System.out.println("Discord bot enabled");

		if(event instanceof GuildMessageReceivedEvent)
			onMessage((GuildMessageReceivedEvent) event);
	}
}
