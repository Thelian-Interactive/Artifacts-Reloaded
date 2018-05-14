package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import artifreload.common.components.BaseComponent;


public class CHealth extends BaseComponent {

//private UUID healthID = UUID.fromString("B3766B59-9546-4402-FC1F-2EE2A206D831");

@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) {
		return "onArmorTickUpdate";
	}
	return "onUpdate";
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	String heart = StatCollector.translateToLocal("effect.Hearts");
	if(heart.equals("{H}"))
		heart = "" + EnumChatFormatting.RED + ((char) 0x2665);//&hearts;
	par3List.add(StatCollector.translateToLocal("effect.Health Boost") + " " + StatCollector.translateToLocal("tool."+trigger) + " " + EnumChatFormatting.BLUE + "+2.5 " + heart);
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Health Boost"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(3)) {
		case 0:
			str = "Hardy";
			break;
		case 1:
			str = "Bulky";
			break;
		case 2:
			str = "Sturdy";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Toughness";
			break;
		case 1:
			str = "of Health";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.AMULET | Flags.FIGURINE | Flags.RING | Flags.TRINKET | Flags.WAND | Flags.ARMOR | Flags.CHESTPLATE | Flags.LEGGINGS | Flags.BELT;
}

@Override
public int getNegTextureBitflags() {
	return Flags.DAGGER | Flags.SWORD | Flags.HELM;
}
}

