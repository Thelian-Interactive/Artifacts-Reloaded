package artifreload.api.interfaces;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;


public interface IArtifactComp {

/**
	* When the item is the active one in the hotbar.  Called 20 times a second.  Called on client & server.
	*/
String TRIGGER_HELD = "onHeld";
/**
	* When the item is anywhere in the player's inventory.  Called 20 times a second.  Called on client & server.
	* Also used to call TRIGGER_ENTITY_UPDATE.  Effect is not required to do anything.
	* (e.g. ComponentLight takes advantage of this)
	*/
String TRIGGER_UPDATE = "onUpdate";
/**
	* When the player right-clicks with the item active.  Called on client only.
	*/
String TRIGGER_RCLICK = "onItemRightClick";
/**
	* When the player hits another entity with the item.  Called on client & server.
	*/
String TRIGGER_HIT = "hitEntity";
/**
	* Indirect used for getStrVsBlock and canHarvestBlock.  Called on client & server.
	* Used by ComponentMining to make artifacts effective pickaxes, but all effects may use these methods
	* (e.g. ComponentDamage makes artifacts just as effective against leaves and webs).
	*/
String TRIGGER_DIG = "onDig";
/**
	* When the player breaks a block holding the item.  Called on client only.
	*/
String TRIGGER_BREAK = "onBlockDestroyed";
/**
	* When the player right clicks on a living entity (i.e. shears).  Called on client & server.
	*/
String TRIGGER_INTERACT = "itemInteractionForEntity";
/**
	* When the item exists in the world; called once per second.  Called on client only.
	*/
String TRIGGER_ENTITY_UPDATE = "onEntityItemUpdate";
/**
	* Identical to TRIGGER_ENTITY_UPDATE, used for on-dropped effects.
	* More useful than TRIGGER_DROPPED_PLAYER.  Called on client & server.
	*/
String TRIGGER_DROPPED = "onDropped";
/**
	* When the player attempts to drop the item (limited usefulness).  Called on client only?
	* Fires before the item is ejected into the world.
	* Not used by any default effects.
	*/
String TRIGGER_DROPPED_PLAYER = "onDroppedByPlayer";
/**
	* Called every tick.  Effect should only trigger if worn is passed as TRUE, otherwise
	* is identical to {@link #TRIGGER_UPDATE}
	*/
String TRIGGER_ARMOR_TICK = "onArmorTickUpdate";
/**
	* Calls onTakeDamage when the event happens.<br/>
	* Also calls onArmorTickUpdate every tick (both equipped and unequipped) for any delays you need.
	*/
String TRIGGER_TAKE_DAMAGE = "onTakeDamage";
/**
	* Calls onDeath when the event happens.<br/>
	* Also calls onArmorTickUpdate every tick (both equipped and unequipped) for any delays you need.
	*/
String TRIGGER_ON_DEATH = "onDeath";

/**
	* Any given effect can have multiple triggers that it can work for.  Any given trigger
	* is going to be used only once per ItemArtifact ItemStack, except where otherwise noted.
	* This function returns a random valid trigger for the implemented effect.  If only one,
	* just return that string.  Cannot return two triggers.  FUTURETECH: assuming ever implemented
	* to allow the same effect on two different trigger for one item, this function would still
	* only need to return a single trigger string (the two triggers would be treated independently
	* of each other as if they were two different effects).
	* @param rand
	* @return the trigger string (see static properties)
	*/
String getRandomTrigger(Random rand, boolean isArmor);

/**
	* Used to supply some initial setup on some effects.  Currently called for all effects once.
	* onHeld is currently used by the +damage effects and applys NBT data to the item stack which is
	* later used by vanilla to modify the player's Shared Monster Attributes.  For onDropped, it's used
	* to set up the "fuse length" delay before the effect kicks in.
	* @param i
	* @param rand
	* @param effects list of all effects on the item (so far).
	* @return
	*/
ItemStack attached(ItemStack i, Random rand, int[] effects);
/**
	* Called when a player drops the item into the world, returning false from this will prevent the item from
	* being removed from the players inventory and spawning in the world (with Q, dropping it outside the
	* inventory window still drops it).<br/><br/>Probably not as useful as {@link #onEntityItemUpdate(EntityItem, String)}
	*
	* @param player The player that dropped the item
	* @param item The item stack, before the item is removed from the player's inventory.
	*/
boolean onDroppedByPlayer(ItemStack item, EntityPlayer player);
/**
	* Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	* True if something happen and false if it don't. This is for ITEMS, not BLOCKS<br/><br/>
	* Currently unused
	*/
boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10);
/**
	* Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
	* sword.  Returning 0 is acceptable, and the default value will be used.<br/>
	* Called for all effects and the largest value returned will be used.
	*/
float getDigSpeed(ItemStack itemStack, Block block, int meta);
/**
	* Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	*/
ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer);
/**
	* Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
	* the damage on the stack.
	*/
boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase);
/**
	* Called when a block is broken with the ItemStack active
	*/
boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase);
/**
	* Returns if the item (tool) can harvest results from the block type.<br/>
	* Called for all effects.
	*/
boolean canHarvestBlock(Block par1Block, ItemStack itemStack);
/**
	* Returns true if the item can be used on the given entity, e.g. shears on sheep.
	*/
boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase);
/**
	* Called by the default implemetation of EntityItem's onUpdate method, allowing for cleaner
	* control over the update of the item without having to write a subclass.
	*
	* @param entityItem The entity Item
	* @param type onUpdate vs. onEntityItemUpdate vs. onDropped
	* @return Return true to skip any further update code.
	*/
boolean onEntityItemUpdate(EntityItem entityItem, String type);
/**
	* Called each tick as long the item is in a player inventory.
	* @param the item stack
	* @param the world
	* @param the player
	* @param the inventory slot number
	* @param true if the item is held (use {@link #onHeld(ItemStack, World, Entity, int, boolean)} for onHeld effects)
	*/
void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5);
/**
	* Called each tick as long the item is the player's active item.
	* @param the item stack
	* @param the world
	* @param the player
	* @param the inventory slot number
	* @param will always pass true
	*/
void onHeld(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5);
/**
	* Called each tick as long the item is equipped in one of the player's armor slots.
	* If it is not equipped, @param worn will be false (to disable 'permanent' effects).
	*/
void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack, boolean worn);
/**
	* Allows items to add custom lines of information to the mouseover description.  Used for triggers that
	* don't have or need a string description.  Such as the "effective pickaxe" effect.
	* @see #addInformation(ItemStack, EntityPlayer, List, String, boolean)
	*/
void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip);
/**
	* Allows items to add custom lines of information to the mouseover description, takes a String
	* for the trigger type.  99% of the time you'll want to use this one.
	* @param trigger the trigger string (returned by {@link #getRandomTrigger(Random)}
	*/
void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip);

/**
	* Should return a non-empty non-null string that would fill in the blank: ______ Wooden Sword<br/><br/>
	* For expample: "Flaming"
	* @param rand
	* @return
	*/
String getPreAdj(Random rand);

/**
	* Should return a non-empty non-null string that would fill in the blank: Wooden Sword ______<br/><br/>
	* For expample: "of Smiting"
	* @param rand
	* @return
	*/
String getPostAdj(Random rand);

/**
	* Icons are chosen based on a weighted sum of the returned flags from all effects.  The higher the value the
	* more likely an icon of that type will be chosen.  0 for a flag indicates either uncaring or negative status
	* depending on {@link #getNegTextureBitflags}.<br/><br/>
	* Armor is currently unused.  When implemented, it will be ignored here, though armor types (leggings, etc.)
	* will still be used as normal.<br/><br/>
	* 0x1 Amulet<br/>0x2 Dagger<br/>0x4 Figurine<br/>0x8 Ring<br/>0x10 Staff<br/>0x20 Sword<br/>0x40 Trinket<br/>
	* 0x80 Wand<br/>0x100 Armor<br/>0x200 Boots<br/>0x400 Chestplate<br/>0x800 Helm<br/>0x1000 Leggings
	*
	* @return bitflag int
	*/
int getTextureBitflags();

/**
	* Icons are chosen based on a weighted sum of the returned flags from all effects.  The higher the value the
	* more likely an icon of that type will be chosen.  While a negative flag will reduce the odds of this effect
	* being placed on an inappropriate item, it is not guaranteed.  0 for a flag indicates uncaring or positive status
	* depending on {@link #getTextureBitflags}.<br/><br/>
	* Armor is currently unused.  When implemented any effect that should never be on a armor should return
	* that flag here.<br/><br/>
	* 0x1 Amulet<br/>0x2 Dagger<br/>0x4 Figurine<br/>0x8 Ring<br/>0x10 Staff<br/>0x20 Sword<br/>0x40 Trinket<br/>
	* 0x80 Wand<br/>0x100 Armor<br/>0x200 Boots<br/>0x400 Chestplate<br/>0x800 Helm<br/>0x1000 Leggings
	*
	* @return bitflag int
	*/
int getNegTextureBitflags();
/**
	* Called from a LivingHurtEvent handler.  Will only fire if the event.entity is of instance EntityPlayer
	* Using these requires an understanding of Shared Monster Attributes and using {@link ArtifactsAPI#OnHurtAttribute}<br/>
	* You will likely want to create unique components for behaviors that use this.
	* @param itemStack is the stack associated with the effect
	* @param event is the LivingHurtEvent
	* @param isWornArmor true if the item is equipped to the player's armor slots
	*/
void onTakeDamage(ItemStack itemStack, LivingHurtEvent event, boolean isWornArmor);
/**
	* Called from a LivingDeathEvent handler.  Will only fire if the event.entity is of instance EntityPlayer
	* Using these requires an understanding of Shared Monster Attributes and using {@link ArtifactsAPI#OnHurtAttribute}<br/>
	* You will likely want to create unique components for behaviors that use this.
	* @param itemStack is the stack associated with the effect
	* @param event is the LivingDeathEvent
	* @param isWornArmor true if the item is equipped to the player's armor slots
	*/
void onDeath(ItemStack itemStack, LivingDeathEvent event, boolean isWornArmor);

/**
	* Returns the level of block this artifact can harvest.
	* 0 - wood and gold
	* 1 - stone
	* 2 - iron
	* 3 - diamond
	* -1 - doesn't harvest
	*
	* @param stack
	* @param toolClass
	*/
int getHarvestLevel(ItemStack stack, String toolClass);

}
