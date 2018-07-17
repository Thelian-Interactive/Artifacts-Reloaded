package artifreload.common;

import java.util.Arrays;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;


import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import artifreload.api.ArtifactAPI;
import artifreload.common.block.IBlock.*;
import artifreload.common.gui.*;
import artifreload.common.item.dusts.OrichalcumDust;
import artifreload.common.util.artifact.Factory.*;
import artifreload.common.item.*;
import artifreload.common.item.ArtifactArmour;
import artifreload.common.proxy.CommonProxy;
import artifreload.common.util.artifact.ModInfo;
import artifreload.common.world.structure.PlaceTraps;


@Mod(
		modid                    = ModInfo.MOD_ID,
		name                     = ModInfo.MOD_NAME,
		version                  = ModInfo.MOD_VERSION,
		dependencies             = "required-after:Forge@[" + ModInfo.MCF_MINVER + "," + ModInfo.MCF_MAXVER + ")" + ModInfo.MOD_DEPS,
		acceptableRemoteVersions = "*"
)
public class DragonArtifacts{
	@Instance("Artifacts")
	public static DragonArtifacts instance;
	public static SimpleNetworkWrapper artifactNetworkWrapper;
	public static boolean renderNamesInPedestals = false;
	public static boolean renderInvis = false;
	public static boolean boundingInvis = true;
	public static PlaceTraps worldGen;
	public static boolean mystcraftLoaded = false;
	public static boolean airwalkDebug = false;

	public static boolean baublesLoaded = false;
	public static boolean baublesMustBeEquipped = true;

	@SidedProxy(clientSide = "artifreload.common.proxy.ClientProxy", serverSide = "artifreload.common.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs ArtifactBlocksTab = new CreativeTabs("Artifact Blocks") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(StructureGenerator.instance);
		}
	};

	public static CreativeTabs ArtifactItemsTab = new CreativeTabs("Artifact Items") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Artifact.instance);
		}
	};

