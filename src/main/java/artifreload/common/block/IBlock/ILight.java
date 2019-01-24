package artifreload.common.block.IBlock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import artifreload.common.block.baseBlock.ClassAbstract.IInvisibleBlock;


public class ILight extends IInvisibleBlock {

public static Block instance;

public ILight() {
	super(Material.AIR,"artifactlight", 0);
	this.setLightLevel(1);
	this.setResistance(0);
	setCreativeTab(null);
	this.setTickRandomly(true); //So it destroys itself over time.
}
/*
@Override
public void updateTick(World world, int x, int y, int z, Random rand) {
	world.setBlockToAir(x, y, z);
}

@Override
public void registerBlockIcons(IIconRegister par1IconRegister)
{
	this.blockIcon = Blocks.glowstone.getBlockTextureFromSide(0);//par1IconRegister.registerIcon("artifacts:light");
}

public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
{
	return false;
}

public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
{
	return null;
}

public boolean isOpaqueCube()
{
	return false;
}

public boolean renderAsNormalBlock()
{
	return false;
}

public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
{
	return null;
}

@Override
public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
{
	return true;
}

public int quantityDropped(Random par1Random)
{
	return 0;
}*/
}


