package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;


public class CMining extends BaseComponent {


@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) return "";
	return "onDig";
}

@Override
public float getDigSpeed(ItemStack itemStack, Block block, int meta) {
	Item.ToolMaterial toolMaterial = Item.ToolMaterial.values()[itemStack.stackTagCompound.getInteger("material")];
	if(toolMaterial == toolMaterial.WOOD) {
		return (Items.wooden_pickaxe.getDigSpeed(itemStack, block, meta) / 2 * Item.ToolMaterial.values()[itemStack.stackTagCompound.getInteger("material")].getEfficiencyOnProperMaterial());
	}
	else if(toolMaterial == toolMaterial.STONE) {
		return (Items.stone_pickaxe.getDigSpeed(itemStack, block, meta) / 2 * Item.ToolMaterial.values()[itemStack.stackTagCompound.getInteger("material")].getEfficiencyOnProperMaterial());
	}
	else if(toolMaterial == toolMaterial.GOLD) {
		return (Items.golden_pickaxe.getDigSpeed(itemStack, block, meta) / 2 * Item.ToolMaterial.values()[itemStack.stackTagCompound.getInteger("material")].getEfficiencyOnProperMaterial());
	}
	else if(toolMaterial == toolMaterial.IRON) {
		return (Items.iron_pickaxe.getDigSpeed(itemStack, block, meta) / 2 * Item.ToolMaterial.values()[itemStack.stackTagCompound.getInteger("material")].getEfficiencyOnProperMaterial());
	}
	else if(toolMaterial == toolMaterial.EMERALD) {
		return (Items.diamond_pickaxe.getDigSpeed(itemStack, block, meta) / 2 * Item.ToolMaterial.values()[itemStack.stackTagCompound.getInteger("material")].getEfficiencyOnProperMaterial());
	}
	return (toolMaterial.values()[itemStack.stackTagCompound.getInteger("material")].getEfficiencyOnProperMaterial());
}

@Override
public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block block, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase) {
	par1ItemStack.damageItem(1, par7EntityLivingBase);
	return false;
}

@Override
public boolean canHarvestBlock(Block block, ItemStack itemStack) {
	Item.ToolMaterial toolMaterial = Item.ToolMaterial.values()[itemStack.stackTagCompound.getInteger("material")];

	if(toolMaterial == toolMaterial.WOOD) {
		return Items.wooden_pickaxe.func_150897_b/*canHarvestBlock*/(block);
	}
	else if(toolMaterial == toolMaterial.STONE) {
		return Items.stone_pickaxe.func_150897_b(block);
	}
	else if(toolMaterial == Item.ToolMaterial.EMERALD) {
		return Items.diamond_pickaxe.func_150897_b(block);
	}
	else if(toolMaterial == Item.ToolMaterial.IRON) {
		return Items.iron_pickaxe.func_150897_b(block);
	}
	else if(toolMaterial == Item.ToolMaterial.GOLD) {
		return Items.golden_pickaxe.func_150897_b(block);
	}
	return block == Blocks.obsidian ? toolMaterial.getHarvestLevel() == 3 : (block != Blocks.diamond_block && block != Blocks.diamond_ore ? (block != Blocks.emerald_ore && block != Blocks.emerald_block ? (block != Blocks.gold_block && block != Blocks.gold_ore ? (block != Blocks.iron_block && block != Blocks.iron_ore ? (block != Blocks.lapis_block && block != Blocks.lapis_ore ? (block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? (block.getMaterial() == Material.rock ? true : (block.getMaterial() == Material.iron ? true : block.getMaterial() == Material.anvil)) : toolMaterial.getHarvestLevel() >= 2) : toolMaterial.getHarvestLevel() >= 1) : toolMaterial.getHarvestLevel() >= 1) : toolMaterial.getHarvestLevel() >= 2) : toolMaterial.getHarvestLevel() >= 2) : toolMaterial.getHarvestLevel() >= 2);
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Effective Pickaxe"));
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Effective Pickaxe"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "Miner's";
			break;
		case 1:
			str = "Digging";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Mining";
			break;
		case 1:
			str = "of Quarrying";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.FIGURINE | Flags.STAFF | Flags.TRINKET | Flags.WAND;
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
	else {
		return -1;
	}
}
}

