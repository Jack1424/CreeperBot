package io.github.jack1424.creeperbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class CreeperBot {
	public static void main(String[] args) {
		final String token = args[0];
		final DiscordClient client = DiscordClient.create(token);
		final GatewayDiscordClient gateway = client.login().block();

		System.out.println("READY!");
		System.out.println("Currently in: " + gateway.getGuilds().count().block() + " servers");

		gateway.on(MessageCreateEvent.class).subscribe(event -> {
			final Message message = event.getMessage();
			if (message.getContent().contains("creeper"))
				message.getChannel().block().createMessage("Aw man").block();
		});

		gateway.onDisconnect().block();
	}
}