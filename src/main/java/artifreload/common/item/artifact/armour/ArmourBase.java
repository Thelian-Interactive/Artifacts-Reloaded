package artifreload.common.item.artifact.armour;



import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.common.ISpecialArmor;

import artifreload.api.IArmourMaterial;
import artifreload.common.DragonArtifacts;
import artifreload.common.util.artifact.ModInfo;
import com.google.common.collect.Multimap;


public class ArmourBase extends ItemArmor implements ISpecialArmor, IModelRegister {

protected Map<EntityEquipmentSlot, ModelBiped> models = null;
public static final CreativeTabs tab = DragonArtifacts.ArtifactItemsTab;
public EntityEquipmentSlot type;
private String name;

public ArmourBase(EntityEquipmentSlot type, String name) {

	this(IArmourMaterial.GENERIC, type, name);

}

public ArmourBase(ArmorMaterial mat, EntityEquipmentSlot type, String name) {

	super( mat, 0, type );
	this.type = type;
	setRegistryName(new ResourceLocation((ModInfo.MOD_ID, name));
	setUnlocalizedName(name);
}


@Override
public void registerModels() {

}

@Override
public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {

	if(source.isUnblockable())
		return new ArmorProperties(0, 0, 0);
	return new ArmorProperties(0, damageReduceAmount / 25D, armor.getMaxDamage() + 1 - armor.getItemDamage());

}

@Override
public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {

	return damageReduceAmount;
}

@Override
public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
	Multimap<String, AttributeModifier> attrib = super.getAttributeModifiers(slot, stack);
	// Remove these or else vanilla will double count it and ISpecialArmor
	attrib.removeAll(SharedMonsterAttributes.ARMOR.getName());
	attrib.removeAll(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName());
	return attrib;
}



@Override
public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {

}
}
