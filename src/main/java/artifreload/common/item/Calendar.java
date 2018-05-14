package artifreload.common.item;

import net.minecraft.item.Item;


public class Calendar extends Item {

public static Item instance;

public ItemCalendar() {
	super();
	//this.setTextureName("artifacts:calendar");
	this.setCreativeTab(DragonArtifacts.tabGeneral);
}

@SideOnly(Side.CLIENT)
@Override
public void registerIcons(IIconRegister iconReg)
{
	itemIcon = ClientProxy.calendar;
}

@Override
@SideOnly(Side.CLIENT)
public IIcon getIconFromDamage(int par1) {
	return ClientProxy.calendar;
}
}