@Mod.EventBusSubscriber
public static class RegistrationHandler {

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

	}

}


	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		String armorIDh1 = "artifact_leather_helmet";
		String armorIDh2 = "artifact_chainmail_helmet";
		String armorIDh3 = "artifact_iron_helmet";
		String armorIDh4 = "artifact_golden_helmet";
		String armorIDh5 = "artifact_diamond_helmet";

		String armorIDc1 = "artifact_leather_chestplate";
		String armorIDc2 = "artifact_chainmail_chestplate";
		String armorIDc3 = "artifact_iron_chestplate";
		String armorIDc4 = "artifact_golden_chestplate";
		String armorIDc5 = "artifact_diamond_chestplate";

		String armorIDl1 = "artifact_leather_leggings";
		String armorIDl2 = "artifact_chainmail_leggings";
		String armorIDl3 = "artifact_iron_leggings";
		String armorIDl4 = "artifact_golden_leggings";
		String armorIDl5 = "artifact_diamond_leggings";

		String armorIDb1 = "artifact_leather_boots";
		String armorIDb2 = "artifact_chainmail_boots";
		String armorIDb3 = "artifact_iron_boots";
		String armorIDb4 = "artifact_golden_boots";
		String armorIDb5 = "artifact_diamond_boots";
		String artifactID = "artifact";
		String tb1 = "trapblade_wood";
		String tb2 = "trapblade_stone";
		String tb3 = "trapblade_iron";
		String tb4 = "trapblade_gold";
		String tb5 = "trapblade_diamond";
		String orichalcumID = "dust_orichalcum";
		String calendarID = "lunar_calendar";

		String structureGenID = "structure_generator";
		String pedestalKeyID = "pedestal_key";

		config.addCustomCategoryComment("WorldGen", "By default this mod alters worldgen slightly, adding more and different traps to\npyramids, temples, and strongholds as well as quicksand 'lakes'.\nThese may be disabled individually.\nTo disable the towers, set their weights to 0.");
		PlaceTraps.genPyramids = config.get("WorldGen","Pyramids ",true).getBoolean(true);
		PlaceTraps.genTemples = config.get("WorldGen","Temples ",true).getBoolean(true);
		PlaceTraps.genStrongholds = config.get("WorldGen","Strongholds ",true).getBoolean(true);
		PlaceTraps.genMystcraftLibraries = config.get("WorldGen","Mystcraft Libraries",true).getBoolean(true);
		PlaceTraps.genQuicksand = config.get("WorldGen","Quicksand Pits",true).getBoolean(true);
		PlaceTraps.weightTower1 = config.get("WorldGen","Small Wizard Tower Weight", 5).getInt();
		if(PlaceTraps.weightTower1 < 0) PlaceTraps.weightTower1 = 0;
		PlaceTraps.weightTower1A = config.get("WorldGen","Small Wizard Tower Ruins Weight", 5).getInt();
		if(PlaceTraps.weightTower1A < 0) PlaceTraps.weightTower1A = 0;
		PlaceTraps.weightTower2 = config.get("WorldGen","Medium Wizard Tower Weight", 4).getInt();
		if(PlaceTraps.weightTower2 < 0) PlaceTraps.weightTower2 = 0;
		PlaceTraps.weightTower2A = config.get("WorldGen","Medium Wizard Tower Ruins Weight", 4).getInt();
		if(PlaceTraps.weightTower2A < 0) PlaceTraps.weightTower2A = 0;
		PlaceTraps.weightTower3 = config.get("WorldGen","Large Wizard Tower (with anti-builders) Weight", 3).getInt();
		if(PlaceTraps.weightTower3 < 0) PlaceTraps.weightTower3 = 0;
		PlaceTraps.weightTower3A = config.get("WorldGen","Large Wizard Tower Ruins Weight", 3).getInt();
		if(PlaceTraps.weightTower3A < 0) PlaceTraps.weightTower3A = 0;
		Property conf = config.get("WorldGen", "Wizard Tower Rarity", 30);
		conf.comment = "The lower the number, the more wizard towers will generate. Minimum is 1.";
		PlaceTraps.towerRarity = conf.getInt();
		if(PlaceTraps.towerRarity <= 0) {
			PlaceTraps.towerRarity = 1;
			conf.set(1);
		}
		conf = config.get("WorldGen", "Quicksand Rarity", 60);
		conf.comment = "The lower the number, the more quicksand pits will generate. Minimum is 1.";
		PlaceTraps.quicksandRarity = conf.getInt();
		if(PlaceTraps.quicksandRarity <= 0) {
			PlaceTraps.quicksandRarity = 1;
			conf.set(1);
		}
		conf = config.get("WorldGen","Dimension Whitelist Enable",false);
		conf.comment = "Enables the whitelist for worldgen.  If enabled, world gen objects will only generate in whitelisted dimensions.";
		PlaceTraps.whitelistEnabled = conf.getBoolean(false);
		conf = config.get("WorldGen","Dimension Blacklist Enable",false);
		conf.comment = "Enables the blacklist for worldgen.  If enabled, world gen objects will never generate in blacklisted dimensions.\nBlacklist will override whitelist.  -1 and 1 (Nether and End) are always blacklisted.";
		PlaceTraps.blacklistEnabled = conf.getBoolean(false);
		conf = config.get("baubles", "Artifacts must be equipped when Baubles installed?", true);
		conf.comment = "If true, if Baubles is installed, the continuous effects of artifacts which can go in the\nBaubles slots will only work when the artifacts are in the slots.";
		baublesMustBeEquipped = conf.getBoolean(true);


		Property cw = config.get("WorldGen","Dimension Whitelist List", new int[] {0});
		Property cb = config.get("WorldGen","Dimension Blacklist List", new int[] {-1,1});
		PlaceTraps.whitelist = cw.getIntList();
		PlaceTraps.blacklist = cb.getIntList();

		Arrays.sort(PlaceTraps.whitelist);
		Arrays.sort(PlaceTraps.blacklist);

		String a = Arrays.toString(PlaceTraps.whitelist);
		String whitestring[]=a.substring(1,a.length()-1).split(", ");
		String b = Arrays.toString(PlaceTraps.blacklist);
		String blackstring[]=b.substring(1,b.length()-1).split(", ");

		cw.set(whitestring);
		cb.set(blackstring);

		PlaceTraps.iDontLikeAntibuilders = ! config.get("WorldGen","Use Antibuilders",true).getBoolean(true);

		int golemID = config.get("Entities", "Golem ID", EntityRegistry.findGlobalUniqueEntityId()).getInt();

		ConfigCategory longNames = config.getCategory("general");
		longNames.setComment("These settings dictate how item names are displayed.");
		Property enchName = config.get("general","Enchantments",true);
		Property matName = config.get("general","Material",true);
		Property adjName = config.get("general","Adjectives",true);
		Property renderNames = config.get("rendering","RenderNames",false);
		renderNames.comment = "Set to false to disable rendering of item names on display pedesals";
		renderNamesInPedestals = renderNames.getBoolean(true);

		ConfigCategory renderConf = config.getCategory("rendering");
		renderConf.setComment("Determines some options on invisible blocks");
		conf = config.get("rendering", "RenderInvis", false);
		conf.comment = "Set this to true to render invisible blocks.  Even when false, they will render in your inventory.";
		renderInvis = conf.getBoolean(false);

		conf = config.get("rendering", "BoundInvis", true);
		conf.comment = "Set this to false to disable bounding boxes on invisible blocks.\nALERT: without bounding boxes you will not be able to destroy them!\nThis is only recommended for playing adventure maps.";
		boundingInvis = conf.getBoolean(true);

		conf = config.get("rendering", "TrapSwordPackage", "artifacts");
		conf.comment = "Sets the package the icons should be pulled from.\nDefault is 'artifacts' which pulls the default icons.\nNot sure where this points otherwise.";
		String bladePackage = conf.getString();
		conf = config.get("rendering", "TrapSwordIcon", "blade");
		conf.comment = "Sets the rendering type for swords in arrow traps.\nDefault is 'blade' which attempts to maintain the jaggy nature of the vanilla sword.\n'blade_alt' uses a smaller texture, maintaining strait lines and mirroring the vanilla item as closely as possible.\nAdditional textures can be created and set here as well, if desired, without replacing existing textures.";
		String bladeRender = conf.getString();
		airwalkDebug = config.get("debug", "Enables the 'true' spam in console/logs when a player is airwalking", false).getBoolean(false);


		String lightID = "light_block";
		String pedID = "pedestal";
		String invis2ID = "invisible_bedrock";
		String spikesID = "spike_block";
		String arrowSlotID = "arrow_trap";
		String coverplateID = "cover_plate";
		String quickID = "quicksand";
		String pseudoATID = "arrow_trap_item";
		String pseudoCPID = "cover_plate_item";
		String pseudoFBID = "fake_block_item";
		String wallplateID = "wall_pressure_plate";
		String cwallplateID = "camouflaged_wall_pressure_plate";
		String owallplateID = "obsidian_wall_pressure_plate";
		String cowallplateID = "camouflaged_obsidian_wall_pressure_plate";
		String wwallplateID = "wooden_wall_pressure_plate";
		String cwwallplateID = "camouflaged_wooden_wall_pressure_plate";
		String invisppID = "invisible_pressure_plate";
		String oinvisppID = "obsidian_invisible_pressure_plate";
		String cppID = "camouflaged_pressure_plate";
		String oppID = "obsidian_pressure_plate";
		String coppID = "camouflaged_obsidian_pressure_plate";
		String cwppID = "camouflaged_wooden_pressure_plate";
		String fakeID = "illusionary_block";
		String invisID = "invisible_block";
		String teSwordID = "fake_tile_entity";
		String floatID = "floating_block";
		String antiID = "anti_builder";
		String ignoreID = "anti_anti_builder_stone";
		String laserSourceID = "laser_source";
		String laserBeamID = "laser_beam";

		config.addCustomCategoryComment("spawning", "These settings alter the spawning rarity of artifacts in the various chests.\nLower is rarer, higher is more common.  By default pyramids and temples generate ~2 total.\nCross-Mod Treasure String ('ProceeduralGeneration') is for inter-mod treasure gen.");
		int dungRare = config.get("spawning","Dungeons",0).getInt(0);
		int pyrRare = config.get("spawning","Pyramids",4).getInt(4);
		int tempRare = config.get("spawning","Temples",8).getInt(8);
		int strong1Rare = config.get("spawning","Stronghold_Library",6).getInt(6);
		int strong2Rare = config.get("spawning","Stronghold_Corridor",1).getInt(1);
		int strong3Rare = config.get("spawning","Stronghold_Crossing",3).getInt(3);
		int mineRare = config.get("spawning","Mineshafts",0).getInt(0);
		int villRare = config.get("spawning","Blacksmith",1).getInt(1);
		int wizRare = config.get("spawning","WizTowers",10).getInt(10);
		int procRare = config.get("spawning","crossModTreasureString_ProceeduralGeneration",5).getInt(5);
		config.save();
		ArtifactAPI.artifacts = new FactoryArtifact();
		ArtifactAPI.itemicons = new FactoryItemIcons();
		ArtifactAPI.traps = new FactoryTrapBehaviors();

		ArtifactAPI.artifacts.registerUpdateNBTKey("orePingDelay");
		ArtifactAPI.artifacts.registerUpdateNBTKey("resCooldown");
		ArtifactAPI.artifacts.registerUpdateNBTKey("medkitDelay");
		ArtifactAPI.artifacts.registerUpdateNBTKey("adrenDelay");

		Artifact.instance = new Artifact().setUnlocalizedName(artifactID);
		ArtifactArmour.hleather = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.LEATHER, 0, 2, 0).setUnlocalizedName(armorIDh1);
		ArtifactArmour.hchain = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.CHAIN, 1, 2, 0).setUnlocalizedName(armorIDh2);
		ArtifactArmour.hiron = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.IRON, 2, 2, 0).setUnlocalizedName(armorIDh3);
		ArtifactArmour.hgold = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.GOLD, 4, 2, 0).setUnlocalizedName(armorIDh4);
		ArtifactArmour.hdiamond = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.DIAMOND, 3, 2, 0).setUnlocalizedName(armorIDh5);
		ArtifactArmour.bleather = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.LEATHER, 0, 3, 3).setUnlocalizedName(armorIDb1);
		ArtifactArmour.bchain = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.CHAIN, 1, 3, 3).setUnlocalizedName(armorIDb2);
		ArtifactArmour.biron = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.IRON, 2, 3, 3).setUnlocalizedName(armorIDb3);
		ArtifactArmour.bgold = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.GOLD, 4, 3, 3).setUnlocalizedName(armorIDb4);
		ArtifactArmour.bdiamond = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.DIAMOND, 3, 3, 3).setUnlocalizedName(armorIDb5);
		ArtifactArmour.cleather = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.LEATHER, 0, 4, 1).setUnlocalizedName(armorIDc1);
		ArtifactArmour.cchain = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.CHAIN, 1, 4, 1).setUnlocalizedName(armorIDc2);
		ArtifactArmour.ciron = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.IRON, 2, 4, 1).setUnlocalizedName(armorIDc3);
		ArtifactArmour.cgold = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.GOLD, 4, 4, 1).setUnlocalizedName(armorIDc4);
		ArtifactArmour.cdiamond = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.DIAMOND, 3, 4, 1).setUnlocalizedName(armorIDc5);
		ArtifactArmour.lleather = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.LEATHER, 0, 5, 2).setUnlocalizedName(armorIDl1);
		ArtifactArmour.lchain = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.CHAIN, 1, 5, 2).setUnlocalizedName(armorIDl2);
		ArtifactArmour.liron = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.IRON, 2, 5, 2).setUnlocalizedName(armorIDl3);
		ArtifactArmour.lgold = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.GOLD, 4, 5, 2).setUnlocalizedName(armorIDl4);
		ArtifactArmour.ldiamond = (ArtifactArmour) new ArtifactArmour(ItemArmor.ArmorMaterial.DIAMOND, 3, 5, 2).setUnlocalizedName(armorIDl5);
		OrichalcumDust.instance = new OrichalcumDust().setUnlocalizedName(orichalcumID);
		Calendar.instance = new Calendar().setUnlocalizedName(calendarID);

		ArtifactArmour.setupArrays();

		FakeSword.wood = new FakeSword(Item.ToolMaterial.WOOD, bladePackage+":wood_"+bladeRender).setUnlocalizedName(tb1);
		FakeSword.stone = new FakeSword(Item.ToolMaterial.STONE, bladePackage+":stone_"+bladeRender).setUnlocalizedName(tb2);
		FakeSword.iron = new FakeSword(Item.ToolMaterial.IRON, bladePackage+":iron_"+bladeRender).setUnlocalizedName(tb3);
		FakeSword.gold = new FakeSword(Item.ToolMaterial.GOLD, bladePackage+":gold_"+bladeRender).setUnlocalizedName(tb4);
		FakeSword.diamond = new FakeSword(Item.ToolMaterial.DIAMOND, bladePackage+":diamond_"+bladeRender).setUnlocalizedName(tb5);

		ArtifactArmour.doEnchName = Artifact.doEnchName = enchName.getBoolean(true);
		ArtifactArmour.doMatName = Artifact.doMatName = matName.getBoolean(true);
		ArtifactArmour.doAdjName = Artifact.doAdjName = adjName.getBoolean(true);

		StructureGenerator.structureGenItem = new StructureGenerator().setUnlocalizedName(structureGenID);
		PedestalKey.pedestalKeyItem = new PedestalKey().setUnlocalizedName(pedestalKeyID).setCreativeTab(tabGeneral);

		IAntibuilder.instance = new IAntibuilder().setBlockName(antiID);
		ICoverplate.instance = new ICoverplate().setBlockName(coverplateID);
		IBlockIllusionary.instance = new IBlockIllusionary().setBlockName(fakeID);
		IInvisBedrock.instance = new IInvisBedrock().setBlockName(invis2ID);
		IInvisBlock.instance = new IInvisBlock().setBlockName(invisID);
		IPressureplate.invisStone = new IPressureplate("Invisible Pressure Plate", Material.circuits, BlockPressurePlate.Sensitivity.mobs, true, false).setBlockName(invisppID);
		IPressureplate.camoStone = new IPressureplate("Camo Pressure Plate", Material.circuits, BlockPressurePlate.Sensitivity.mobs, false, true).setBlockName(cppID);
		IPressureplate.invisObsidian = new IPressureplate("Invisible Obsidiplate", Material.circuits, BlockPressurePlate.Sensitivity.players, true, false).setBlockName(oinvisppID);
		IPressureplate.obsidian = new IPressureplate("Obsidiplate", Material.circuits, BlockPressurePlate.Sensitivity.players, false, false).setBlockName(oppID);
		IPressureplate.camoObsidian = new IPressureplate("Camo Obsidiplate", Material.circuits, BlockPressurePlate.Sensitivity.players, false, true).setBlockName(coppID);
		IPressureplate.camoWood = new IPressureplate("Camo Wooden Pressure Plate", Material.circuits, BlockPressurePlate.Sensitivity.everything, false, true).setBlockName(cwppID);
		ILaser.instance = new ILaser().setBlockName(laserBeamID);
		ILaserSrc.instance = (ILaserSrc) new ILaserSrc().setBlockName(laserSourceID);
		ILight.instance = new ILight().setBlockName(lightID);
		IPedestal.instance = (IPedestal) new IPedestal().setBlockName(pedID);
		IQuicksand.instance = new IQuicksand().setBlockName(quickID);
		ISolidAir.instance = new ISolidAir().setBlockName(floatID);
		ISpikes.instance = new ISpikes().setBlockName(spikesID);
		IBlockMoveable.instance = new IBlockMoveable().setBlockName(ignoreID);
		ISword.instance = new ISword().setBlockName(teSwordID);
		ITrap.instance = new ITrap().setBlockName(arrowSlotID);
		IWallplate.stone = new IWallplate("Wallplate", Material.circuits, BlockPressurePlate.Sensitivity.mobs, false).setBlockName(wallplateID);
		IWallplate.camoStone = new IWallplate("Camo Wallplate", Material.circuits, BlockPressurePlate.Sensitivity.mobs, true).setBlockName(cwallplateID);
		IWallplate.obsidian = new IWallplate("Wall Obsidiplate", Material.circuits, BlockPressurePlate.Sensitivity.players, false).setBlockName(owallplateID);
		IWallplate.camoObsidian = new IWallplate("Camo Wall Obsidiplate", Material.circuits, BlockPressurePlate.Sensitivity.players, true).setBlockName(cowallplateID);
		IWallplate.wood = new IWallplate("Wood Wallplate", Material.circuits, BlockPressurePlate.Sensitivity.everything, false).setBlockName(wwallplateID);
		IWallplate.camoWood = new IWallplate("Camo Wood Wallplate", Material.circuits, BlockPressurePlate.Sensitivity.everything, true).setBlockName(cwwallplateID);
		IPseudoIllusionary.instance = new IPseudoIllusionary().setBlockName(pseudoFBID);
		IPseudoTrap.instance = new IPseudoTrap().setBlockName(pseudoATID);
		IPseudoCoverplate.instance = new IPseudoCoverplate().setBlockName(pseudoCPID);

		GameRegistry.registerBlock(IAntibuilder.instance, antiID);
		GameRegistry.registerBlock(ICoverplate.instance, coverplateID);
		GameRegistry.registerBlock(IBlockIllusionary.instance, fakeID);
		GameRegistry.registerBlock(IInvisBedrock.instance, invis2ID);
		GameRegistry.registerBlock(IInvisibleBlock.instance, invisID);
		GameRegistry.registerBlock(IPressureplate.invisStone, invisppID);
		GameRegistry.registerBlock(IPressureplate.invisObsidian, oinvisppID);
		GameRegistry.registerBlock(IPressureplate.obsidian, oppID);
		GameRegistry.registerBlock(IPressureplate.camoWood, cwppID);
		GameRegistry.registerBlock(IPressureplate.camoStone, cppID);
		GameRegistry.registerBlock(IPressureplate.camoObsidian, coppID);
		GameRegistry.registerBlock(ILaser.instance, laserBeamID);
		GameRegistry.registerBlock(ILaserSrc.instance, laserSourceID);
		GameRegistry.registerBlock(ILight.instance, lightID);
		GameRegistry.registerBlock(IPedestal.instance, pedID);
		GameRegistry.registerBlock(IQuicksand.instance, quickID);
		GameRegistry.registerBlock(ISolidAir.instance, floatID);
		GameRegistry.registerBlock(ISpikes.instance, spikesID);
		GameRegistry.registerBlock(IBlockMoveable.instance, ignoreID);
		GameRegistry.registerBlock(ISword.instance, teSwordID);
		GameRegistry.registerBlock(ITrap.instance, arrowSlotID);
		GameRegistry.registerBlock(IWallplate.wood, wwallplateID);
		GameRegistry.registerBlock(IWallplate.stone, wallplateID);
		GameRegistry.registerBlock(IWallplate.obsidian, owallplateID);
		GameRegistry.registerBlock(IWallplate.camoWood, cwwallplateID);
		GameRegistry.registerBlock(IWallplate.camoStone, cwallplateID);
		GameRegistry.registerBlock(IWallplate.camoObsidian, cowallplateID);
		GameRegistry.registerBlock(IPseudoIllusionary.instance, pseudoFBID);
		GameRegistry.registerBlock(IPseudoTrap.instance, pseudoATID);
		GameRegistry.registerBlock(IPseudoCoverplate.instance, pseudoCPID);

		GameRegistry.registerItem(Artifact.instance, artifactID);
		GameRegistry.registerItem(ArtifactArmour.hleather, armorIDh1);
		GameRegistry.registerItem(ArtifactArmour.hchain, armorIDh2);
		GameRegistry.registerItem(ArtifactArmour.hiron, armorIDh3);
		GameRegistry.registerItem(ArtifactArmour.hgold, armorIDh4);
		GameRegistry.registerItem(ArtifactArmour.hdiamond, armorIDh5);
		GameRegistry.registerItem(ArtifactArmour.cleather, armorIDc1);
		GameRegistry.registerItem(ArtifactArmour.cchain, armorIDc2);
		GameRegistry.registerItem(ArtifactArmour.ciron, armorIDc3);
		GameRegistry.registerItem(ArtifactArmour.cgold, armorIDc4);
		GameRegistry.registerItem(ArtifactArmour.cdiamond, armorIDc5);
		GameRegistry.registerItem(ArtifactArmour.lleather, armorIDl1);
		GameRegistry.registerItem(ArtifactArmour.lchain, armorIDl2);
		GameRegistry.registerItem(ArtifactArmour.liron, armorIDl3);
		GameRegistry.registerItem(ArtifactArmour.lgold, armorIDl4);
		GameRegistry.registerItem(ArtifactArmour.ldiamond, armorIDl5);
		GameRegistry.registerItem(ArtifactArmour.bleather, armorIDb1);
		GameRegistry.registerItem(ArtifactArmour.bchain, armorIDb2);
		GameRegistry.registerItem(ArtifactArmour.biron, armorIDb3);
		GameRegistry.registerItem(ArtifactArmour.bgold, armorIDb4);
		GameRegistry.registerItem(ArtifactArmour.bdiamond, armorIDb5);
		GameRegistry.registerItem(OrichalcumDust.instance, orichalcumID);
		GameRegistry.registerItem(Calendar.instance, calendarID);
		GameRegistry.registerItem(FakeSword.wood, tb1);
		GameRegistry.registerItem(FakeSword.stone, tb2);
		GameRegistry.registerItem(FakeSword.iron, tb3);
		GameRegistry.registerItem(FakeSword.gold, tb4);
		GameRegistry.registerItem(FakeSword.diamond, tb5);
		GameRegistry.registerItem(StructureGenerator.structureGenItem, structureGenID);
		GameRegistry.registerItem(PedestalKey.pedestalKeyItem, pedestalKeyID);

		worldGen = new PlaceTraps();//pyrm, temp, strn, lib, quik, towers, usewhite, useblack, white, black, useAntibuild);
		GameRegistry.registerWorldGenerator(worldGen, 10);

		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, Math.max(6, wizRare)));
		//ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, 2));//would a second, rarer chance, be any different than a single large chance?
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(Items.enchanted_book, 0, 1, 1, 5));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(Items.diamond, 0, 2, 5, 3));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(Items.gold_nugget, 0, 3, 7, 5));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(ItemOrichalcumDust.instance, 0, 1, 1, 3));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(ItemOrichalcumDust.instance, 1, 1, 1, 3));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(ItemOrichalcumDust.instance, 2, 1, 1, 3));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(ItemOrichalcumDust.instance, 3, 1, 1, 3));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(ItemOrichalcumDust.instance, 4, 1, 1, 3));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(Items.experience_bottle, 0, 1, 1, 2));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomChestContent(ItemOrichalcumDust.instance, 0, 4, 12, 2));

		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.hcloth, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.ccloth, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.lcloth, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.bcloth, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.hchain, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.cchain, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.lchain, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.bchain, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.hiron, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.ciron, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.liron, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.biron, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.hgold, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.cgold, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.lgold, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.bgold, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.hdiamond, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.cdiamond, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.ldiamond, 0, 1, 1, procRare));
		ChestGenHooks.getInfo("ProceeduralGeneration").addItem(new WeightedRandomArtifact(ItemArtifactArmor.bdiamond, 0, 1, 1, procRare));

		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, pyrRare));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, tempRare));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, strong1Rare));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, strong2Rare));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, strong3Rare));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, villRare));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, mineRare));
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomArtifact(ItemArtifact.instance, 0, 1, 1, dungRare));

		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomArtifact(ItemArtifactArmor.hcloth, 0, 1, 1, Math.max(6, wizRare)));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomArtifact(ItemArtifactArmor.ccloth, 0, 1, 1, Math.max(6, wizRare)));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomArtifact(ItemArtifactArmor.lcloth, 0, 1, 1, Math.max(6, wizRare)));
		ChestGenHooks.getInfo("A_WIZARD_DID_IT").addItem(new WeightedRandomArtifact(ItemArtifactArmor.bcloth, 0, 1, 1, Math.max(6, wizRare)));

		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.hchain, 0, 1, 1, dungRare));
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.cchain, 0, 1, 1, dungRare));
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.lchain, 0, 1, 1, dungRare));
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.bchain, 0, 1, 1, dungRare));

		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomArtifact(ItemArtifactArmor.hiron, 0, 1, 1, villRare));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomArtifact(ItemArtifactArmor.ciron, 0, 1, 1, villRare));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomArtifact(ItemArtifactArmor.liron, 0, 1, 1, villRare));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomArtifact(ItemArtifactArmor.biron, 0, 1, 1, villRare));

		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.hgold, 0, 1, 1, pyrRare));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.cgold, 0, 1, 1, pyrRare));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.lgold, 0, 1, 1, pyrRare));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.bgold, 0, 1, 1, pyrRare));

		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomArtifact(ItemArtifactArmor.hdiamond, 0, 1, 1, strong2Rare));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomArtifact(ItemArtifactArmor.cdiamond, 0, 1, 1, strong2Rare));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomArtifact(ItemArtifactArmor.ldiamond, 0, 1, 1, strong2Rare));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomArtifact(ItemArtifactArmor.bdiamond, 0, 1, 1, strong2Rare));

		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomArtifact(ItemArtifactArmor.hdiamond, 0, 1, 1, strong3Rare));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomArtifact(ItemArtifactArmor.cdiamond, 0, 1, 1, strong3Rare));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomArtifact(ItemArtifactArmor.ldiamond, 0, 1, 1, strong3Rare));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomArtifact(ItemArtifactArmor.bdiamond, 0, 1, 1, strong3Rare));

		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomArtifact(ItemArtifactArmor.ccloth, 0, 1, 1, mineRare));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomArtifact(ItemArtifactArmor.hiron, 0, 1, 1, mineRare));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomArtifact(ItemArtifactArmor.hchain, 0, 1, 1, mineRare));

		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.cdiamond, 0, 1, 1, tempRare));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.cgold, 0, 1, 1, tempRare));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomArtifact(ItemArtifactArmor.ciron, 0, 1, 1, tempRare));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockPedestal.instance,2), "ggg","g g","sss",'g', new ItemStack(Blocks.glass_pane), 's', "stone"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockSpikes.instance, 2), "i i","sss", 'i', "ingotIron", 's', new ItemStack(Blocks.stone_slab)));
		GameRegistry.addShapedRecipe(new ItemStack(BlockSpikes.instance, 2), "i i","sss", 'i', new ItemStack(Items.iron_ingot), 's', new ItemStack(Blocks.stone_slab));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockTrap.instance), new ItemStack(Items.painting), new ItemStack(Blocks.dispenser));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockWallPlate.camoStone), new ItemStack(Items.painting), new ItemStack(BlockWallPlate.stone));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockWallPlate.camoObsidian), new ItemStack(Items.painting), new ItemStack(BlockWallPlate.obsidian));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockWallPlate.camoWood), new ItemStack(Items.painting), new ItemStack(BlockWallPlate.wood));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockArtifactsPressurePlate.camoStone), new ItemStack(Items.painting), new ItemStack(Blocks.stone_pressure_plate));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockArtifactsPressurePlate.camoObsidian), new ItemStack(Items.painting), new ItemStack(BlockArtifactsPressurePlate.obsidian));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockArtifactsPressurePlate.camoWood), new ItemStack(Items.painting), new ItemStack(Blocks.wooden_pressure_plate));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockWallPlate.stone, 2), "s", "s", "s", 's', "stone"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockWallPlate.wood, 2), "w", "w", "w", 'w', "plankWood"));
		GameRegistry.addShapedRecipe(new ItemStack(BlockWallPlate.obsidian, 2), "o", "o", "o", 'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addShapedRecipe(new ItemStack(BlockArtifactsPressurePlate.obsidian, 2), "ooo", 'o', new ItemStack(Blocks.obsidian));
		GameRegistry.addShapedRecipe(new ItemStack(BlockCoverPlate.instance, 2), "s", "p", 's', new ItemStack(BlockWallPlate.camoStone), 'p', new ItemStack(Items.painting));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemOrichalcumDust.instance, 2, 1), "logWood", new ItemStack(ItemOrichalcumDust.instance, 1, 0), Items.gold_nugget));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemOrichalcumDust.instance, 2, 2), "cobblestone", new ItemStack(ItemOrichalcumDust.instance, 1, 0), Items.gold_nugget));
		//These oredict recipes don't work with vanilla items
		/*GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemOrichalcumDust.instance, 1, 2), "ingotIron", new ItemStack(ItemOrichalcumDust.instance, 1, 0), Item.goldNugget));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemOrichalcumDust.instance, 1, 3), "gemDiamond", new ItemStack(ItemOrichalcumDust.instance, 1, 0), Item.goldNugget));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemOrichalcumDust.instance, 1, 4), "ingotGold", new ItemStack(ItemOrichalcumDust.instance, 1, 0), Item.goldNugget));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemOrichalcumDust.instance, 1, 5), "leather", new ItemStack(ItemOrichalcumDust.instance, 1, 0), Item.goldNugget));*/
		GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 2, 6), new ItemStack(Items.leather), new ItemStack(ItemOrichalcumDust.instance, 1, 0), Items.gold_nugget);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 2, 3), new ItemStack(Items.iron_ingot), new ItemStack(ItemOrichalcumDust.instance, 1, 0), Items.gold_nugget);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 2, 5), new ItemStack(Items.gold_ingot), new ItemStack(ItemOrichalcumDust.instance, 1, 0), Items.gold_nugget);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 2, 4), new ItemStack(Items.diamond), new ItemStack(ItemOrichalcumDust.instance, 1, 0), Items.gold_nugget);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 4, 0), new ItemStack(ItemArtifact.instance));

		for(int i = 0; i < 4; ++i) {
			GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 4, 0), new ItemStack(ItemArtifactArmor.clothArray[i]));
			GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 4, 0), new ItemStack(ItemArtifactArmor.chainArray[i]));
			GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 4, 0), new ItemStack(ItemArtifactArmor.ironArray[i]));
			GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 4, 0), new ItemStack(ItemArtifactArmor.goldArray[i]));
			GameRegistry.addShapelessRecipe(new ItemStack(ItemOrichalcumDust.instance, 4, 0), new ItemStack(ItemArtifactArmor.diamondArray[i]));
		}

		GameRegistry.addShapelessRecipe(new ItemStack(BlockQuickSand.instance), new ItemStack(Items.potionitem, 1, 8204), new ItemStack(Blocks.dirt));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockQuickSand.instance, 2), new ItemStack(Items.potionitem, 1, 8204), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockQuickSand.instance, 3), new ItemStack(Items.potionitem, 1, 8204), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockQuickSand.instance, 4), new ItemStack(Items.potionitem, 1, 8204), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockQuickSand.instance, 5), new ItemStack(Items.potionitem, 1, 8204), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockQuickSand.instance, 6), new ItemStack(Items.potionitem, 1, 8204), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockQuickSand.instance, 7), new ItemStack(Items.potionitem, 1, 8204), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockQuickSand.instance, 8), new ItemStack(Items.potionitem, 1, 8204), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLaserBeamSource.instance), "sss", "rog", "sss", 'o', new ItemStack(ItemOrichalcumDust.instance, 4, 0), 'g', new ItemStack(Blocks.glass_pane), 's', "stone", 'r', new ItemStack(Items.redstone)));
		GameRegistry.addShapedRecipe(new ItemStack(ItemCalendar.instance), "ppp","pcp","ppp", 'p', new ItemStack(Items.paper), 'c', new ItemStack(Items.clock));

		//MinecraftForge.setToolClass(ItemArtifact.instance, "pickaxe", 3); //This code changed; moved to artifact api.
		DamageSourceSword.instance = new DamageSourceSword("artifacts.trapsword");
		DamageSourceQuicksand.instance = new DamageSourceQuicksand("artifacts.quicksand");

		GameRegistry.registerTileEntity(TileEntityDisplayPedestal.class, "artifacts.pedestal");
		GameRegistry.registerTileEntity(TileEntitySword.class, "artifacts.tesword");
		GameRegistry.registerTileEntity(TileEntityTrap.class, "artifacts.arrowtrap");
		GameRegistry.registerTileEntity(TileEntitySpikes.class, "artifacts.spiketrap");
		GameRegistry.registerTileEntity(TileEntityAntibuilder.class, "artifacts.antibuilder");
		EntityRegistry.registerGlobalEntityID(EntityClayGolem.class, "ClayGolem", golemID, 13347172, 7033635);
