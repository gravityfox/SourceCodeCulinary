package net.mrblockplacer.JM.CuisineMod;

import java.util.Random;

import javax.jws.Oneway;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockSaltCollector extends BlockContainer {

	public int SurroundingWater;

	public BlockSaltCollector(int par1) {
		super(par1, Material.wood);
		this.setCreativeTab(CreativeTabs.tabDeco);
		setBlockName("saltcollectorblock");
		setStepSound(soundWoodFootstep);
		blockIndexInTexture = 1;

	}

	public String getTextureFile() {
		return CommonProxy.BLOCKS;
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4) {

		onNeighborBlockChange(par1World, par2, par3, par4, 0);
		TileEntity tileEntity = par1World.getBlockTileEntity(par2, par3, par4);
		TileEntitySaltCollector tileEntitySC = (TileEntitySaltCollector) tileEntity;
		tileEntitySC.startTimeLeft();

	}

	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {

		int countwater = 0;
		TileEntity tileEntity = par1World.getBlockTileEntity(par2, par3, par4);
		TileEntitySaltCollector tileEntitySC = (TileEntitySaltCollector) tileEntity;

		BiomeGenBase b = par1World.getBiomeGenForCoords(par2, par4);
		if (b.biomeName == BiomeGenBase.ocean.biomeName || b.biomeName == BiomeGenBase.river.biomeName) {
			if (par1World.getBlockId(par2, par3, par4 + 1) == Block.waterMoving.blockID || par1World.getBlockId(par2, par3, par4 + 1) == Block.waterStill.blockID) {
				++countwater;
			}
			if (par1World.getBlockId(par2 + 1, par3, par4) == Block.waterMoving.blockID || par1World.getBlockId(par2 + 1, par3, par4) == Block.waterStill.blockID) {
				++countwater;
			}
			if (par1World.getBlockId(par2 - 1, par3, par4) == Block.waterMoving.blockID || par1World.getBlockId(par2 - 1, par3, par4) == Block.waterStill.blockID) {
				++countwater;
			}
			if (par1World.getBlockId(par2, par3 + 1, par4) == Block.waterMoving.blockID || par1World.getBlockId(par2, par3 + 1, par4) == Block.waterStill.blockID) {
				++countwater;
			}
			if (par1World.getBlockId(par2, par3 - 1, par4) == Block.waterMoving.blockID || par1World.getBlockId(par2, par3 - 1, par4) == Block.waterStill.blockID) {
				++countwater;
			}
			if (par1World.getBlockId(par2, par3, par4 - 1) == Block.waterMoving.blockID || par1World.getBlockId(par2, par3, par4 - 1) == Block.waterStill.blockID) {
				++countwater;
			}
		}

		if (b.biomeName == BiomeGenBase.ocean.biomeName) {

			tileEntitySC.setSurroundingWater(countwater * 2);
		} else if (b.biomeName == BiomeGenBase.river.biomeName) {
			tileEntitySC.setSurroundingWater(countwater);
		}
		
	}

	Random random;

	@SideOnly(Side.CLIENT)
	@Override
	public boolean onBlockActivated(World world, int x, int height, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		/*
		 * random = new Random(); int l = random.nextInt(1) + 4;
		 * 
		 * for (int k1 = (height - 3) + x; k1 <= height + l; k1++) { int j2 = k1
		 * - (height + l); int i3 = 1 - j2 / 2; for (int k3 = x - i3; k3 <= x +
		 * i3; k3++) { int l3 = k3 - x; for (int i4 = z - i3; i4 <= z + i3;
		 * i4++) { int j4 = i4 - z; if ((Math.abs(l3) != i3 || Math.abs(j4) !=
		 * i3 || random.nextInt(2) != 0 && j2 != 0) &&
		 * !Block.opaqueCubeLookup[world.getBlockId(k3, k1, i4)]) {
		 * world.setBlock(x, height, z, CuisineMod.goldLeaf.blockID); //
		 * Leaf//// } }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * for (int l1 = 0; l1 < l; l1++) { int k2 = world.getBlockId(x, height
		 * + l1, z); if (k2 == 0 || k2 == CuisineMod.goldLeaf.blockID) //
		 * //Leaf///// { world.setBlock(x, height + l1, z,
		 * CuisineMod.goldLog.blockID); // ////Log//// } }
		 */

		TileEntity tileEntity = world.getBlockTileEntity(x, height, z);
		if (tileEntity == null || par5EntityPlayer.isSneaking()) {
			return false;
		} // opens gui, to be implemented later
		par5EntityPlayer.openGui(CuisineMod.instance, 0, world, x, height, z);

		return true;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		dropItems(par1World, par2, par3, par4);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound()) {
					entityItem.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public TileEntity createNewTileEntity(World par1World) {
		try {
			return new TileEntitySaltCollector();
		} catch (Exception var3) {
			throw new RuntimeException(var3);
		}
	}

}
