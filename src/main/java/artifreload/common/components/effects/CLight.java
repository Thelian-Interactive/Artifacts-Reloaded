package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;


public class CLight extends BaseComponent {


@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) {
		return "onArmorTickUpdate";
	}
	return "onUpdate";
}

@Override
public ItemStack attached(ItemStack i, Random rand, int[] eff) {
	i.stackTagCompound.setInteger("lastLightX", -1);
	i.stackTagCompound.setInteger("lastLightY", -1);
	i.stackTagCompound.setInteger("lastLightZ", -1);
	return i;
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

	//If armor check if equipped
	if(slot >= 0 && stack.stackTagCompound != null &&
						UtilsForComponents.isArmor(stack.stackTagCompound.getString("iconName"))) {
		return;
	}

	if(!world.isRemote) {

		int lx = stack.stackTagCompound.getInteger("lastLightX");
		int ly = stack.stackTagCompound.getInteger("lastLightY");
		int lz = stack.stackTagCompound.getInteger("lastLightZ");

		if(entity instanceof EntityPlayer) {

			int nlx = MathHelper.floor_double(entity.posX);
			int nly = MathHelper.floor_double(entity.posY);
			int nlz = MathHelper.floor_double(entity.posZ);
			boolean setLightBlock = false;

			if(nlx != lx || nly != ly || nlz != lz) {
				int d = (nlx - lx)*(nlx - lx)+(nly - ly)*(nly - ly)+(nlz - lz)*(nlz - lz);
				if(world.getBlockLightValue(nlx,nly,nlz) < 10) {
					if(d > 13) {
						//updating lighting info isn't fast :(
						if(ly >= 0 && ly < 256 && world.getBlock(lx, ly, lz) == BlockLight.instance) {
							world.setBlockToAir(lx, ly, lz);
							//System.out.println("Removed: " + lx + "," + ly + "," + lz);
						}
						//Set the light block at the player's feet (if possible)
						setLightBlock = true;
					}
					else {
						if(world.getBlock(lx, ly, lz) != BlockLight.instance) {

							if(world.isAirBlock(lx, ly, lz) && world.getBlock(lx, ly, lz) != BlockSolidAir.instance) {
								//Reset the block if it disappeared
								world.setBlock(lx, ly, lz, BlockLight.instance);
							}
							else {
								//Set the new light block at the player's feet (if possible)
								setLightBlock = true;
							}
						}
					}
				}

				if(setLightBlock && nly >= 0 && nly < 256 && world.isAirBlock(nlx, nly, nlz) && world.getBlock(nlx, nly, nlz) != BlockSolidAir.instance) {
					world.setBlock(nlx, nly, nlz, BlockLight.instance);
					stack.stackTagCompound.setInteger("lastLightX",nlx);
					stack.stackTagCompound.setInteger("lastLightY",nly);
					stack.stackTagCompound.setInteger("lastLightZ",nlz);
				}
			}
		}
	}
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Provides illumination"));
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Provides illumination"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "Illuminating";
			break;
		case 1:
			str = "Bright";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Light";
			break;
		case 1:
			str = "of the Sun";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.AMULET | Flags.FIGURINE | Flags.RING | Flags.STAFF | Flags.TRINKET | Flags.WAND | Flags.ARMOR | Flags.HELM;
}

@Override
public int getNegTextureBitflags() {
	return Flags.BOOTS | Flags.CHESTPLATE | Flags.LEGGINGS;
}

@Override
public boolean onEntityItemUpdate(EntityItem entityItem, String type) {
	if(type == "onUpdate") {
		World world = entityItem.worldObj;
		NBTTagCompound data = entityItem.getEntityItem().stackTagCompound;
		int lx = data.getInteger("lastLightX");
		int ly = data.getInteger("lastLightY");
		int lz = data.getInteger("lastLightZ");
		//System.out.println("Last: " + lx + "," + ly + "," + lz);
		int nlx = (int) entityItem.posX;
		int nly = (int) entityItem.posY;
		int nlz = (int) entityItem.posZ;
		if(nlx != lx || nly != ly || nlz != lz) {
			int d = (nlx - lx)*(nlx - lx)+(nly - ly)*(nly - ly)+(nlz - lz)*(nlz - lz);
			if(d > 13) {
				if(ly >= 0 && ly < 256 && world.getBlock(nlx, nly, nlz) == BlockLight.instance)
					world.setBlockToAir(lx, ly, lz);
				if(nly >= 0 && nly < 256 && world.isAirBlock(nlx, nly, nlz)) {
					world.setBlock(nlx, nly, nlz, BlockLight.instance);
					data.setInteger("lastLightX",nlx);
					data.setInteger("lastLightY",nly);
					data.setInteger("lastLightZ",nlz);
					//System.out.println("Placed: " + nlx + "," + nly + "," + nlz);
				}
			}
		}
		//entityItem.getEntityItem().stackTagCompound = data;
	}
	else {
		System.out.println("Hmm. " + type);
	}
	return false;
}

@Override
public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack, boolean worn) {
	onUpdate(itemStack, world, player, worn ? -1 : 0, false);
}
}

