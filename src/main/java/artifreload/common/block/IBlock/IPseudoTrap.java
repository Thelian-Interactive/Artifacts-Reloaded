package artifreload.common.block.IBlock;


import net.minecraft.block.material.Material;

import artifreload.common.block.baseBlock.BlockBase;


public class IPseudoTrap extends BlockBase {


public IPseudoTrap() {
	super(Material.ROCK,"pseudotrap", 2.0F);
}



/*
@Override
public IIcon getIcon(int par1, int par2)
{
	int k = par2 & 7;
	return par1 == k ? (k != 1 && k != 0 ? this.blockIcon : this.blockIcon) : (k != 1 && k != 0 ? (par1 != 1 && par1 != 0 ? this.furnaceFrontIcon : this.furnaceTopIcon) : this.furnaceTopIcon);
}

@Override
public void registerBlockIcons(IIconRegister par1IconRegister)
{
	this.furnaceFrontIcon = Blocks.furnace.getBlockTextureFromSide(2);
	this.furnaceTopIcon = Blocks.furnace.getBlockTextureFromSide(0);
	this.blockIcon = par1IconRegister.registerIcon("artifacts:pseudo_trap_front");
}*/
}

