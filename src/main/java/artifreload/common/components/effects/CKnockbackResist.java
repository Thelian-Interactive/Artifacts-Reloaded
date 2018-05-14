package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import artifreload.common.components.BaseComponent;


public class CKnockbackResist extends BaseComponent {


private static double knockbackAmount = 1.0;

@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor)
		return "onArmorTickUpdate";//onUpdate?
	else
		return "";
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Knockback Resistance"));
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Knockback Resistance") + " " + StatCollector.translateToLocal("tool." + trigger));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "Stable";
			break;
		case 1:
			str = "Heavy";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {

	return "of Stability";
}

@Override
public int getTextureBitflags() {
	return Flags.ARMOR | Flags.BOOTS | Flags.LEGGINGS;
}

@Override
public int getNegTextureBitflags() {
	return ~(Flags.ARMOR | Flags.BOOTS | Flags.LEGGINGS | Flags.CHESTPLATE | Flags.HELM);
}
}

