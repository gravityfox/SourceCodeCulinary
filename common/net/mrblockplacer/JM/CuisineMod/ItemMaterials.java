package net.mrblockplacer.JM.CuisineMod;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemMaterials extends Item {

	public ItemMaterials(int par1, int par2, String par3) {
		super(par1 - 256);
		iconIndex = par2;
		setTabToDisplayOn(CreativeTabs.tabMaterials);
		setItemName(par3);
	}

	public String getTextureFile() {
		return CommonProxy.ITEMS;

	}

}
