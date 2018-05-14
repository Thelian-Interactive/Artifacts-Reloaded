package artifreload.api.interfaces;


import net.minecraft.item.ItemStack;

import artifreload.api.interfaces.Internal.BehaviorITrapProvider;
import artifreload.api.interfaces.Internal.IBlockSource;


/**
	* Duplicate of dispenser behaviors for the Trap block.  This interface
	* is the one that should be extended by plugin developers.
	* @see net.minecraft.dispenser
	* @author Draco18s
	*
	*/
public interface IBehaviorITrap {


IBehaviorITrap itemDispenseBehaviorProvider = new BehaviorITrapProvider();

/**
	* Dispenses the specified ItemStack from a dispenser.
	*/
ItemStack dispense(IBlockSource iblocksource, ItemStack itemstack);















}
