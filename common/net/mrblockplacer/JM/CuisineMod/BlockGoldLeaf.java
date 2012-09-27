package net.mrblockplacer.JM.CuisineMod;

import java.util.Random;

import net.minecraft.src.BlockLeavesBase;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockGoldLeaf extends BlockLeavesBase {

	private int baseIndexInPNG;
	int adjacentTreeBlocks[];

	protected BlockGoldLeaf(int i, int j) {
		super(i, j, Material.leaves, false);
		baseIndexInPNG = j;
		setTickRandomly(true);
		setCreativeTab(CreativeTabs.tabDeco);
	}
	//hello

	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS;
	}

	/*
	 * public int getBlockColor() { double d = 0.5D; double d1 = 1.0D; // return
	 * ColorizerFoliage.getFoliageColor(d, d1); return 0; // Put this so your
	 * leaves don't change// }
	 */

	/*
	 * public int getRenderColor(int i) { if((i & 1) == 1) { return
	 * ColorizerFoliage.getFoliageColorPine(); } if((i & 2) == 2) { return
	 * ColorizerFoliage.getFoliageColorBirch(); } else { return
	 * ColorizerFoliage.getFoliageColorBasic(); } } *************Unnecessary
	 * Method
	 */

	/*
	 * public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int
	 * k) { int l = iblockaccess.getBlockMetadata(i, j, k); if((l & 1) == 1) {
	 * return ColorizerFoliage.getFoliageColorPine(); } if((l & 2) == 2) {
	 * return ColorizerFoliage.getFoliageColorBirch(); } else { return
	 * iblockaccess.getWorldChunkManager().getBiomeGenAt(i,
	 * k).func_40255_b(iblockaccess, i, j, k); } //**************Unnecessary
	 * Method, messes up your custom texture }
	 */

	/*
	 * public void onBlockRemoval(World world, int i, int j, int k) { int l = 1;
	 * int i1 = l + 1; if(world.checkChunksExist(i - i1, j - i1, k - i1, i + i1,
	 * j + i1, k + i1)) { for(int j1 = -l; j1 <= l; j1++) { for(int k1 = -l; k1
	 * <= l; k1++) { for(int l1 = -l; l1 <= l; l1++) { int i2 =
	 * world.getBlockId(i + j1, j + k1, k + l1); if(i2 ==
	 * mod_OreMod.FireSapling.blockID) ///////Leaf///////////// { int j2 =
	 * world.getBlockMetadata(i + j1, j + k1, k + l1); world.setBlockMetadata(i
	 * + j1, j + k1, k + l1, j2 | 8); } }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } }
	 */
	/*
	 * public void updateTick(World world, int i, int j, int k, Random random) {
	 * if(world.isRemote) { return; } int l = world.getBlockMetadata(i, j, k);
	 * if((l & 8) != 0 && (l & 4) == 0) { byte byte0 = 4; int i1 = byte0 + 1;
	 * byte byte1 = 32; int j1 = byte1 * byte1; int k1 = byte1 / 2;
	 * if(adjacentTreeBlocks == null) { adjacentTreeBlocks = new int[byte1 *
	 * byte1 * byte1]; } if(world.checkChunksExist(i - i1, j - i1, k - i1, i +
	 * i1, j + i1, k + i1)) { for(int l1 = -byte0; l1 <= byte0; l1++) { for(int
	 * k2 = -byte0; k2 <= byte0; k2++) { for(int i3 = -byte0; i3 <= byte0; i3++)
	 * { int k3 = world.getBlockId(i + l1, j + k2, k + i3); if(k3 ==
	 * mod_OreMod.FireLog.blockID) ///////Log////////////// {
	 * adjacentTreeBlocks[(l1 + k1) * j1 + (k2 + k1) * byte1 + (i3 + k1)] = 0;
	 * continue; } if(k3 == mod_OreMod.FireLeaf.blockID) ///////Leaf///////////
	 * { adjacentTreeBlocks[(l1 + k1) * j1 + (k2 + k1) * byte1 + (i3 + k1)] =
	 * -2; } else { adjacentTreeBlocks[(l1 + k1) * j1 + (k2 + k1) * byte1 + (i3
	 * + k1)] = -1; } }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * for(int i2 = 1; i2 <= 4; i2++) { for(int l2 = -byte0; l2 <= byte0; l2++)
	 * { for(int j3 = -byte0; j3 <= byte0; j3++) { for(int l3 = -byte0; l3 <=
	 * byte0; l3++) { if(adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1) * byte1 +
	 * (l3 + k1)] != i2 - 1) { continue; } if(adjacentTreeBlocks[((l2 + k1) - 1)
	 * * j1 + (j3 + k1) * byte1 + (l3 + k1)] == -2) { adjacentTreeBlocks[((l2 +
	 * k1) - 1) * j1 + (j3 + k1) * byte1 + (l3 + k1)] = i2; }
	 * if(adjacentTreeBlocks[(l2 + k1 + 1) * j1 + (j3 + k1) * byte1 + (l3 + k1)]
	 * == -2) { adjacentTreeBlocks[(l2 + k1 + 1) * j1 + (j3 + k1) * byte1 + (l3
	 * + k1)] = i2; } if(adjacentTreeBlocks[(l2 + k1) * j1 + ((j3 + k1) - 1) *
	 * byte1 + (l3 + k1)] == -2) { adjacentTreeBlocks[(l2 + k1) * j1 + ((j3 +
	 * k1) - 1) * byte1 + (l3 + k1)] = i2; } if(adjacentTreeBlocks[(l2 + k1) *
	 * j1 + (j3 + k1 + 1) * byte1 + (l3 + k1)] == -2) { adjacentTreeBlocks[(l2 +
	 * k1) * j1 + (j3 + k1 + 1) * byte1 + (l3 + k1)] = i2; }
	 * if(adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1) * byte1 + ((l3 + k1) -
	 * 1)] == -2) { adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1) * byte1 + ((l3
	 * + k1) - 1)] = i2; } if(adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1) *
	 * byte1 + (l3 + k1 + 1)] == -2) { adjacentTreeBlocks[(l2 + k1) * j1 + (j3 +
	 * k1) * byte1 + (l3 + k1 + 1)] = i2; } }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } int j2 = adjacentTreeBlocks[k1 * j1 + k1 * byte1 + k1]; if(j2 >= 0) {
	 * world.setBlockMetadata(i, j, k, l & -9); } else { removeLeaves(world, i,
	 * j, k); } } }
	 */

	private void removeLeaves(World world, int i, int j, int k) {
		// dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
		world.setBlockWithNotify(i, j, k, 0);
	}

	public int quantityDropped(Random random) {
		return random.nextInt(20) != 0 ? 0 : 1;
	}

	public int idDropped(int i, Random random, int j) {
		return 0;
	}

	// public void dropBlockAsItemWithChance(World world, int i, int j, int k,
	// int l, float f, int i1)

	/*
	 * protected int damageDropped(int i) { return i & 3; }
	 */
	public boolean isOpaqueCube() {
		return !graphicsLevel;
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		/*
		 * if ((j & 3) == 1) { return blockIndexInTexture; // get rid of the
		 * 80/////////// } else {
		 */
		return blockIndexInTexture;
		// }
	}

	public void setGraphicsLevel(boolean flag) {
		graphicsLevel = flag;
		blockIndexInTexture = baseIndexInPNG + (flag ? 0 : 1);
		// - Not needed, comment out
	}

	/*
	 * public void onEntityWalking(World world, int i, int j, int k, Entity
	 * entity) { super.onEntityWalking(world, i, j, k, entity); }
	 */

}
