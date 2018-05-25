package artifreload.common.item;

import net.minecraft.item.Item;


public class FakeSword extends Item {


public static Item wood;
public static Item stone;
public static Item iron;
public static Item gold;
public static Item diamond;
private String regString;

public FakeSword(Item.ToolMaterial toolMaterial, String str) {
	super(toolMaterial);
	regString = str;
	this.setCreativeTab(null);
}


public void registerIcons(IIconRegister iconReg)
{
	itemIcon = iconReg.registerIcon(regString);
}
}
