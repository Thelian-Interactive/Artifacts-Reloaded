package artifreload.common.util.registry;



import net.minecraft.block.Block;


import net.minecraftforge.fml.common.*;

import artifreload.common.block.EBlock.*;
import artifreload.common.block.IBlock.*;
import artifreload.common.util.artifact.ModInfo;


@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
public final class ModRegistry {


	public static final Block antibuilder = new IAntibuilder();
	public static final Block coverplate = new ICoverplate();
	public static final Block laser = new ILaser();
	public static final Block lasersrc = new ILaserSrc();
	public static final Block pedestal = new IPedestal();
	public static final Block pressureplate = new IPressureplate();
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
































}
