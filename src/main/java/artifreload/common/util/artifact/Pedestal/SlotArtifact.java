package artifreload.common.util.artifact.Pedestal;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import artifreload.common.item.Artifact;


public class SlotArtifact extends SlotItemHandler
{

public SlotArtifact(IItemHandler itemHandler, int index, int xPos, int yPos) {
	super(itemHandler, index, xPos, yPos);

}

public boolean isItemValid(ItemStack stack)
{

	return stack.getItem() == Artifact.instance || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemArmor;

}
}

