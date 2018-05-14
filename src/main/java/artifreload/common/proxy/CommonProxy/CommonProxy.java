package artifreload.common.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

import artifreload.common.util.event.ServerEventHandler;
import artifreload.common.util.event.TickHandler;


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

