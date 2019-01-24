package artifreload.common.block.IBlock;

import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import artifreload.common.DragonArtifacts;
import artifreload.common.block.EBlock.TEPedestal;
import artifreload.common.block.baseBlock.BlockBase;
import artifreload.common.block.baseBlock.ClassAbstract.BlockTileEntity;
import artifreload.common.entity.TEDisplayPedestal;
import artifreload.common.gui.GuiHandler;


public class IPedestal extends BlockTileEntity<TEPedestal> {

protected String name;

public IPedestal() {
	super(Material.ROCK,"pedestal", 5.0F);
	setCreativeTab(tab);
	this.setResistance(10F);
	setSoundType(SoundType.GLASS);
	this.setLightOpacity(0);
	//setBlockBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 1.0F, 0.8125F);
}

@Override
public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
	if (!world.isRemote) {
		// ...
		if (!player.isSneaking()) {
			// ...
		} else {
			player.openGui(DragonArtifacts.instance, GuiHandler.PEDESTAL, world, pos.getX(), pos.getY(), pos.getZ());
		}
	}
	return true;

}

/*
@Override
public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
	if (!world.isRemote) {
		TEPedestal tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
		if (!player.isSneaking()) {
			if (heldItem.isEmpty()) {
				player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
			} else {
				player.setHeldItem(hand, itemHandler.insertItem(0, heldItem, false));
			}
			tile.markDirty();
		} else {
			ItemStack stack = itemHandler.getStackInSlot(0);
			if (!stack.isEmpty()) {
				String localized = DragonArtifacts.proxy.localize(stack.getUnlocalizedName() + ".name");
				player.addChatMessage(new TextComponentString(stack.getCount() + "x " + localized));
			} else {
				player.addChatMessage(new TextComponentString("Empty"));
			}
		}
	}
	return true;
}
*/

@Override
public void breakBlock(World world, BlockPos pos, IBlockState state) {
	TEPedestal tile = getTileEntity(world, pos);
	IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
	ItemStack stack = itemHandler.getStackInSlot(0);
	if (!stack.isEmpty()) {
		EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		world.spawnEntity(item);
	}
	super.breakBlock(world, pos, state);
}




@Override
public Class<TEPedestal> getTileEntityClass() {
	return TEPedestal.class;
}

@Nullable
@Override
public TEPedestal createTileEntity(World world, IBlockState state) {
	return new TEPedestal();
}


/*
@Override
public void registerBlockIcons(IIconRegister par1IconRegister)
{
	this.blockIcon = par1IconRegister.registerIcon("artifacts:pedestal_icon");
}

@Override
public TileEntity createNewTileEntity(World world, int meta) {
	return new TileEntityDisplayPedestal();
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
public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
{
	if(!world.isRemote) {
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof TileEntityDisplayPedestal && entityLivingBase instanceof EntityPlayer) {
			TileEntityDisplayPedestal ted = (TileEntityDisplayPedestal)te;
			EntityPlayerMP player = null;
			Iterator iterator = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();

			do
			{
				if (!iterator.hasNext())
				{
					player = null;
					break;
				}

				player = (EntityPlayerMP)iterator.next();
			}
			while (!player.getCommandSenderName().equalsIgnoreCase(entityLivingBase.getCommandSenderName()));

			if(player == null) {
				ted.ownerName = entityLivingBase.getCommandSenderName();
			}
			else {
				ted.ownerName = player.getCommandSenderName();
			}

			ted.rotation = ((int) ((MathHelper.floor_double((double)((player.rotationYaw) * 16.0F / 360.0F) + 0.5D) & 15) / 4)) * 90;
		}
	}
}

@Override
public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
{
	//Don't activate the block if the player is holding a key
	if(player.getHeldItem() != null && player.getHeldItem().getItem() == ItemPedestalKey.pedestalKeyItem) {
		return false;
	}

	world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.door_open", 1.0f, 1.2f);

	if (world.isRemote)
	{
		TileEntity te = world.getTileEntity(x, y, z);

		if(te instanceof TileEntityDisplayPedestal) {
			String ownerName = ((TileEntityDisplayPedestal) te).ownerName;
			if(ownerName != null && !ownerName.equals("") && !ownerName.equals(player.getCommandSenderName())) {
				Minecraft.getMinecraft().ingameGUI.func_110326_a(StatCollector.translateToLocal("misc.Owned by:") + " " + ownerName, false);
			}
		}
		return true;
	}
	else
	{
		player.openGui(DragonArtifacts.instance, 0, world, x, y, z);
		return true;
	}
}

@Override
public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
	dropItems(world, x, y, z);
	super.breakBlock(world, x, y, z, block, par6);
}

private void dropItems(World world, int x, int y, int z){
	Random rand = new Random();

	TileEntity tileEntity = world.getTileEntity(x, y, z);
	if (!(tileEntity instanceof IInventory)) {
		return;
	}
	IInventory inventory = (IInventory) tileEntity;

	for (int i = 0; i < inventory.getSizeInventory(); i++) {
		ItemStack item = inventory.getStackInSlot(i);

		if (item != null && item.stackSize > 0) {
			float rx = rand.nextFloat() * 0.8F + 0.1F;
			float ry = rand.nextFloat() * 0.8F + 0.1F;
			float rz = rand.nextFloat() * 0.8F + 0.1F;

			EntityItem entityItem = new EntityItem(world,
					x + rx, y + ry, z + rz,
					new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

			if (item.hasTagCompound()) {
				entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
			}

			float factor = 0.05F;
			entityItem.motionX = rand.nextGaussian() * factor;
			entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
			entityItem.motionZ = rand.nextGaussian() * factor;
			world.spawnEntityInWorld(entityItem);
			item.stackSize = 0;
		}
	}
}

@Override
public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z)
{
	TileEntity te = world.getTileEntity(x, y, z);
	TileEntityDisplayPedestal ted = (TileEntityDisplayPedestal)te;

	if((ted.ownerUUID.getLeastSignificantBits() == player.getUniqueID().getLeastSignificantBits() &&
							ted.ownerUUID.getMostSignificantBits() == player.getUniqueID().getMostSignificantBits()) ||
						ted.ownerName.equals(player.getCommandSenderName()) ||
						ted.ownerName == null || ted.ownerName.equals("") ||
						ted.ownerUUID == null || ted.ownerUUID.equals(new UUID(0,0))) {

		float f = this.getBlockHardness(world, x, y, z);
		return ForgeHooks.blockStrength(this, player, world, x, y, z);
	}
	else {
		return 0;
	}
}*/
}
