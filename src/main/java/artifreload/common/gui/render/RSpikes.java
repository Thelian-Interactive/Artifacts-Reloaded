package artifreload.common.gui.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;


public class RSpikes extends TileEntitySpecialRenderer {

private SpikesModel spikes = new SpikesModel();

@Override
public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
	spikes.render(tileentity, d0, d1, d2);
}
}