//        EntityRegistry.registerModEntity(EntityClayGolem.class, "EntClayGolem", 0, this, 350, 5, false);
		EntityRegistry.registerModEntity(EntitySpecialArrow.class, "SpecialArrow", 1, this, 64, 20, true);
//        EntityList.addMapping(EntityClayGolem.class, "ClayGolem", golemID, 13347172, 7033635);//13347172 is pale

		proxy.registerTickHandlers();
		proxy.registerEventHandlers();
		proxy.registerRenders();
		DispenserBehaviors.registerBehaviors();

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		//These have to be unique
		byte serverMessageID = 1;
		byte clientMessageID = 2;

		//Registering the "messages", which are simple packets.
		artifactNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("Artifacts");
		artifactNetworkWrapper.registerMessage(PacketHandlerServer.class, CToSMessage.class, serverMessageID, Side.SERVER);
		artifactNetworkWrapper.registerMessage(PacketHandlerClient.class, SToCMessage.class, clientMessageID, Side.CLIENT);
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		baublesLoaded = Loader.isModLoaded("Baubles");
		mystcraftLoaded = Loader.isModLoaded("Mystcraft");
		//System.out.println("[Artifacts] Is Baubles Loaded? " + baublesLoaded);
		//System.out.println("[Artifacts] Is Mystcraft Loaded? " + mystcraftLoaded);
	}
}
