package artifreload.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.network.IGuiHandler;

import artifreload.common.block.IBlock.IPedestal;
import artifreload.common.entity.TEDisplayPedestal;
import artifreload.common.gui.cont.PedestalGui;


public class GuiHandler implements IGuiHandler {

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
}
}
