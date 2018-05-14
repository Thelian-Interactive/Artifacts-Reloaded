package artifreload.common.gui.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;


public class RLaserSource implements ISimpleBlockRenderingHandler {

public int renderID;

public RenderLaserSource(int r) {
	renderID = r;
}

@Override
public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	renderWorldBlock(null, 0, 0, 0, block, modelID, renderer);
}

@Override
public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
	int l = 1;
	if(world != null)
		l = world.getBlockMetadata(x, y, z);
	IIcon laser = block.getIcon(0,0);
	Tessellator tessellator = Tessellator.instance;
	tessellator.setBrightness(15728880);
	tessellator.setColorOpaque_F(1, 1, 1);
	float f = 1F - 1F/128F;
	renderer.renderMaxX = 1;
	renderer.renderMinX = 0;
	renderer.renderMaxY = 1;
	renderer.renderMinY = 0;
	renderer.renderMaxZ = 1;
	renderer.renderMinZ = 0;
	switch(l&3) {
		case 0:
			renderer.renderFaceZPos(Blocks.ice, x, y, z-f, laser);
			break;
		case 1:
			renderer.renderFaceXNeg(Blocks.ice, x+f, y, z, laser);
			break;
		case 2:
			renderer.renderFaceZNeg(Blocks.ice, x, y, z+f, laser);
			break;
		case 3:
			renderer.renderFaceXPos(Blocks.ice, x-f, y, z, laser);
			break;
	}
	if(world != null)
		renderer.renderBlockAllFaces(BlockLaserBeam.instance, x, y, z);
	return true;
}

@Override
public boolean shouldRender3DInInventory(int modelId) {
	return false;
}

@Override
public int getRenderId() {
	return renderID;
}

}
