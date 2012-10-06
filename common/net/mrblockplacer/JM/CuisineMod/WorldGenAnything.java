package net.mrblockplacer.JM.CuisineMod;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.BiomeGenForest;

import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenAnything implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		for (int i = 0; i < 16; i++) {
			boolean inair = true;
			int spawnX = chunkX * 16 + random.nextInt(16);
			int spawnZ = chunkZ * 16 + random.nextInt(16);
			int curY = 255;

			while (inair) {

				if (world.getBlockId(spawnX, curY - 1, spawnZ) != 0) {
					inair = false;
					if (world.getBlockId(spawnX, curY - 1, spawnZ) == Block.sand.blockID) {
						world.setBlock(spawnX, curY, spawnZ, CuisineMod.saltslab.blockID);
					}
				} else {
					curY = curY - 1;
				}

			}

		}

		
	}

	private boolean canPlaceSaltSlabAt(World par1World, int par2, int par3, int par4) {
		int var5 = par1World.getBlockId(par2, par3 - 1, par4);
		Block block = Block.blocksList[var5];
		if (par1World.getBlockId(par2, par3 - 1, par4) == Block.sand.blockID) {
			if (par1World.getBlockId(par2 + 1, par3, par4) == Block.waterStill.blockID || par1World.getBlockId(par2 + 1, par3, par4) == Block.waterMoving.blockID) {
				return false;
			} else if (par1World.getBlockId(par2 - 1, par3, par4) == Block.waterStill.blockID || par1World.getBlockId(par2 - 1, par3, par4) == Block.waterMoving.blockID) {
				return false;
			} else if (par1World.getBlockId(par2, par3, par4 + 1) == Block.waterStill.blockID || par1World.getBlockId(par2, par3, par4 + 1) == Block.waterMoving.blockID) {
				return false;
			} else if (par1World.getBlockId(par2, par3, par4 - 1) == Block.waterStill.blockID || par1World.getBlockId(par2, par3, par4 + 1) == Block.waterMoving.blockID) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}


}
