package artifreload.common.block.IBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

//forge

//artifacts
import artifreload.api.ArtifactAPI;
import artifreload.common.entity.TESword;
import artifreload.common.item.FakeSword;


public class ISword extends BlockContainer {

public ISword() {
	super(Material.ROCK);
	this.setBlockBounds(0.4f, 0.0f, 0.4f, 0.6f, 0.5f, 0.6f);
}

@Override
public TileEntity createNewTileEntity(World world, int meta) {
	return new TileEntitySword();
}

public boolean renderAsNormalBlock()
{
	return false;
}

public int getRenderType()
{
	return -1;
}

public boolean isOpaqueCube()
{
	return false;
}

public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
{
	return null;
}

public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
	return null;
}

@SideOnly(Side.CLIENT)
@Override
public void registerBlockIcons(IIconRegister iconReg)
{
	blockIcon = ItemFakeSwordRenderable.wood.getIcon(new ItemStack(ItemFakeSwordRenderable.wood), 0);
}
}

