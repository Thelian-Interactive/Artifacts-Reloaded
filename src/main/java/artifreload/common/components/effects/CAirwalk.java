package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;


public class CAirwalk extends BaseComponent {


public CAirWalk() {
}

public String getRandomTrigger(Random rand, boolean isArmor) {
	if(!isArmor) {
		return "onHeld";
	}
	else {
		return "onArmorTickUpdate";
	}
}

public void addInformation(ItemStack itemStack, EntityPlayer player, List list, String trigger, boolean advTooltip) {
	list.add(StatCollector.translateToLocal("effect.Allows sneaking on air") + " " + StatCollector.translateToLocal("tool."+trigger));
}

@Override
public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advTooltip) {
	list.add(StatCollector.translateToLocal("effect.Allows sneaking on air"));
}

@Override
public void onHeld(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {

	//Similar to the ComponentLight code. Sets the block beneath the player to solid air,
	//and cleans up any solid air blocks that were there before.
	if(entity instanceof EntityPlayer) {
		EntityPlayer player = (EntityPlayer) entity;
		if(player.isSneaking()) {
			int lastX = itemStack.stackTagCompound.getInteger("lastAirX");
			int lastY = itemStack.stackTagCompound.getInteger("lastAirY");
			int lastZ = itemStack.stackTagCompound.getInteger("lastAirZ");
			int newX = MathHelper.floor_double(entity.posX);
			int newY = MathHelper.floor_double(entity.posY-1);
			int newZ = MathHelper.floor_double(entity.posZ);

			//If the player isn't standing on solid air, set the block beneath their feet so solid air.
			if(world.getBlock(newX, newY, newZ) != BlockSolidAir.instance) {
				if(lastY >= 0 && lastY < 256 && world.getBlock(lastX, lastY, lastZ) == BlockSolidAir.instance) {
					//System.out.println("Setting solid air at x="+lastX+", y="+lastY+", z="+lastZ+" to air.");
					world.setBlockToAir(lastX, lastY, lastZ);
				}
				if(newY >= 0 && newY < 256 && world.isAirBlock(newX, newY, newZ)) {
					//System.out.println("Creating solid air at x="+newX+", y="+newY+", z="+newZ+".");
					boolean worked = world.setBlock(newX, newY, newZ, BlockSolidAir.instance);
					if(!DragonArtifacts.airWalkSpam) {

					}else {
						System.out.println(worked);
					}
					itemStack.stackTagCompound.setInteger("lastAirX",newX);
					itemStack.stackTagCompound.setInteger("lastAirY",newY);
					itemStack.stackTagCompound.setInteger("lastAirZ",newZ);
				}
			}
		}
		else {
			//Clean up left-over blocks.
			//Only happens when player is holding the artifact, so they will disappear like leaves otherwise.
			int lastX = itemStack.stackTagCompound.getInteger("lastAirX");
			int lastY = itemStack.stackTagCompound.getInteger("lastAirY");
			int lastZ = itemStack.stackTagCompound.getInteger("lastAirZ");

			if(lastY != -1 && lastY >= 0 && lastY < 256 && world.getBlock(lastX, lastY, lastZ) == BlockSolidAir.instance) {
				//System.out.println("Player is no longer shifting. Setting x="+lastX+", y="+lastY+", z="+lastZ+" to air.");
				world.setBlockToAir(lastX, lastY, lastZ);
				itemStack.stackTagCompound.setInteger("lastAirY",-1);
			}
		}
	}
}

@Override
public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack, boolean worn) {
	if(worn)
		onHeld(itemStack, world, player, 0, true);
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "Floating";
			break;
		case 1:
			str = "Light";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Hermes";
			break;
		case 1:
			str = "of Flight";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.AMULET | Flags.RING | Flags.STAFF | Flags.TRINKET | Flags.WAND | Flags.ARMOR | Flags.BOOTS;
}

@Override
public int getNegTextureBitflags() {
	return Flags.DAGGER | Flags.SWORD | Flags.FIGURINE | Flags.HELM | Flags.BELT;
}

}

