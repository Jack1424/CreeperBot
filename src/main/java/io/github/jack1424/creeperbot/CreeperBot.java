package io.github.jack1424.creeperbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;

import java.util.Objects;

public class CreeperBot {
	public static void main(String[] args) {
		final GatewayDiscordClient gateway = DiscordClient
				.create(args[0])
				.gateway()
				.setInitialPresence(s -> ClientPresence.online(ClientActivity.watching("you")))
				.login()
				.block();

		System.out.println("READY!");

		Objects.requireNonNull(gateway).on(MessageCreateEvent.class).subscribe(event -> {
			if (event.getMessage().getContent().toLowerCase().contains("creeper"))
				Objects.requireNonNull(event.getMessage().getChannel().block()).createMessage("Aw man").block();
		});

		gateway.onDisconnect().block();
	}
}
