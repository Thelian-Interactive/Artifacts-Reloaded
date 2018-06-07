package artifreload.common.block.IBlock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import artifreload.common.block.baseBlock.BlockBase;


public class IBlockMoveable extends BlockBase {



public IBlockMoveable() {
	super(Material.ROCK,"moveableblock", 1.5F);
	setResistance(10.0F);
	setSoundType(SoundType.STONE);
	setCreativeTab(tab);
}

public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
{
	return Item.getItemFromBlock(Blocks.STONEBRICK);
}
}
