package artifreload.common.util.artifact.event;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;

import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;


public class ClientEventHandler {


public static boolean cloaked = false;
private static float fogMin = 10.0f;
private static float fogCurrent = 192.0f;

@SubscribeEvent
public void onFogRender(EntityViewRenderEvent.FogDensity event) {
	//Handle player "cloaking" with Obscurity component.
	Minecraft mc = Minecraft.getMinecraft();
	if((cloaked && !mc.thePlayer.isPotionActive(Potion.invisibility)) || mc.thePlayer.isPotionActive(Potion.blindness)) {
		cloaked = false;
	}

	//Make the fog max depend on the render distance.
	float fogMax = mc.gameSettings.renderDistanceChunks * 16;
	if(fogCurrent > fogMax) {
		fogCurrent = fogMax;
	}

	//Check if the effect should or shouldn't be applied (it shouldn't if the player already has a strong fog effect).
	if(!mc.thePlayer.isPotionActive(Potion.blindness) && !mc.thePlayer.isInsideOfMaterial(Material.water) && !mc.thePlayer.isInsideOfMaterial(Material.lava)) {
		//Note to self - this didn't work that well: && !BlockQuickSand.headInQuicksand(mc.theWorld, MathHelper.floor_double(mc.thePlayer.posX), MathHelper.floor_double(mc.thePlayer.posY + mc.thePlayer.getEyeHeight()), MathHelper.floor_double(mc.thePlayer.posZ), mc.thePlayer)

		//If the player is cloaked, render the fog coming in. It stops when it reaches fogMin.
		if(cloaked) {
			event.setCanceled(true);
			if(fogCurrent > fogMin + 0.01) {
				fogCurrent -= (fogCurrent-fogMin)/fogMax * 0.3f;
			}
		}
		//Otherwise render the fog going out, then stop rendering when it reaches fogMax.
		else {
			if(fogCurrent < fogMax) {
				event.setCanceled(true);

				fogCurrent += 0.1f;
			}
		}
	}
	else {
		//"Insta-move" the fog if it isn't rendering.
		if(cloaked) {
			fogCurrent = fogMin;
		}
		else {
			fogCurrent = fogMax;
		}
	}

	//Render the fog.
	if(event.isCanceled()) {
		GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);

		GL11.glFogf(GL11.GL_FOG_START, fogCurrent * 0.25F);
		GL11.glFogf(GL11.GL_FOG_END, fogCurrent);

		if (GLContext.getCapabilities().GL_NV_fog_distance)
		{
			GL11.glFogi(34138, 34139);
		}
	}
}
}

