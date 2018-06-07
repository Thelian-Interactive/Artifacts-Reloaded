package artifreload.common.block.IBlock;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import artifreload.common.DragonArtifacts;
import artifreload.common.block.baseBlock.BlockBase;
import artifreload.common.block.baseBlock.ClassAbstract.IInvisibleBlock;


public class IInvisBedrock extends IInvisibleBlock {

public static Block instance;

public IInvisBedrock() {
	super(Material.ROCK,"invisbedrock",-1F);
	setResistance(-1F);
	setSoundType(SoundType.STONE);
	setHardness(-1F);
	this.setCreativeTab(tab);

}








}