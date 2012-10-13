package net.mrblockplacer.JM.CuisineMod;

import java.util.List;
import java.util.Random;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import java.util.Random;

import org.lwjgl.Sys;

public class EventListener {

	@ForgeSubscribe
	public void PlayerAttack(LivingDeathEvent event) {
		Random rand = new Random();

		Entity entity = event.entityLiving;
		DamageSource source = event.source;

		EntityLiving el = (EntityLiving) entity;
		
		if (entity.getClass() == EntitySheep.class && !entity.worldObj.isRemote) {
			int drop = rand.nextInt(3);

			System.out.println(source.getDamageType());
			if (source.getDamageType() == "player" && source.getSourceOfDamage().getClass() == EntityPlayerMP.class) {
				EntityPlayerMP player = (EntityPlayerMP) source.getSourceOfDamage();
				drop = drop + (rand.nextInt(1 + (EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, player.getCurrentEquippedItem()) * 1)));
			}
			int meattype;
			if (source.getDamageType() == "onFire") {
				meattype = CuisineMod.lambmeatcooked.shiftedIndex;
			} else {
				meattype = CuisineMod.lambmeatraw.shiftedIndex;
			}

			for (int i = 0; i < drop; i++) {
				World w = entity.worldObj;
				EntityItem entityItem = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(meattype, 1, 0));
				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * 0.05 + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				w.spawnEntityInWorld(entityItem);
			}

		}
	}

}
