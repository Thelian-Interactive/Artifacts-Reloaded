package artifreload.common.block.IBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class IPseudoIllusionary extends Block {
public static Block instance;

public PseudoBlockIllusionary() {
	super(Material.rock);
}

@Override
public IIcon getIcon(int par1, int par2)
{
	return this.blockIcon;
}

@Override
public void registerBlockIcons(IIconRegister iconRegister)
{
	this.blockIcon = iconRegister.registerIcon("artifacts:invisible");
}
}
