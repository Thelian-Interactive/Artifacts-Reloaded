package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import artifreload.common.components.BaseComponent;


public class CDamage extends BaseComponent {


public ComponentDamage() {
}

public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) return "";
	return "onHeld";
}

@Override
public ItemStack attached(ItemStack i, Random rand, int[] eff) {
	NBTTagCompound inbt = i.stackTagCompound;
	NBTTagCompound nnbt = new NBTTagCompound();
	NBTTagList nnbtl = new NBTTagList();
	int base = i.stackTagCompound.getInteger("material");
	switch(base) {
		case 0:
		case 3:
			base = 0;
			break;
	}
	base += 6;
	AttributeModifier att = new AttributeModifier("generic.attackDamage", rand.nextInt(5)+base, 0);
	nnbt.setLong("UUIDMost", att.getID().getMostSignificantBits());
	nnbt.setLong("UUIDLeast", att.getID().getLeastSignificantBits());
	nnbt.setString("Name", att.getName());
	nnbt.setDouble("Amount", att.getAmount());
	nnbt.setInteger("Operation", att.getOperation());
	nnbt.setString("AttributeName", att.getName());
	nnbtl.appendTag(nnbt);
	inbt.setTag("AttributeModifiers", nnbtl);
	//i.addEnchantment(Enchantment.sharpness, rand.nextInt(5)+1);
	return i;
}

@Override
public float getDigSpeed(ItemStack itemStack, Block block, int meta) {
	if (block == Blocks.web)
	{
		return 15.0F;
	}
	else
	{
		Material material = block.getMaterial();
		return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 0.0F : 1.5F;
	}
}

@Override
public boolean canHarvestBlock(Block par1Block, ItemStack itemStack) {
	if (par1Block == Blocks.web)
	{
		return true;
	}
	else
	{
		Material material = par1Block.getMaterial();
		return material == Material.plants || material == Material.vine || material == Material.coral || material == Material.leaves || material == Material.gourd;
	}
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	//par3List.add("Weapon damage");
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	//par3List.add("Weapon damage");
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(3)) {
		case 0:
			str = "Deadly";
			break;
		case 1:
			str = "Painful";
			break;
		case 2:
			str = "Killing";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Rending";
			break;
		case 1:
			str = "of Pain";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.DAGGER | Flags.SWORD;
}

@Override
public int getNegTextureBitflags() {
	return Flags.AMULET | Flags.FIGURINE | Flags.TRINKET | Flags.BELT | Flags.ARMOR;
}
}

