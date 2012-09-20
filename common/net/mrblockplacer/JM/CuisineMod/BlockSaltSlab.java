package net.mrblockplacer.JM.CuisineMod;

import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockSaltSlab extends Block {

	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS;
	}

	public BlockSaltSlab(int par1) {
		super(par1, Material.sand);
		setHardness(0.1F);
		setResistance(0.5F);
		blockIndexInTexture = 0;
		setCreativeTab(CreativeTabs.tabDeco);
		setBlockName("saltslab");
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setStepSound(soundSandFootstep);

	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		int var5 = par1World.getBlockMetadata(par2, par3, par4) & 7;
		return var5 >= 3 ? AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double) par2 + this.minX, (double) par3 + this.minY, (double) par4 + this.minZ, (double) par2 + this.maxX, (double) ((float) par3 + 0.5F), (double) par4 + this.maxZ) : null;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return false;
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 7;
		float var6 = (float) (2 * (1 + var5)) / 16.0F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, var6, 1.0F);
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		int var5 = par1World.getBlockId(par2, par3 - 1, par4);
		
		if (var5 == 0) {
			return false;
		}
		Block block = Block.blocksList[var5];
		return Block.sand.blockID == var5 || block.isOpaqueCube() == true;
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor blockID
	 */
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		if (!this.canBlockStay(par1World, par2, par3, par4)) {
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockWithNotify(par2, par3, par4, 0);

		}
	}

	/**
	 * Checks if this snow block can stay at this location.
	 */

	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		return this.canPlaceBlockAt(par1World, par2, par3, par4);

	}

	/**
	 * checks to see if you can place this block can be placed on that side of a
	 * block: BlockLever overrides
	 */

	public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5) {
		return canPlaceBlockAt(par1World, par2, par3, par4);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int meta, Random par2Random, int par3) {
		return CuisineMod.salt.shiftedIndex;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(int meta, int fortune, Random par1Random) {
		Random rnd = new Random();
		fortune = fortune+1;
		int i = rnd.nextInt(3) + 1;
		int fortrand = par1Random.nextInt(fortune);
		fortrand = fortrand * (rnd.nextInt(3)+1);
		i = i + fortrand;
		System.out.println("fortune: " + (fortune - 1));
		System.out.println(i);

		return i;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return par5 == 1 ? true : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}
	
}
