package artifreload.api.main.trap.internal;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;

import artifreload.api.main.trap.IBehaviorITrap;


public final class ITrapBehaviorProvider implements IBehaviorITrap {

	public ItemStack dispense(IBlockSource iSource, ItemStack iStack) {

		return iStack;
	}

}
