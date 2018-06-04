package artifreload.common.block.IBlock;



//TODO Ask Draco18s What the AntiBuilder is and its Functions
/*
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import artifreload.common.DragonArtifacts;
import artifreload.common.entity.TEAntibuilder;
*/


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.*;

import artifreload.common.block.baseBlock.*;


public class IAntibuilder extends BlockBase {

	protected String name;

public IAntibuilder() {

	super(Material.ROCK, "antibuilder", 1.0F);
	setUnlocalizedName(name);
	setRegistryName(name);
	setCreativeTab(tab);
	this.setResistance(200.0F);
	this.setSoundType(SoundType.STONE);

}


/*
public static Block instance;

public IAntibuilder() {
	super(Material.ROCK);
	setHardness(1.0F);
	setResistance(200.0F);
	setStepSound(Block.soundTypeStone);
	setCreativeTab(DragonArtifacts.tabGeneral);
}

@Override
public void registerBlockIcons(IIconRegister iconRegister)
{
	this.blockIcon = iconRegister.registerIcon("stonebrick_carved");
}

@Override
public TileEntity createNewTileEntity(World world, int meta) {
	return new TileEntityAntibuilder();
}

@Override
public int quantityDropped(Random par1Random)
{
	return 0;
}

	/*public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            boolean flag = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4);
            TEAntibuilder tab = (TEAntibuilder)par1World.getBlockTileEntity(par2, par3, par4);
            tab.setActive(!flag);
        	//System.out.println("Antibuilder should be active: " + (!flag));
        }
    }
    */
}