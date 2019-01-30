package artifreload.common.block.EBlock;

import javax.annotation.Nullable;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;




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
	NBTTagList nbtTagList = compound.getTagList("Items", 10);
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