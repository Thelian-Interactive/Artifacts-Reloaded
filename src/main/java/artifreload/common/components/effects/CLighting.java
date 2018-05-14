package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;
import io.netty.buffer.Unpooled;


public class CLighting extends BaseComponent {


@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) return "";
	String str = "";
	switch(rand.nextInt(3)) {
		case 0:
			str = "itemInteractionForEntity";
			break;
		case 1:
			str = "hitEntity";
			break;
		case 2:
			str = "onItemRightClick";
			break;
		case 3:
			str = "onDroppedByPlayer";
			break;
	}
	return str;
}

@Override
public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
	PacketBuffer out = new PacketBuffer(Unpooled.buffer());
	//System.out.println("Building packet...");
	out.writeInt(PacketHandlerServer.LIGHTNING);
	out.writeInt(player.getEntityId());
	out.writeInt(player.inventory.currentItem);
	CToSMessage packet = new CToSMessage(out);
	DragonArtifacts.artifactNetworkWrapper.sendToServer(packet);
	UtilsForComponents.sendItemDamagePacket(player, player.inventory.currentItem, 1);
	itemStack.stackTagCompound.setInteger("onItemRightClickDelay", 5);

	return itemStack;
}

@Override
public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLivingHit, EntityLivingBase entityLivingPlayer) {
	if(!entityLivingPlayer.worldObj.isRemote && entityLivingPlayer instanceof EntityPlayer) {
		EntityLightningBolt entityLightningBolt = new EntityLightningBolt(entityLivingHit.worldObj, entityLivingHit.posX, entityLivingHit.posY, entityLivingHit.posZ);
		entityLivingHit.worldObj.addWeatherEffect(entityLightningBolt);
	}
	return false;
}

@Override
public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase entityLiving) {
	if(player.worldObj.isRemote) {
		PacketBuffer out = new PacketBuffer(Unpooled.buffer());
		//System.out.println("Building packet...");
		out.writeInt(PacketHandlerServer.LIGHTNING);
		out.writeInt(player.getEntityId());
		//EntityPlayer par3EntityPlayer = (EntityPlayer) par3EntityLivingBase;
		out.writeInt(player.inventory.currentItem);
		//out.writeFloat(par3EntityPlayer.getHealth()+1);
		CToSMessage packet = new CToSMessage(out);
		DragonArtifacts.artifactNetworkWrapper.sendToServer(packet);
		//par1ItemStack.damageItem(1, par2EntityPlayer);
		return true;

	}
	return false;
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Causes lightning") + " " + StatCollector.translateToLocal("tool."+trigger));
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Causes lightning"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "Shocking";
			break;
		case 1:
			str = "Electric";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Thunder";
			break;
		case 1:
			str = "of Lightning";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.RING | Flags.STAFF | Flags.WAND;
}

@Override
public int getNegTextureBitflags() {
	return Flags.AMULET | Flags.ARMOR | Flags.BELT;
}
}

