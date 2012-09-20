package net.mrblockplacer.JM.CuisineMod;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	public static String ITEMS = "/JMMods/Cuisine/JMItems.png";
	public static String BLOCKS = "/JMMods/Cuisine/JMBlocks.png";

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	public void registerRenderers() {
	}

}
