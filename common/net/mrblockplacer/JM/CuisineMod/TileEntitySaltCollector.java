package net.mrblockplacer.JM.CuisineMod;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;

public class TileEntitySaltCollector extends TileEntity implements IInventory {

	private ItemStack[] inv;

	public static int TotalTime = 640 * 39;

	public int TimeLeft;

	public int SurroundingWater;
	
	public boolean Initialized= false;
	
	public TileEntitySaltCollector() {
		inv = new ItemStack[1];
	}

	public void setSurroundingWater(int par1) {
		SurroundingWater = par1;
	}

	public void startTimeLeft() {
		TimeLeft = TotalTime;
	}

	@Override
	public int getSizeInventory() {

		// TODO Auto-generated method stub
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	public void updateEntity() {
		if(!Initialized) {
			Block.blocksList[worldObj.getBlockId(xCoord, yCoord, zCoord)].onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord, 0);
			Initialized = true;
		}

		// if (!this.worldObj.isRemote) {

		if (inv[0] == null || (inv[0].stackSize != 64 && this.inv[0].isItemEqual(new ItemStack(CuisineMod.salt)))) {

			if (TimeLeft <= 0) {
				TimeLeft = TotalTime;
				if (this.inv[0] == null) {
					this.inv[0] = new ItemStack(CuisineMod.salt, 1);
				} else if (this.inv[0].isItemEqual(new ItemStack(CuisineMod.salt))) {
					setInventorySlotContents(0, new ItemStack(CuisineMod.salt, inv[0].stackSize + 1));
				}
				this.onInventoryChanged();

			} else {
				TimeLeft -= SurroundingWater;
			}

		}

		// }
	}

	@SideOnly(Side.CLIENT)
	public int getTimeLeft() {
		return TimeLeft;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		TimeLeft = tagCompound.getInteger("SaltTimeLeft");
		

		NBTTagList tagList = tagCompound.getTagList("SaltCollectorInventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("SaltCollectorSlot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("SaltTimeLeft", TimeLeft);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++) {
			ItemStack stack = inv[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("SaltCollectorSlot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("SaltCollectorInventory", itemList);
	}

}
