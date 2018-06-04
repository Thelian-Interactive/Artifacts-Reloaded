package artifreload.common.block.IBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import artifreload.common.block.baseBlock.BlockBase;


public class IPseudoCoverplate extends BlockBase {

public static Block instance;

public IPseudoCoverplate() {
	super(Material.ROCK,"pseudocoverplate", 0.5F);
}

@Override
public IIcon getIcon(int par1, int par2)
{
	if(par1 == 4 || par1 == 5)
		return this.blockIcon;
	return Blocks.stonebrick.getIcon(par1, par2);
}

@Override
public void registerBlockIcons(IIconRegister par1IconRegister)
{
	this.blockIcon = par1IconRegister.registerIcon("artifacts:pseudo_coverplate");
}

@Override
public void setBlockBoundsForItemRender()
{
	float f1 = 0.125F;
	this.setBlockBounds(0.5F, 0, 0, 0.5F + f1, 1, 1);
}
}
