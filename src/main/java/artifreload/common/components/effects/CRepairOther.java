package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;
import io.netty.buffer.Unpooled;


public class CRepairOther extends BaseComponent {


@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	String str = "";
	if(isArmor) {
		str = "onArmorTickUpdate";
	}
	else {
		switch(rand.nextInt(2)) {
			case 0:
				str = "onHeld";
				break;
			case 1:
				str = "onItemRightClick";
				break;
		}
	}
	return str;
}

@Override
public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
	int i = -1;
	int c = 0;
	ItemStack stack;
	do {
		c++;
		i = world.rand.nextInt(40);
		stack = player.inventory.getStackInSlot(i);
	} while((stack == null || stack == itemStack || !stack.isItemDamaged()) && c < 100);
	if(stack != null && stack.isItemDamaged()) {
		//stack.setItemDamage(stack.getItemDamage()-5);
		//par1ItemStack.damageItem(5, (EntityLivingBase) par3EntityPlayer);
		PacketBuffer out =  new PacketBuffer(Unpooled.buffer());
		out.writeInt(PacketHandlerServer.REPAIRING);
		out.writeInt(i);
		out.writeInt(player.inventory.currentItem);
		CToSMessage packet = new CToSMessage(out);
		DragonArtifacts.artifactNetworkWrapper.sendToServer(packet);

		//par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()+1);
	}
	return itemStack;
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Repairs other items") + " " + StatCollector.translateToLocal("tool."+trigger));
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Repairs other items"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(3)) {
		case 0:
			str = "Recharging";
			break;
		case 1:
			str = "Repairing";
			break;
		case 2:
			str = "Refreshing";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Repair";
			break;
		case 1:
			str = "of Renew";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.AMULET | Flags.FIGURINE | Flags.RING | Flags.TRINKET | Flags.LEGGINGS;//77;
}

@Override
public int getNegTextureBitflags() {
	return 0;
}

@Override
public void onHeld(ItemStack itemStack, World world, Entity entity, int slot, boolean isWorn) {
	//onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
//		int del = par1ItemStack.stackTagCompound.getInteger("repairDelay");
//		if(del > 0) {
//			--del;
//		}
//		else
	if(entity instanceof EntityPlayer){
		if(ArtifactTickHandler.repairCount % 200 == 0) {
			//par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() + 1);
			EntityPlayer player = (EntityPlayer)entity;
			int i = -1;
			int c = 0;
			ItemStack stack;
			do {
				c++;
				i = world.rand.nextInt(player.inventory.getSizeInventory());
				stack = player.inventory.getStackInSlot(i);
			} while((stack == null || stack == itemStack || !stack.isItemDamaged()) && c < 100);
			if(stack != null && stack.isItemDamaged()) {
				stack.setItemDamage(stack.getItemDamage()-1);
				itemStack.damageItem(1, player);
				//par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()+1);
			}
//				del = 200;
		}
	}
//		par1ItemStack.stackTagCompound.setInteger("repairDelay", del);
}

@Override
public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack, boolean worn) {
	if(worn)
		onHeld(itemStack, world, player, 0, true);
}
}

