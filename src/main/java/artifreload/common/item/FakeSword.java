package artifreload.common.item;

import net.minecraft.item.Item;


public class FakeSword extends Item {


public static Item wood;
public static Item stone;
public static Item iron;
public static Item gold;
public static Item diamond;
private String regString;

public ItemFakeSwordRenderable(Item.ToolMaterial toolMaterial, String str) {
	super(toolMaterial);
	regString = str;
	this.setCreativeTab(null);
}

@Override
public void registerIcons(IIconRegister iconReg)
{
	itemIcon = iconReg.registerIcon(regString);
}
}
