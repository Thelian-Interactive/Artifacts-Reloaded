package artifreload.common.item.ItemBase;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import artifreload.common.DragonArtifacts;


public class BaseItem extends Item {

	public static final CreativeTabs tab = DragonArtifacts.ArtifactItemsTab;
	protected String name;



	public BaseItem(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
	}

	public void registerItemModel() {
		DragonArtifacts.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public BaseItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}