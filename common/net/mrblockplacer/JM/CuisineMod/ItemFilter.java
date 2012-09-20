package net.mrblockplacer.JM.CuisineMod;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemFilter extends Item{

	protected ItemFilter(int par1) {
		super(par1 - 256);
		iconIndex = 2;
		setTabToDisplayOn(CreativeTabs.tabTools);
		setItemName("filter");
		maxStackSize = 1;
	}
	public String getTextureFile(){
		return CommonProxy.ITEMS;
		
	}
}
