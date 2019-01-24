package artifreload.common.block.IBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import artifreload.common.DragonArtifacts;
import artifreload.common.block.baseBlock.BlockBase;


public class ISpikes extends BlockBase {

public static Block instance;
public int renderID = 0;

public ISpikes() {
	super(Material.IRON,"ispikes", 2.0F);
	setResistance(5F);
	setSoundType(SoundType.ANVIL);
	setCreativeTab(tab);

}
/*
@Override
public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
{
	return canBlockStay(par1World, par2, par3, par4);
}

@Override
public boolean canBlockStay(World par1World, int par2, int par3, int par4)
{
	return par1World.isSideSolid(par2, par3-1, par4, ForgeDirection.UP);
}

@Override
public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block block)
{
	if (!this.canBlockStay(par1World, par2, par3, par4))
	{
		par1World.func_147480_a/*destroyBlock(par2, par3, par4, true);
	}
}

@Override
public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
{
	if(entity instanceof EntityLivingBase) {
		entity.attackEntityFrom(DamageSource.generic, 2);
		entity.motionX *= 0.7D;
		entity.motionZ *= 0.7D;
		TileEntity te = world.getTileEntity(x, y, z);
		if(te != null && te instanceof TileEntitySpikes) {
			((TileEntitySpikes)te).setBloody(2);
		}
	}
}

@Override
public void registerBlockIcons(IIconRegister par1IconRegister)
{
	this.blockIcon = par1IconRegister.registerIcon("artifacts:iron_spikes");
}

@Override
public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
{
	float f = 0.0625F;
	return AxisAlignedBB.getBoundingBox((double)((float)par2), (double)par3, (double)((float)par4), (double)((float)(par2 + 1)), (double)(par3 + 1 - f), (double)((float)(par4 + 1)));
}

@Override
public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
{
	float f = 0.0625F;
	return AxisAlignedBB.getBoundingBox((double)((float)par2), (double)par3, (double)((float)par4), (double)((float)(par2 + 1)), (double)(par3 + 1 - f), (double)((float)(par4 + 1)));
}

@Override
public boolean renderAsNormalBlock()
{
	return false;
}

@Override
public int getRenderType()
{
	return -1;
}

@Override
public boolean isOpaqueCube()
{
	return false;
}

@Override
public TileEntity createNewTileEntity(World world, int meta) {
	return new TileEntitySpikes();
}*/
}
