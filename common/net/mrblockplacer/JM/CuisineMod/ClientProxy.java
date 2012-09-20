package net.mrblockplacer.JM.CuisineMod;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(ITEMS);
		MinecraftForgeClient.preloadTexture(BLOCKS);
	}

}