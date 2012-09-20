package net.mrblockplacer.JM.CuisineMod;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

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

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Return an item rarity from EnumRarity
	 */
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.common;
	}

}
