package artifreload.common.proxy;

import net.minecraft.item.*;
import net.minecraft.util.text.translation.I18n;

import net.minecraftforge.common.MinecraftForge;

import artifreload.common.util.artifact.event.ServerEventHandler;
import artifreload.common.util.artifact.event.TickHandler;


public class CommonProxy {

	public String localize(String unlocalized, Object... args) {
		return I18n.translateToLocalFormatted(unlocalized, args);
	}

	public void registerItemRenderer(Item item, int meta, String id) {
	}





	/*

public void registerTickHandlers() {

	MinecraftForge.EVENT_BUS.register(new TickHandler());
}

public void registerEventHandlers() {
	MinecraftForge.EVENT_BUS.register(new ServerEventHandler());
}
*/

}

