package artifreload.common.block.IBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import artifreload.common.DragonArtifacts;
import artifreload.common.block.IInvisibleBlock;


public class IInvisBlock extends IInvisibleBlock {
public static Block instance;

public BlockInvisibleBlock() {
	super(Material.rock);
	setResistance(10F);
	setStepSound(Block.soundTypeStone);
	setHardness(2.0F);
	this.setCreativeTab(DragonArtifacts.tabGeneral);
}
}

