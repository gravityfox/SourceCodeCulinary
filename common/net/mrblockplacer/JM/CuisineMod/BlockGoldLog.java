package net.mrblockplacer.JM.CuisineMod;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockPistonBase;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockGoldLog extends Block {

	protected BlockGoldLog(int par1) {
		super(par1, Material.wood);
		setLightValue(0.49f);
		setHardness(2.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType() {
		return 31;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3) {
		return CuisineMod.goldLog.blockID;
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		if (par1 == 0)
			return 6;
		if (par1 == 1)
			return 6;
		if (par1 == 2)
			return 5;
		if (par1 == 3)
			return 5;
		if (par1 == 4)
			return 5;
		if (par1 == 5)
			return 5;
		else
			return 0;
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isWood(World world, int x, int y, int z) {
		return true;
	}

}
