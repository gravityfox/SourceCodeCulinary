package net.mrblockplacer.JM.CuisineMod;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemSalt extends Item {

	public ItemSalt(int par1) {
		super(par1-256);
		iconIndex = 0;
		setTabToDisplayOn(CreativeTabs.tabMaterials);
		setItemName("salt");
	}
	public String getTextureFile(){
		return CommonProxy.ITEMS;
		
	}

}
