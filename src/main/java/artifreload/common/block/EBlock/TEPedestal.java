package artifreload.common.block.EBlock;

import javax.annotation.Nullable;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import artifreload.common.block.baseBlock.ClassAbstract.BlockTileEntity;
import jdk.nashorn.internal.ir.Block;


public class TEPedestal extends TileEntity {

private ItemStackHandler inventory = new ItemStackHandler(1);


@Override
public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	compound.setTag("inventory", inventory.serializeNBT());
	return super.writeToNBT(compound);
}

@Override
public void readFromNBT(NBTTagCompound compound) {
	inventory.deserializeNBT(compound.getCompoundTag("inventory"));
	super.readFromNBT(compound);
}

@Override
public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
	return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
}

@Nullable
@Override
public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
	return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
}





}
