package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.event.entity.living.LivingDeathEvent;

import artifreload.common.components.BaseComponent;


public class CResurect extends BaseComponent {


@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) {
		return "onDeath";
	}
	return "";
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	NBTTagCompound data = par1ItemStack.getTagCompound();
	int c = data.getInteger("resCooldown_armor");
	if(c < 1) {
		par3List.add(StatCollector.translateToLocal("effect.Restores health") + " " + StatCollector.translateToLocal("tool."+trigger));
		par3List.add("   (5 " + StatCollector.translateToLocal("time.minute") + " " + StatCollector.translateToLocal("time.cooldown") + ")");
	}
	else {
		String m = "";
		if(c >= 1200) {
			m = ((c+30)/1200) + " "+StatCollector.translateToLocal("time.minutes");
		}
		else {
			m = (c/20) + " "+StatCollector.translateToLocal("time.seconds");
		}
		par3List.add(StatCollector.translateToLocal("effect.Restores health") + " " + StatCollector.translateToLocal("tool."+trigger));
		par3List.add("   (" + StatCollector.translateToLocal("time.on") + " " + StatCollector.translateToLocal("time.cooldown") + ": " + m + ")");
	}
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add("Restores health when the player takes");
	par3List.add("leathal damage. (5 minute cooldown)");
}

@Override
public String getPreAdj(Random rand) {
	return "Regenerative";
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Regeneration";
			break;
		case 1:
			str = "of Lazarus";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.ARMOR | Flags.CHESTPLATE;
}

@Override
public int getNegTextureBitflags() {
	return ~(getTextureBitflags() | Flags.LEGGINGS | Flags.HELM);
}

@Override
public void onDeath(ItemStack itemStack, LivingDeathEvent event, boolean isWornArmor) {
	System.out.println("ABORTING DEATH");
	if(isWornArmor && !event.isCanceled()) {
		EntityPlayer player = (EntityPlayer)event.entity;
		NBTTagCompound data = itemStack.getTagCompound();
		//System.out.println("Cooldown: " + data.getInteger("resCooldown"));
		if(data.getInteger("resCooldown_armor") <= 0) {
			event.setCanceled(true);
			player.setHealth(20);
			data.setInteger("resCooldown_armor", 6000);
			itemStack.damageItem(5, player);
			return;
		}
	}
}
}

