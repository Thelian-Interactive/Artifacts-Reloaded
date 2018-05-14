package artifreload.api.interfaces.Internal;
import net.minecraft.item.ItemStack;

import artifreload.api.interfaces.IBehaviorITrap;


public final class BehaviorITrapProvider implements IBehaviorITrap {

private ItemStack IItemStack;

public ItemStack dispense(IBlockSource IBlockPos, ItemStack IItemStack) {

	this.IItemStack = IItemStack;

	return IItemStack;
}
}


















