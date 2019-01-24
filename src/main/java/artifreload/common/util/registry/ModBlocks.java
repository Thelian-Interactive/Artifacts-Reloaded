package artifreload.common.util.registry;



import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.*;

import artifreload.common.block.EBlock.TEPedestal;
import artifreload.common.block.EBlock.TETrap;
import artifreload.common.block.IBlock.*;
import artifreload.common.block.baseBlock.BlockBase;
import artifreload.common.util.artifact.ModInfo;


@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
public final class ModBlocks {

	public static final Block antibuilder = new IAntibuilder();
	public static final Block coverplate = new ICoverplate();
	public static final Block laser = new ILaser();
	public static final Block lasersrc = new ILaserSrc();
	public static final Block ipedestal = new IPedestal();
	public static final Block pressureplate = new IPressurePlate();
	public static final Block pseudocoverplate = new IPseudoCoverplate();
	public static final Block pseudotrap = new IPseudoTrap();
	public static final Block fakesword = new ISword();
	public static final Block atrap = new ITrap();
	public static final Block wallplate = new IWallplate();
	public static final Block illusionary = new IBlockIllusionary();
	public static final Block moveable = new IBlockMoveable();
	public static final Block invisbedrock = new IInvisBedrock();
	public static final Block invisblock = new IInvisBlock();
	public static final Block Blight = new ILight();
	public static final Block pseudoillusionary = new IPseudoIllusionary();
	public static final Block Bquicksand = new IQuicksand();
	public static final Block solidair = new ISolidAir();
	public static final Block Bspikes = new ISpikes();
	public static final TileEntity tepedestal = new TEPedestal();
	public static final TileEntity tetrap = new TETrap();

    public static void registerItemBlocks(IForgeRegistry<Item> evt) {

    }


    public static void register(IForgeRegistry<Block> evt) {

               evt.register(antibuilder);
               evt.register(coverplate);
               evt.register(laser);
               evt.register(lasersrc);
               evt.register(ipedestal);
               evt.register(pressureplate);
               evt.register(pseudocoverplate);
               evt.register(pseudotrap);
               evt.register(fakesword);
               evt.register(atrap);
               evt.register(wallplate);
               evt.register(illusionary);
               evt.register(moveable);
               evt.register(invisbedrock);
               evt.register(invisblock);
               evt.register(Blight);
               evt.register(pseudoillusionary);
               evt.register(Bquicksand);
               evt.register(solidair);
               evt.register(Bspikes);
           GameRegistry.registerTileEntity(TEPedestal.class, "tepedestal");
           GameRegistry.registerTileEntity(TETrap.class, "tetrap");














}


public static void registerModels() {

}






























}
