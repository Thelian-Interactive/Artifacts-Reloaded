package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

import io.netty.buffer.Unpooled;


public class CFireBall extends  {

public ComponentFireball() {
}

public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) return "";
	String str = "";
	switch(rand.nextInt(2)) {
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
	PacketBuffer out =  new PacketBuffer(Unpooled.buffer());
	out.writeInt(PacketHandlerServer.FIREBALLS);
	out.writeInt(player.getEntityId());
	out.writeInt(player.inventory.currentItem);
	CToSMessage packet = new CToSMessage(out);
	DragonArtifacts.artifactNetworkWrapper.sendToServer(packet);
	UtilsForComponents.sendItemDamagePacket(player, player.inventory.currentItem, 1);
	itemStack.stackTagCompound.setInteger("onItemRightClickDelay", 20);

	return itemStack;
}

@Override
public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLivingHit, EntityLivingBase entityLivingPlayer) {

	if(!entityLivingPlayer.worldObj.isRemote && entityLivingPlayer instanceof EntityPlayer) {
		System.out.println("Entity was hit. Summoning fireball.");
		EntityPlayer player = (EntityPlayer) entityLivingPlayer;
		World world = player.worldObj;

		Vec3 vec3 = player.getLook(1.0F);
		double d8 = 4.0D;
		//System.out.println(vec3);
		//EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, p.posX + vec3.xCoord * d8, p.posY, p.posZ + vec3.zCoord * d8, vec3.xCoord, vec3.yCoord, vec3.zCoord);
		EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, player, vec3.xCoord, vec3.yCoord, vec3.zCoord);
		entitylargefireball.posX += vec3.xCoord * d8;
		entitylargefireball.posZ += vec3.zCoord * d8;
		entitylargefireball.accelerationX = vec3.xCoord;
		entitylargefireball.accelerationY = vec3.yCoord;
		entitylargefireball.accelerationZ = vec3.zCoord;
		entitylargefireball.field_92057_e = 1;
			/*entitylargefireball.posX = p.posX;// + vec3.xCoord * d8;
                entitylargefireball.posY = p.posY + (double)(p.height / 2.0F);
                entitylargefireball.posZ = p.posZ;// + vec3.zCoord * d8;*/
		//System.out.println(entitylargefireball.posX + "," + entitylargefireball.posY + "," + entitylargefireball.posZ);
		world.spawnEntityInWorld(entitylargefireball);
		itemStack.damageItem(1, player);

	}
	return false;
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Shoots fireballs") + " " + StatCollector.translateToLocal("tool."+trigger));
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Shoots fireballs"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(3)) {
		case 0:
			str = "Flaming";
			break;
		case 1:
			str = "Burning";
			break;
		case 2:
			str = "Incinerating";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(3)) {
		case 0:
			str = "of Flames";
			break;
		case 1:
			str = "of Fire";
			break;
		case 2:
			str = "of Incineration";
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
	return Flags.AMULET | Flags.TRINKET | Flags.BELT | Flags.ARMOR;
}
}

