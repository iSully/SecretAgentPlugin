package com.iSully.TagGame;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;


public class TagGamePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TagGamePlugin.class);
		RuneLite.main(args);

	}

	private void connectToServer() {

	}
}