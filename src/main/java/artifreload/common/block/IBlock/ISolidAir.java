package artifreload.common.block.IBlock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import artifreload.common.block.baseBlock.ClassAbstract.IInvisibleBlock;


public class ISolidAir extends IInvisibleBlock {

public static Block instance;

public ISolidAir() {
	super(Material.AIR,"solidair", 0F);
	setResistance(0F);
	setSoundType(SoundType.LADDER);
	setCreativeTab(null);
	this.setTickRandomly(true); //So it destroys itself over time.
}
/*
@Override
public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
{
	return null;
}

@Override
public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
{
	return true;
}

@Override
public void updateTick(World world, int x, int y, int z, Random random)
{
	//Set itself to air (self-destruction).
	world.setBlockToAir(x, y, z);
}

@Override
public void onEntityWalking(World world, int x, int y, int z, Entity entity)
{
	super.onEntityWalking(world, x, y, z, entity);
	if(entity instanceof EntityPlayer && !entity.isSneaking()) {
		world.setBlockMetadataWithNotify(x, y, z, 0, 3);
	}
}

@Override
public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
{
	return false;
}

public int quantityDropped(Random par1Random)
{
	return 0;
}*/
}

