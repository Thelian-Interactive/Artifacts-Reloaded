package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import artifreload.common.components.BaseComponent;


public class CCashout extends BaseComponent {

public String getRandomTrigger(Random rand, boolean isArmor) {
	return "onDropped";
}

@Override
public ItemStack attached(ItemStack i, Random rand, int[] eff) {
		/*if(eff.length == 1) {
			i.stackSize = 10;
		}*/
	int bonus = eff.length*5;
	if(1 == eff.length) {
		bonus = 0;
		i.stackSize = 10;
	}
	i.stackTagCompound.setInteger("cashBonus", bonus);
	i.stackTagCompound.setInteger("droppedDelay", 60);
	return i;
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Converts to emeralds") + " " + StatCollector.translateToLocal("tool."+trigger));
	if(trigger == "when dropped.") {
		par3List.add(EnumChatFormatting.YELLOW + "  2 " + StatCollector.translateToLocal("time.second") + " " + StatCollector.translateToLocal("time.fuse"));
	}
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Converts to emeralds"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "Valuable";
			break;
		case 1:
			str = "Emerald Studded";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(3)) {
		case 0:
			str = "of Emeralds";
			break;
		case 1:
			str = "of Money";
			break;
		case 2:
			str = "of Wealth";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.AMULET | Flags.FIGURINE | Flags.RING | Flags.TRINKET;
}

@Override
public int getNegTextureBitflags() {
	return Flags.ARMOR;
}

@Override
public boolean onEntityItemUpdate(EntityItem entityItem, String type) {
	if(type == "onDropped") {
		ItemStack item = entityItem.getEntityItem();
		int n = 1;
		int m = item.stackTagCompound.getInteger("material");
		n += m*m;
		if(item.isItemEnchanted()) {
			n += 2*(m+1);
		}
		n += item.stackTagCompound.getInteger("cashBonus");
		item = new ItemStack(Items.emerald, n);
		entityItem.setEntityItemStack(item);
	}
	return false;
}
}

