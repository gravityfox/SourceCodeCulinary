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

	public boolean generate2(World par1World, Random par2Random, int par3, int par4, int par5) {
		int var6 = par2Random.nextInt(4) + 6;
		int var7 = 1 + par2Random.nextInt(2);
		int var8 = var6 - var7;
		int var9 = 2 + par2Random.nextInt(2);
		boolean var10 = true;

		if (par4 >= 1 && par4 + var6 + 1 <= 256) {
			int var11;
			int var13;
			int var15;
			int var21;

			for (var11 = par4; var11 <= par4 + 1 + var6 && var10; ++var11) {
				boolean var12 = true;

				if (var11 - par4 < var7) {
					var21 = 0;
				} else {
					var21 = var9;
				}

				for (var13 = par3 - var21; var13 <= par3 + var21 && var10; ++var13) {
					for (int var14 = par5 - var21; var14 <= par5 + var21 && var10; ++var14) {
						if (var11 >= 0 && var11 < 256) {
							var15 = par1World.getBlockId(var13, var11, var14);

							Block block = Block.blocksList[var15];

							if (var15 != 0 && block != null && !block.isLeaves(par1World, var13, var11, var14)) {
								var10 = false;
							}
						} else {
							var10 = false;
						}
					}
				}
			}

			if (!var10) {
				return false;
			} else {
				var11 = par1World.getBlockId(par3, par4 - 1, par5);

				if ((var11 == Block.grass.blockID || var11 == Block.dirt.blockID) && par4 < 256 - var6 - 1) {
					// this.setBlock(par1World, par3, par4 - 1, par5,
					// Block.dirt.blockID);
					var21 = par2Random.nextInt(2);
					var13 = 1;
					byte var22 = 0;
					int var17;
					int var16;

					for (var15 = 0; var15 <= var8; ++var15) {
						var16 = par4 + var6 - var15;

						for (var17 = par3 - var21; var17 <= par3 + var21; ++var17) {
							int var18 = var17 - par3;

							for (int var19 = par5 - var21; var19 <= par5 + var21; ++var19) {
								int var20 = var19 - par5;

								Block block = Block.blocksList[par1World.getBlockId(var17, var16, var19)];

								if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && (block == null || block.canBeReplacedByLeaves(par1World, var17, var16, var19))) {
									par1World.setBlockAndMetadata(var17, var16, var19, CuisineMod.goldLeaf.blockID, 1);
								}
							}
						}

						if (var21 >= var13) {
							var21 = var22;
							var22 = 1;
							++var13;

							if (var13 > var9) {
								var13 = var9;
							}
						} else {
							++var21;
						}
					}

					var15 = par2Random.nextInt(3);

					for (var16 = 0; var16 < var6 - var15; ++var16) {
						var17 = par1World.getBlockId(par3, par4 + var16, par5);

						Block block = Block.blocksList[var17];

						if (var17 == 0 || block == null || block.isLeaves(par1World, par3, par4 + var16, par5)) {
							par1World.setBlockAndMetadata(par3, par4 + var16, par5, CuisineMod.goldLog.blockID, 1);
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

}
