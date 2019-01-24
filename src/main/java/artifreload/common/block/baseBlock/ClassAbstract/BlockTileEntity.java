package artifreload.common.block.baseBlock.ClassAbstract;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import artifreload.common.block.baseBlock.BlockBase;


public abstract class BlockTileEntity<TE extends TileEntity> extends BlockBase {


public BlockTileEntity(Material material, String name, float hardness) {
	super(material, name, hardness);
}

public abstract Class<TE> getTileEntityClass();

public TE getTileEntity(IBlockAccess world, BlockPos pos) {
	return (TE)world.getTileEntity(pos);
}

@Override
public boolean hasTileEntity(IBlockState state) {
	return true;
}

@Nullable
@Override
public abstract TE createTileEntity(World world, IBlockState state);


}