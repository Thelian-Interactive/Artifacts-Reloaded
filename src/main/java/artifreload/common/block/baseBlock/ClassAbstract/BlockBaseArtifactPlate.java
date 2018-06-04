package artifreload.common.block.baseBlock.ClassAbstract;


import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import artifreload.common.DragonArtifacts;


public abstract class BlockBaseArtifactPlate extends BlockBasePressurePlate {

public static final CreativeTabs tab = DragonArtifacts.ArtifactBlocksTab;
	protected String name;


	public BlockBaseArtifactPlate(Material material, String name, float hardness, boolean camo) {
		super(material);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
}

@Override
protected void playClickOnSound(World worldIn, BlockPos color) {

}

@Override
protected void playClickOffSound(World worldIn, BlockPos pos) {

}

@Override
protected int computeRedstoneStrength(World worldIn, BlockPos pos) {

	return 0;
}

@Override
protected int getRedstoneStrength(IBlockState state) {

	return 0;
}

@Override
protected IBlockState setRedstoneStrength(IBlockState state, int strength) {

	return null;
}







}
