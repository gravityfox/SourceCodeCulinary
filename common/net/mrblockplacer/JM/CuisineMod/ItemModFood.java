package net.mrblockplacer.JM.CuisineMod;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.World;

public class ItemModFood extends ItemFood {

	public final int duration;
	public ItemModFood(int id, int amount, float saturation, boolean wolf, int length, int index, String name) {
		super(id-256, amount, saturation, wolf);
		duration=length;
		iconIndex= index;
		setItemName(name);
		
		
	}
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return duration;
    }

	public String getTextureFile(){
		return CommonProxy.ITEMS;
		
	}
	
	
	
}
