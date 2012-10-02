package net.mrblockplacer.JM.CuisineMod;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.BiomeGenForest;

import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenSalt implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);
		// WorldGenGoldTree tree = new WorldGenGoldTree();
		if (biome instanceof BiomeGenForest) {
			for (int x1 = 0; x1 < 10; x1++) {
				int Xcoord = chunkX * 16 + random.nextInt(16);
				int Zcoord = chunkZ * 16 + random.nextInt(16);
				int height = world.getHeightValue(Xcoord, Zcoord);
				int x = Xcoord;
				int z = Zcoord;
				/*
				 * world.setBlock(x, i, z, CuisineMod.goldLog.blockID);
				 * world.setBlock(x, i + 1, z, CuisineMod.goldLog.blockID);
				 * world.setBlock(x, i + 2, z, CuisineMod.goldLog.blockID);
				 * world.setBlock(x, i + 3, z, CuisineMod.goldLog.blockID);
				 * world.setBlock(x, i + 4, z, CuisineMod.goldLog.blockID);
				 * 
				 * world.setBlock(x + 1, i + 3, z, CuisineMod.goldLeaf.blockID);
				 * world.setBlock(x - 1, i + 3, z, CuisineMod.goldLeaf.blockID);
				 * 
				 * world.setBlock(x + 1, i + 3, z + 1,
				 * CuisineMod.goldLeaf.blockID); world.setBlock(x + 1, i + 3, z
				 * - 1, CuisineMod.goldLeaf.blockID); world.setBlock(x + 1, i +
				 * 3, z + 2, CuisineMod.goldLeaf.blockID); world.setBlock(x + 1,
				 * i + 3, z - 2, CuisineMod.goldLeaf.blockID); world.setBlock(x
				 * + 1, i + 3, z + 3, CuisineMod.goldLeaf.blockID);
				 * world.setBlock(x + 1, i + 3, z - 3,
				 * CuisineMod.goldLeaf.blockID);
				 * 
				 * world.setBlock(x - 1, i + 3, z + 1,
				 * CuisineMod.goldLeaf.blockID); world.setBlock(x - 1, i + 3, z
				 * - 1, CuisineMod.goldLeaf.blockID); world.setBlock(x - 1, i +
				 * 3, z + 2, CuisineMod.goldLeaf.blockID); world.setBlock(x - 1,
				 * i + 3, z - 2, CuisineMod.goldLeaf.blockID); world.setBlock(x
				 * - 1, i + 3, z + 3, CuisineMod.goldLeaf.blockID);
				 * world.setBlock(x - 1, i + 3, z - 3,
				 * CuisineMod.goldLeaf.blockID);
				 * 
				 * world.setBlock(x + 1, i + 4, z, CuisineMod.goldLeaf.blockID);
				 * world.setBlock(x - 1, i + 4, z, CuisineMod.goldLeaf.blockID);
				 * world.setBlock(x + 1, i + 4, z + 1,
				 * CuisineMod.goldLeaf.blockID); world.setBlock(x - 1, i + 4, z
				 * - 1, CuisineMod.goldLeaf.blockID); world.setBlock(x, i + 4, z
				 * + 1, CuisineMod.goldLeaf.blockID); world.setBlock(x, i + 4, z
				 * - 1, CuisineMod.goldLeaf.blockID);
				 */
				int l = random.nextInt(1) + 4;

				for (int k1 = (height - 3) + x; k1 <= height + l; k1++) {
					int j2 = k1 - (height + l);
					int i3 = 1 - j2 / 2;
					for (int k3 = x - i3; k3 <= x + i3; k3++) {
						int l3 = k3 - x;
						for (int i4 = z - i3; i4 <= z + i3; i4++) {
							int j4 = i4 - z;
							if ((Math.abs(l3) != i3 || Math.abs(j4) != i3 || random.nextInt(2) != 0 && j2 != 0) && !Block.opaqueCubeLookup[world.getBlockId(k3, k1, i4)]) {
								world.setBlock(x, height, z, CuisineMod.goldLeaf.blockID); // Leaf////
							}
						}

					}

				}

				for (int l1 = 0; l1 < l; l1++) {
					int k2 = world.getBlockId(x, height + l1, z);
					if (k2 == 0 || k2 == CuisineMod.goldLeaf.blockID) // //Leaf/////
					{
						world.setBlock(x, height + l1, z, CuisineMod.goldLog.blockID); // ////Log////
					}
				}

				// tree.generate(world, random, Xcoord, i, Zcoord);
			}
		}

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

		/*
		 * BiomeGenBase biome =
		 * world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);
		 * WorldGenGoldTree tree = new WorldGenGoldTree(); if (biome instanceof
		 * BiomeGenForest)
		 * 
		 * { for (int x = 0; x < 1; x++) { int Xcoord = chunkX +
		 * random.nextInt(16); int Zcoord = chunkZ + random.nextInt(16); int i =
		 * world.getHeightValue(Xcoord, Zcoord); tree.generate(world, random,
		 * Xcoord, i, Zcoord); } }
		 */
		/*
		 * BiomeGenBase b = world.getBiomeGenForCoords(chunkX, chunkZ);
		 * if(b.biomeName==BiomeGenBase.desert.biomeName) { for(int l = 0; l <
		 * 6; l++) { int i1 = chunkX*16 + random.nextInt(16); int j1 =
		 * random.nextInt(20)+40; int k1 = chunkZ*16 + random.nextInt(16); (new
		 * WorldGenMinable(CuisineMod.saltblock.blockID, 3)).generate(world,
		 * random, i1, j1, k1); }
		 * 
		 * }
		 */

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
