package artifreload.common.util.registry;

import artifreload.common.item.ItemBase.BaseItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;


public class ModItems {

    public static BaseItem orichalcumDust = new BaseItem("orichalcum_dust").setCreativeTab(BaseItem.tab);
    public static BaseItem orichalcumDiamond = new BaseItem("orichalcum_dust_diamond").setCreativeTab(BaseItem.tab);
    public static BaseItem orichalcumGold = new BaseItem("orichalcum_dust_gold").setCreativeTab(BaseItem.tab);
    public static BaseItem orchalcumIron = new BaseItem("orichalcum_dust_iron").setCreativeTab(BaseItem.tab);
    public static BaseItem orichalcumLeather = new BaseItem("orichalcum_dust_leather").setCreativeTab(BaseItem.tab);
    public static BaseItem orichalcumStone = new BaseItem("orichalcum_dust_stone").setCreativeTab(BaseItem.tab);
    public static BaseItem orichalcumWood = new BaseItem("orichalcum_dust_wood").setCreativeTab(BaseItem.tab);
    //public static BaseItem  = new BaseItem("").setCreativeTab(BaseItem.tab);



    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                orichalcumDust,
                orichalcumDiamond,
                orichalcumGold,
                orchalcumIron,
                orichalcumLeather,
                orichalcumStone,
                orichalcumWood

        );
    }

    public static void registerModels() {
        orichalcumDust.registerItemModel(),
        orichalcumDiamond.registerItemModel(),
        orichalcumGold.registerItemModel(),
        orchalcumIron.registerItemModel(),
        orichalcumLeather.registerItemModel(),
        orichalcumStone.registerItemModel(),
        orichalcumWood.registerItemModel()
    };

}
