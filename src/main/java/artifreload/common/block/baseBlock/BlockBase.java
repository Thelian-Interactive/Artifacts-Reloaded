package artifreload.common.block.baseBlock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import artifreload.common.DragonArtifacts;


public class BlockBase extends Block {

    private static final CreativeTabs tab = DragonArtifacts.ArtifactBlocksTab;
    protected String name;
    protected float hardness;

public BlockBase(Material material, String name) {
          super(material);
          this.name = name;
          setUnlocalizedName(name);
          setRegistryName(name);
          setCreativeTab(tab);
          this.setHardness(hardness);

    }

    public void registerItemModel(Item itemBlock) {
    	     DragonArtifacts.proxy.registerItemRenderer(itemBlock, 0, name);
				}

				public BlockBase setCreativeTab() {
    	super.setCreativeTab(tab);
    	return this;
				}
				public Item createItemBlock(){

	return new ItemBlock(this).setRegistryName(getRegistryName());

				}

}

public abstract class IInvisibleBlock extends BlockBase {

	 private static final CreativeTabs tab = DragonArtifacts.ArtifactBlocksTab;
	 protected String name;

      public IInvisibleBlock(Material material, String name) {
	            super(material, name);
	            this.name = name;
	            setUnlocalizedName(name);
	            setRegistryName(name);
	            setCreativeTab(tab);
	            this.setLightOpacity(0);
	            this.setHardness(hardness);

}

public void registerItemModel(Item itemBlock) {
      	DragonArtifacts.proxy.registerItemRenderer(itemBlock,0, name);
}
public Item createItemBlock(Item itemBlock) {
      	return new ItemBlock(this).setRegistryName(getRegistryName());
}

@Override
public boolean isOpaqueCube()
{
	return false;
}

@Override
public boolean shouldSideBeRendered(IBlockState iblockState, IBlockAccess iblockAccess, BlockPos ipos, EnumFacing iside)
{
	return DragonArtifacts.renderInvis;
}

@Override
 public AxisAlignedBB getCollisionBoundingBox(IBlockState iblockState, IBlockAccess iblockAccess, BlockPos ipos)
{
	if(DragonArtifacts.boundingInvis) {
		return super.getCollisionBoundingBox(iblockState, iblockAccess, ipos);
	}
	else {
		return null;
	}
}



public int quantityDropped(Random iRandom)
{
	return 0;
}
}
