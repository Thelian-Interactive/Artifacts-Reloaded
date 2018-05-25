package artifreload.common.block.baseBlock;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import artifreload.common.DragonArtifacts;


public abstract class BlockContainerBase extends BlockContainer {

private static final CreativeTabs tab = DragonArtifacts.BlocksTab;

protected String name;

public BlockContainerBase(Material material, String name) {

	super(material);
	this.name = name;
	setUnlocalizedName(name);
	setRegistryName(name);
	setCreativeTab(tab);
	this.setHardness();

}


}