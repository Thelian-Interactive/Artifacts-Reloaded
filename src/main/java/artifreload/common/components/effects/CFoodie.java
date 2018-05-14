package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;


public class CFoodie extends BaseComponent {


public ComponentFoodie() {
}

@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) {
		return "onArmorTickUpdate";
	}
	String str = "";
	switch(rand.nextInt(3)) {
		case 0:
			str = "onItemRightClick";
			break;
		case 1:
			str = "hitEntity";
			break;
	}
	return str;
}

@Override
public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
	UtilsForComponents.sendPotionPacket(23, 2, 0, player);
//		UtilsForComponents.sendPotionPacket(9, 200, 0, player);
	UtilsForComponents.sendItemDamagePacket(player, player.inventory.currentItem, 1);
	itemStack.stackTagCompound.setInteger("onItemRightClickDelay", 20);
	return itemStack;
}

@Override
public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLivingHit, EntityLivingBase entityLivingPlayer) {
	if(!entityLivingPlayer.worldObj.isRemote && entityLivingPlayer instanceof EntityPlayer) {
		entityLivingPlayer.addPotionEffect(new PotionEffect(23, 2, 0));
//			entityLivingPlayer.addPotionEffect(new PotionEffect(9, 200, 0));
		itemStack.damageItem(1, entityLivingPlayer);
	}
	return false;
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	String amount = "effect.fills hunger when half empty";
	if(trigger == "when inflicting damage." || trigger == "when used.") {
		amount = "effect.fills 1 drumstick";
	}
	par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("effect.Food Saturation"));
	par3List.add("  " + EnumChatFormatting.AQUA + StatCollector.translateToLocal("tool."+trigger) + " (" + StatCollector.translateToLocal(amount) + ")");
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("effect.Food Saturation"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(3)) {
		case 0:
			str = "Well Fed";
			break;
		case 1:
			str = "Shanked";
			break;
		case 2:
			str = "Porky";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Saturation";
			break;
		case 1:
			str = "of Noms";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.AMULET | Flags.FIGURINE | Flags.STAFF | Flags.RING | Flags.TRINKET | Flags.ARMOR | Flags.CHESTPLATE | Flags.HELM;
}

@Override
public int getNegTextureBitflags() {
	return Flags.DAGGER | Flags.WAND | Flags.BOOTS | Flags.LEGGINGS | Flags.BELT;
}

@Override
public void onHeld(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
	//onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
	if(!par2World.isRemote) {
		if(par3Entity instanceof EntityPlayer) {
			EntityPlayer ent = (EntityPlayer) par3Entity;
			if(ent.getFoodStats().getFoodLevel() < 11) {
				ent.addPotionEffect(new PotionEffect(23, 10, 0));
//					ent.addPotionEffect(new PotionEffect(9, 200, 0));
				par1ItemStack.damageItem(1, ent);
			}
		}
	}
}

@Override
public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack, boolean worn) {
	if(worn) {
		onHeld(itemStack, world, player, 0, true);
	}
}
}

