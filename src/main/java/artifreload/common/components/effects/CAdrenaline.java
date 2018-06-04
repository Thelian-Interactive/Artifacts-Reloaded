package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import net.minecraftforge.event.entity.living.LivingHurtEvent;

import artifreload.common.components.BaseComponent;


public class CAdrenaline extends BaseComponent {


@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor)
		return "onTakeDamage";
	return "";
}

@Override
public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack, boolean worn) {
	int delay =  ;
	if(!world.isRemote && delay > 0) {
		if(delay == 198) {
			//System.out.println("Crashing!");
			player.addPotionEffect(new PotionEffect(, 200, 1));
			player.addPotionEffect(new PotionEffect(17, 200, 0));
		}
	}
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add("Activates several potion effects after taking damage");
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Activates several potion effects"));
	par3List.add("   " + StatCollector.translateToLocal("tool."+trigger));
}

@Override
public void onTakeDamage(ItemStack itemStack, LivingHurtEvent event, boolean isWornArmor) {
	//System.out.println("Player has been damaged!");
	if(isWornArmor && !event.isCanceled()) {
		if(itemStack.stackTagCompound.getInteger("adrenDelay_armor") <= 0 && event.entity instanceof EntityPlayer) {
			//System.out.println("Attempting to apply potion effects to player.");
			EntityPlayer p = (EntityPlayer)event.entity;
			//if(p.getHealth() <= 4) {
			//System.out.println("Applying Potion Effects.");
			itemStack.stackTagCompound.setInteger("adrenDelay_armor", 300);

			p.addPotionEffect(new PotionEffect(1, 100, 1));
			p.addPotionEffect(new PotionEffect(5, 100, 1));
			p.addPotionEffect(new PotionEffect(11, 100, 2));

			//}
		}
	}
}

@Override
public String getPreAdj(Random rand) {
	return "Brawling";
}

@Override
public String getPostAdj(Random rand) {
	return "of Rage";
}

@Override
public int getTextureBitflags() {
	return Flags.CHESTPLATE;//1024
}

@Override
public int getNegTextureBitflags() {

	return ~(Flags.CHESTPLATE | Flags.LEGGINGS);//2815;//6911;
}
}

