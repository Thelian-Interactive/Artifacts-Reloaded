package artifreload.api.main;


import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;

/**
 * TODO Update Class so it works with 1.12.2
 * FOR NOW, Mod Developers can create their own Hooks for grabbing Artifacts to their Loot Table Pools
 * I have to figure out how i want other mods to add Artifacts into their Loot Tables
 * Once I Do, I will Let Mod Devs know how i want it implemented.
 * -------------------------------------------------------------------------------------------------------------
 * This class is exposed to aid mod developers adding artifacts to their loot chests in a more detailed manner.
 * This class insures that artifacts generated in this manner will already have their effects applied.
 * Use this class exactly as you would WeightedRandomChestContent
 * -------------------------------------------------------------------------------------------------------------
 * @author ThelianInteractive
 * @author "Original" Draco18s
 *
 */


public class WeighedRandomArtifact extends WeightedRandom {

/*
public WeightedRandomArtifact(Item item, int metadata, int minNumberPerStack, int maxNumberPerStack, int probability) {
	super(item, metadata, minNumberPerStack, maxNumberPerStack, probability);
}

public WeightedRandomArtifact(ItemStack par1ItemStack, int minNumberPerStack, int maxNumberPerStack, int probability) {
	super(par1ItemStack, minNumberPerStack, maxNumberPerStack, probability);
}

@Override
protected ItemStack[] generateChestContent(Random random, IInventory newInventory)
{
	ItemStack[] a = ChestGenHooks.loottable(random, theItemId, theMinimumChanceToGenerateItem, theMaximumChanceToGenerateItem);
	for(int i=a.length-1; i>=0; --i) {
		a[i] = ArtifactAPI.artifacts.applyRandomEffects(a[i]);
	}
	return a;
}*/
}

