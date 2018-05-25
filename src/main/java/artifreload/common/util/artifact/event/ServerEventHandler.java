package artifreload.common.util.artifact.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.oredict.OreDictionary;


public class ServerEventHandler {


@SubscribeEvent
public void EntityHurtEvent(LivingHurtEvent event) {
	//System.out.println("Hurt event detected");
	if(event.entity instanceof EntityPlayer) {
		EntityPlayer player = (EntityPlayer)event.entity;
		//AttributeInstance atinst = player.getEntityAttribute(ArtifactsAPI.OnHurtAttribute);
		//AttributeModifier mod;
		//mod = new AttributeModifier(resID,"ResurrectComponent",1F,2);
		//if(atinst != null) {
		for(int a=0; a < 4; a++) {
			if(player.inventory.armorInventory[a] != null && player.inventory.armorInventory[a].getItem() instanceof ItemArtifactArmor) {
				NBTTagCompound data = player.inventory.armorInventory[a].getTagCompound();
				int effectID = data.getInteger("onTakeDamage");
				if(effectID != 0) {
					IArtifactComponent c = ArtifactsAPI.artifacts.getComponent(effectID);
					c.onTakeDamage(player.inventory.armorInventory[a], event, true);
				}
			}
		}
		for(int a = player.inventory.mainInventory.length-1; a >= 0; a--) {
			if(player.inventory.mainInventory[a] != null && player.inventory.mainInventory[a].getItem() instanceof ItemArtifactArmor) {
				NBTTagCompound data = player.inventory.mainInventory[a].getTagCompound();
				int effectID = data.getInteger("onTakeDamage");
				if(effectID != 0) {
					IArtifactComponent c = ArtifactsAPI.artifacts.getComponent(effectID);
					c.onTakeDamage(player.inventory.mainInventory[a], event, false);
				}
			}
		}
		//}
	}
}

@SubscribeEvent
public void EntityDeathEvent(LivingDeathEvent event) {
	//System.out.println("Death event detected");
	if(event.entity instanceof EntityPlayer) {
		System.out.println("Is a player");
		EntityPlayer player = (EntityPlayer)event.entity;
		//AttributeInstance atinst = player.getEntityAttribute(ArtifactsAPI.OnDeathAttribute);
		//AttributeModifier mod;
		//mod = new AttributeModifier(resID,"ResurrectComponent",1F,2);
		//if(atinst != null) {
		//System.out.println("Has attribute");
		for(int a=0; a < 4; a++) {
			if(player.inventory.armorInventory[a] != null && player.inventory.armorInventory[a].getItem() instanceof ItemArtifactArmor) {
				NBTTagCompound data = player.inventory.armorInventory[a].getTagCompound();
				int effectID = data.getInteger("onDeath");
				//System.out.println("EffectID: " + effectID);
				if(effectID != 0) {
					IArtifactComponent c = ArtifactsAPI.artifacts.getComponent(effectID);
					c.onDeath(player.inventory.armorInventory[a], event, true);
				}
			}
		}
		for(int a = player.inventory.mainInventory.length-1; a >= 0; a--) {
			if(player.inventory.mainInventory[a] != null && player.inventory.mainInventory[a].getItem() instanceof ItemArtifactArmor) {
				NBTTagCompound data = player.inventory.mainInventory[a].getTagCompound();
				int effectID = data.getInteger("onDeath");
				if(effectID != 0) {
					IArtifactComponent c = ArtifactsAPI.artifacts.getComponent(effectID);
					c.onDeath(player.inventory.mainInventory[a], event, false);
				}
			}
		}
		//}
	}
}

@SubscribeEvent
public void oreRegisterEvent(OreDictionary.OreRegisterEvent event) {
	if(event.Name.indexOf("ore") >= 0 || event.Name.indexOf("gem") >= 0) {
		ComponentOreRadar.addOre(event.Ore);
	}
}

@SubscribeEvent
public void interactEvent(PlayerInteractEvent event) {
	World world = event.entityPlayer.worldObj;
	if(!world.isRemote) {
		if(world.getBlock(event.x, event.y, event.z) == Blocks.enchanting_table) {
			ArtifactTickHandler.instance.readyTickHandler(world, event.entityPlayer);
		}
	}
}

@SubscribeEvent
@SideOnly(Side.CLIENT)
public void registerTextures(TextureStitchEvent.Pre event) {
	//System.out.println("before: " + event.map.getTextureExtry("artifacts:calendar"));
	//TextureMap tm = (TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationItemsTexture);
	//boolean re = tm.setTextureEntry("artifacts:calendar", new TextureCalendar("artifacts:calendar"));
	//System.out.println("Able to register calendar texture: " + re);
	//ItemCalendar.instance.setTextureName("artifacts:calendar");
	//System.out.println(event.map.getTextureExtry("artifacts:calendar"));
	if(event.map.getTextureType() == 1 ) {
		event.map.setTextureEntry("artifacts:calendar", ClientProxy.calendar = new TextureCalendar("artifacts:calendar"));
	}
}

private boolean blockUnderAntibuilderInfluence(World world, int x, int y, int z) {
	if(world.getBlock(x, y, z) == Blocks.fire ||
						world.getBlock(x, y, z) == BlockAntibuilder.instance ||
						world.getBlock(x, y, z) == Blocks.web ||
						world.getBlock(x, y, z) == Blocks.torch ||
						world.getBlock(x, y, z) == BlockSword.instance) {
		return false;
	}

	for(AntibuilderLocation al : TileEntityAntibuilder.antibuilders.keySet()) {
		if(world.provider.dimensionId == al.dimension && MathHelper.abs(x - al.x) <= 5 && MathHelper.abs(y - al.y) <= 5 && MathHelper.abs(z - al.z) <= 5) {
			return true;
		}
	}

	return false;
}

@SubscribeEvent
public void onBlockDestroyedByPlayer(BlockEvent.BreakEvent event) {
	if(blockUnderAntibuilderInfluence(event.world, event.x, event.y, event.z)) {
		event.setCanceled(true);
	}
}

public static boolean ignore = false;

@SubscribeEvent
public void onBlockHarvested(BlockEvent.HarvestDropsEvent event) {
	if(!ignore && blockUnderAntibuilderInfluence(event.world, event.x, event.y, event.z)) {
		event.drops.clear();
	}
}
}

