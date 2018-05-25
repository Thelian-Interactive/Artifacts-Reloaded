package artifreload.common.block.IBlock;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import artifreload.common.DragonArtifacts;
import artifreload.common.block.baseBlock.IInvisibleBlock;


public class IInvisBedrock extends IInvisibleBlock {

public static Block instance;

public BlockInvisibleBedrock() {
	super(Material.rock);
	setResistance(-1F);
	setStepSound(Block.soundTypeStone);
	setHardness(-1F);
	this.setCreativeTab(DragonArtifacts.tabGeneral);

}

@Override
public void registerBlockIcons(IIconRegister iconRegister)
{
	blockIcon = iconRegister.registerIcon("artifacts:adainvisible");
}
}