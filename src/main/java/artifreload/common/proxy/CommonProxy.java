package artifreload.common.proxy;

import net.minecraft.item.*;

import net.minecraftforge.common.MinecraftForge;

import artifreload.common.util.artifact.event.ServerEventHandler;
import artifreload.common.util.artifact.event.TickHandler;


public class CommonProxy {
public void registerRenders() {

}

public void registerTickHandlers() {

	MinecraftForge.EVENT_BUS.register(new TickHandler());

}

public void registerEventHandlers() {
	MinecraftForge.EVENT_BUS.register(new ServerEventHandler());
}


}

