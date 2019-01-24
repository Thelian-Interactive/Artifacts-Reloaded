package artifreload.common.block.IBlock;


import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;

import net.minecraft.util.SoundCategory;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import artifreload.common.DragonArtifacts;
import artifreload.common.block.baseBlock.ClassAbstract.BlockBaseArtifactPlate;


public class IPressurePlate extends BlockBaseArtifactPlate {


public static final PropertyBool POWERED = PropertyBool.create("powered");
private final  IPressurePlate.Sensitivity sensitivity;


public IPressurePlate(IPressurePlate.Sensitivity sensitivity) {
	super(Material.ROCK,"pressplate", 0.5F, false, true, false);
	this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, false));
	setCreativeTab(tab);
	this.sensitivity = sensitivity;

}

protected int getRedstoneStrength(IBlockState state) {

	return ((Boolean)state.getValue(POWERED)).booleanValue() ? 15 : 0;

}

protected IBlockState setRedstoneStrength(IBlockState state, int strength) {

	return state.withProperty(POWERED, Boolean.valueOf(strength > 0));

}

protected void playClickOnSound(World worldIn, BlockPos color)
{
		worldIn.playSound((EntityPlayer)null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
	}


protected void playClickOffSound(World worldIn, BlockPos pos)
{
		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
	}

protected int computeRedstoneStrength(World worldIn, BlockPos pos)
{
	AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
	List <? extends Entity > list;

	switch (this.sensitivity)
	{
		case EVERYTHING:
			list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb);
			break;

		case MOBS:
			list = worldIn.<Entity>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
			break;

		default:
			return 0;
	}

	if (!list.isEmpty())
	{
		for (Entity entity : list)
		{
			if (!entity.doesEntityNotTriggerPressurePlate())
			{
				return 15;
			}
		}
	}

	return 0;
}

/**
 * Convert the given metadata into a BlockState for this Block
 */
public IBlockState getStateFromMeta(int meta)
{
	return this.getDefaultState().withProperty(POWERED, Boolean.valueOf(meta == 1));
}

/**
 * Convert the BlockState into the correct metadata value
 */
public int getMetaFromState(IBlockState state)
{
	return ((Boolean)state.getValue(POWERED)).booleanValue() ? 1 : 0;
}

protected BlockStateContainer createBlockState()
{
	return new BlockStateContainer(this, new IProperty[] {POWERED});
}




























public enum Sensitivity
{
	EVERYTHING,
	MOBS
}



}
