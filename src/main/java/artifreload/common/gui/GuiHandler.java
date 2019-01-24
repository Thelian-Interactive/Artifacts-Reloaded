package artifreload.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.network.IGuiHandler;

import artifreload.common.block.EBlock.CPedestal;
import artifreload.common.block.EBlock.TEPedestal;
import artifreload.common.block.IBlock.IPedestal;
import artifreload.common.entity.TEDisplayPedestal;
import artifreload.common.gui.cont.PedestalGui;


public class GuiHandler implements IGuiHandler {

	public static final int PEDESTAL = 0;

@Override
public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	switch (ID) {
		case PEDESTAL:
			return new CPedestal(player.inventory, (TEPedestal)world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
	}
}

@Override
public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	switch (ID) {
		case PEDESTAL:
			return new PedestalGui(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		default:
			return null;
	}
}






	/*
@Override
public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
	//System.out.println("This occurrs (client)");
	if(id == 0) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TEDisplayPedestal){
			return new IPedestal(player.inventory, (TEDisplayPedestal) tileEntity);
		}
	}
	return null;
}

//returns an instance of the Gui you made earlier
@Override
public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
	//System.out.println("This occurrs (server)");
	if(id == 0) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TEDisplayPedestal){
			return new PedestalGui(player.inventory, (TEDisplayPedestal) tileEntity);
		}
	}
	return null;
}*/
}
