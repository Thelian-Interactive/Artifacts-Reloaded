package artifreload.api;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import net.minecraftforge.common.util.EnumHelper;


public class IArmourMaterial {

	public static ArmorMaterial ORICHALCUM_DIAMOND = EnumHelper.addArmorMaterial("ORICHALCUM_DIAMOND", "", 33, new int[] { 2, 4, 5, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
	public static ArmorMaterial ORICHALCUM_WOOD = EnumHelper.addArmorMaterial("ORICHALCUM_WOOD", "", 8, new int[] { 0, 1, 2, 0}, 6, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F );
	public static ArmorMaterial ORICHALCUM_LEATHER = EnumHelper.addArmorMaterial("ORICHALCUM_LEATHER", "", 9, new int[] { 1, 2, 3, 1}, 15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F );
	public static ArmorMaterial ORICHALCUM_STONE = EnumHelper.addArmorMaterial("ORICHALCUM_STONE", "", 10, new int[] { 1, 2, 2, 1}, 7, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F );
	public static ArmorMaterial ORICHALCUM_IRON = EnumHelper.addArmorMaterial("ORICHALCUM_IRON", "", 24, new int[] { 1, 3, 2, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static ArmorMaterial ORICHALCUM_GOLD = EnumHelper.addArmorMaterial("ORICHALCUM_GOLD", "", 16, new int[] { 1, 2, 3, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F	);
	public static ArmorMaterial ORICHALCUM_RAW = EnumHelper.addArmorMaterial("ORICHALCUM_RAW", "", 50, new int[] { 4, 7, 12, 6}, 5, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
	public static ArmorMaterial GENERIC = EnumHelper.addArmorMaterial("GENERIC", "", 0, new int[]{0,0,0,0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
}
