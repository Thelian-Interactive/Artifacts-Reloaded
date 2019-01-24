package artifreload.common.block.IBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import artifreload.common.DragonArtifacts;
import artifreload.common.block.baseBlock.ClassAbstract.IInvisibleBlock;


public class IInvisBlock extends IInvisibleBlock {
public static Block instance;

public IInvisBlock() {
	super(Material.ROCK,"invisblock",2.0F);
	setResistance(10F);
	setSoundType(SoundType.STONE);
	this.setCreativeTab(tab);
}
}

