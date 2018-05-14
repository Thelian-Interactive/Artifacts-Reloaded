package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;
import io.netty.buffer.Unpooled;


public class CHeal extends BaseComponent {


public ComponentHeal() {
}

@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) {
		return "onArmorTickUpdate";
	}
	String str = "";
	switch(rand.nextInt(4)) {
		case 0:
			str = "onUpdate";
			break;
		case 1:
			str = "hitEntity";
			break;
		case 2:
			str = "onItemRightClick";
			break;
		case 3:
			str = "onHeld";
			break;
	}
	return str;
}

//works once!?
//client side only!!
@Override
public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
	PacketBuffer out = new PacketBuffer(Unpooled.buffer());
	//System.out.println("Building packet...");
	out.writeInt(PacketHandlerServer.HEALING);
	out.writeFloat(1);
	CToSMessage packet = new CToSMessage(out);
	DragonArtifacts.artifactNetworkWrapper.sendToServer(packet);
	UtilsForComponents.sendItemDamagePacket(player, player.inventory.currentItem, 1);
	itemStack.stackTagCompound.setInteger("onItemRightClickDelay", 20);

	return itemStack;
}

@Override
public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLivingHit, EntityLivingBase entityLivingPlayer) {
	if(!entityLivingPlayer.worldObj.isRemote && entityLivingPlayer instanceof EntityPlayer) {
		EntityPlayer player = (EntityPlayer)entityLivingPlayer;
		player.heal(1.0F);
	}
	return false;
}

//works great
@Override
public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean held) {
	//Check that the artifact is in a baubles slot if it should be
	if(DragonArtifacts.baublesLoaded && stack.stackTagCompound != null &&
						UtilsForComponents.equipableByBaubles(stack.stackTagCompound.getString("iconName")) &&
						DragonArtifacts.baublesMustBeEquipped && slot >= 0) {
		return;
	}

	if(entity instanceof EntityLivingBase) {
		if(!world.isRemote && world.rand.nextInt(300) == 0) {//about 15 seconds
			EntityLivingBase elb = (EntityLivingBase)entity;
			elb.setHealth(elb.getHealth()+1);
			//elb.heal(1F);
		}
	}
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Heals the Player") + " " + StatCollector.translateToLocal("tool."+trigger));
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Heals the Player"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "Healing";
			break;
		case 1:
			str = "Rejuvenating";
			break;
		case 2:
			str = "Holy";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Healing";
			break;
		case 1:
			str = "of Rejuvenation";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.AMULET | Flags.ARMOR | Flags.BELT | Flags.BOOTS | Flags.CHESTPLATE | Flags.DAGGER | Flags.FIGURINE | Flags.HELM | Flags.LEGGINGS | Flags.RING | Flags.STAFF | Flags.SWORD | Flags.TRINKET | Flags.WAND;
}

@Override
public int getNegTextureBitflags() {
	return 0;
}

@Override
public void onHeld(ItemStack itemStack, World world,Entity entity, int slot, boolean held) {
	if(entity instanceof EntityLivingBase) {
		if(!world.isRemote && world.rand.nextInt(100) == 0) {//about 5 seconds
			EntityLivingBase elb = (EntityLivingBase)entity;
			elb.setHealth(elb.getHealth()+1);
			//elb.heal(1F);
		}
	}
}

@Override
public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack, boolean worn) {
	if(worn)
		onUpdate(itemStack, world, player, 0, true);
}
}

