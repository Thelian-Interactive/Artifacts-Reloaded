package artifreload.common.proxy;


import net.minecraft.client.resources.I18n;


public class ClientProxy extends CommonProxy {

@Override
public String localize(String unlocalized, Object... args) {
	return I18n.format(unlocalized, args);
}


















	/*
@Override
public void registerItemRenderer(Item item, int meta, String id) {
	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ModInfo.MOD_ID + ":" + id, "inventory"));
}

@SubscribeEvent
public static void registerBlocks(RegistryEvent.Register<Block> event) {
	ModBlocks.register(event.getRegistry());
}
















	/*
public static TextureAtlasSprite calendar;

@Override
public void registerRenders() {

	int r = RenderingRegistry.getNextAvailableRenderId();
	ISimpleBlockRenderingHandler handler = new RenderFakeBlock(r);
	RenderingRegistry.registerBlockHandler(handler);
	((BlockIllusionary)BlockIllusionary.instance).renderType = r;
//		GameRegistry.registerTileEntity(TileEntitySpikes.class, "artifacts.spiketrap");

	r = RenderingRegistry.getNextAvailableRenderId();
	handler = new RenderArrowTrap(r);
	RenderingRegistry.registerBlockHandler(handler);
	((BlockTrap)BlockTrap.instance).renderType = r;
//		GameRegistry.registerTileEntity(TileEntityTrap.class, "artifacts.arrowtrap");

//		r = RenderingRegistry.getNextAvailableRenderId();
//		handler = new RenderQuickSand(r);
//		RenderingRegistry.registerBlockHandler(handler);
//		((BlockQuickSand)BlockQuickSand.instance).renderType = r;

	r = RenderingRegistry.getNextAvailableRenderId();
	handler = new RenderCoverPlate(r);
	RenderingRegistry.registerBlockHandler(handler);
	((BlockCoverPlate)BlockCoverPlate.instance).renderType = r;
	RenderingRegistry.registerEntityRenderingHandler(EntityClayGolem.class, new RenderClayGolem());

	TileEntitySpecialRenderer render = new TESwordRenderer();
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySword.class, render);
	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockSword.instance), new ItemRenderPedestal(render, new TileEntitySword()));

	render = new PedestalRenderer();
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayPedestal.class, render);
	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockPedestal.instance), new ItemRenderPedestal(render, new TileEntityDisplayPedestal()));

	render = new SpikesRenderer();
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpikes.class, render);
	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockSpikes.instance), new ItemRenderPedestal(render, new TileEntitySpikes()));

	r = RenderingRegistry.getNextAvailableRenderId();
	handler = new RenderLaserBeam(r);
	RenderingRegistry.registerBlockHandler(handler);
	((BlockLaserBeam)BlockLaserBeam.instance).renderID = r;

	r = RenderingRegistry.getNextAvailableRenderId();
	handler = new RenderLaserSource(r);
	RenderingRegistry.registerBlockHandler(handler);
	((BlockLaserBeamSource)BlockLaserBeamSource.instance).renderID = r;

	if(Loader.isModLoaded("antiqueatlas")) {
//			AtlasAPI.getTileAPI().setTexture("wizardtower", new ResourceLocation("artifacts:textures/gui/tower.png"));
	}
}

public void registerEventHandlers() {
	super.registerEventHandlers();
	MinecraftForge.EVENT_BUS.register(new ArtifactClientEventHandler());
}*/
}
