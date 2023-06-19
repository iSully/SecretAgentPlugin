package com.iSully.TagGame;

import com.google.gson.Gson;
import com.google.inject.Provides;

import javax.inject.Inject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.net.URISyntaxException;

@Slf4j
@PluginDescriptor(
	name = "Tag Game"
)
public class TagGamePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private TagGameConfig config;

	@Inject
	private ClientToolbar clientToolbar;

	private TagGamePluginPanel panel;
	private NavigationButton navButton;

	public Socket socket;

	public ConnectionManager connectionManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Tag Game Started!");
		panel = new TagGamePluginPanel(this, this.client);
		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "tag_icon.png");
		navButton = NavigationButton.builder()
				.tooltip("Tag Game")
				.priority(10)
				.icon(icon)
				.panel(panel)
				.build();
		clientToolbar.addNavigation(navButton);

		this.connectionManager = new ConnectionManager(this.client, new Gson());

//		updateDropdown();
//		overlayManager.add(propHuntOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		clientToolbar.removeNavigation(navButton);
		log.info("Tag Game Stopped!");
	}


	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN
//		&& config.enablePartyTag()
    	&& !(panel.partyCode == null || panel.partyCode.isBlank()))
		{
			this.connectToServer();
			String username = client.getLocalPlayer().getName();
			System.out.println(username);
		}
	}

	@Provides
	TagGameConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TagGameConfig.class);
	}

	public void createGame() {

	}

	private void connectToServer() {
		try {
			this.socket = IO.socket("http://localhost:4001");

			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
				@Override
				public void call(Object... args) {
					socket.emit("chat message", "Hello World!");
				}
			}).on("chat message", new Emitter.Listener() {
				@Override
				public void call(Object... args) {
					System.out.println("message: " + args[0]);
				}
			}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
				@Override
				public void call(Object... args) {}
			});

			socket.connect();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

}
