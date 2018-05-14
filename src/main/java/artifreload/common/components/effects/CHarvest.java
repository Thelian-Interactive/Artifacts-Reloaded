package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;


public class CHarvest extends BaseComponent {


public ComponentHarvesting() {
}

@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) return "";
	return "onBlockDestroyed";
}

@Override
public float getDigSpeed(ItemStack itemStack, Block block, int meta) {
	if (block != Blocks.coal_ore && block != Blocks.iron_ore && block != Blocks.emerald_ore && block != Blocks.gold_ore && block != Blocks.diamond_ore && block != Blocks.quartz_ore && block != Blocks.lapis_ore && block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore)
	{
		return -1;
	}
	else {
		return (Item.ToolMaterial.values()[itemStack.stackTagCompound.getInteger("material")].getEfficiencyOnProperMaterial() / 2);
		//return 15;
	}
}

@Override
public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
	if (block != Blocks.coal_ore && block != Blocks.iron_ore && block != Blocks.emerald_ore && block != Blocks.gold_ore && block != Blocks.diamond_ore && block != Blocks.quartz_ore && block != Blocks.lapis_ore && block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore)
	{
		return false;
	}
	else if(world.rand.nextInt(4) == 0) {
		//drop another
			/*ItemStack is = new ItemStack(Block.blocksList[blockID], 1);
			EntityItem ei = new EntityItem(world, x, y, z, is);
			world.spawnEntityInWorld(ei);*/
		itemStack.damageItem(1, player);
		block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
	}
	return true;
}

@Override
public boolean canHarvestBlock(Block block, ItemStack itemStack) {
	if (block != Blocks.coal_ore && block != Blocks.iron_ore && block != Blocks.emerald_ore && block != Blocks.gold_ore && block != Blocks.diamond_ore && block != Blocks.quartz_ore && block != Blocks.lapis_ore && block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore)
	{
		return false;
	}
	else {
		return true;
	}
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Extra drops from ores"));
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Extra drops from ores"));
}

@Override
public String getPreAdj(Random rand) {
	return "Harvesting";
}

@Override
public String getPostAdj(Random rand) {
	return "of Prospecting";
}

@Override
public int getTextureBitflags() {
	return Flags.DAGGER | Flags.STAFF | Flags.WAND | Flags.TRINKET;
}

@Override
public int getNegTextureBitflags() {
	return Flags.AMULET | Flags.RING | Flags.ARMOR | Flags.BELT;
}

@Override
public int getHarvestLevel(ItemStack stack, String toolClass) {
	Item.ToolMaterial toolMaterial = Item.ToolMaterial.values()[stack.stackTagCompound.getInteger("material")];

	if(toolClass.equals("pickaxe")) {
		return toolMaterial.getHarvestLevel();
	}

	return -1;
}
}

