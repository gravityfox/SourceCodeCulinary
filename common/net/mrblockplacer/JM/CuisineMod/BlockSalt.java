package net.mrblockplacer.JM.CuisineMod;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockSalt extends Block {

	public String getTextureFile() {
		return CommonProxy.BLOCKS;
	}

	public BlockSalt(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		setHardness(0.5F);
		setResistance(7.5F);
		setBlockName("saltblock");
		setStepSound(soundStoneFootstep);
		blockIndexInTexture = 0;

	}
	
		
	

}
