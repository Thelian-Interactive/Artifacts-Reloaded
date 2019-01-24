package artifreload.api.trap;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;

import artifreload.api.trap.internal.ITrapBehaviorProvider;
import artifreload.api.trap.internal.ITrapBlockSource;


public interface IBehaviorITrap {

	IBehaviorITrap iTrapBehaviorProvider = new ITrapBehaviorProvider();



	ItemStack dispense(IBlockSource iSource, ItemStack iStack);

}
