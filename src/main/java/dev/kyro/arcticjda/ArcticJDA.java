package dev.kyro.arcticjda;

import dev.kyro.arcticjda.controllers.ADiscordCommand;
import dev.kyro.arcticjda.controllers.ADiscordManager;

public class ArcticJDA {

	public static ADiscordManager MANAGER;

	public static void init(String token, boolean blockThread) {

		MANAGER = new ADiscordManager(token, blockThread);
	}

	public static void registerCommand(ADiscordCommand command) {

		ADiscordManager.commands.add(command);
	}
}
