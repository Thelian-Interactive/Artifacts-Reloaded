package artifreload.common.item.artifact.armour;



import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

import net.minecraftforge.common.ISpecialArmor;

import artifreload.common.util.registry.interfaces.IModelRegister;


public abstract class ArmourBase extends ItemArmor implements ISpecialArmor, IModelRegister {




protected Map<EntityEquipmentSlot, ModelBiped> models = null;
public final EntityEquipmentSlot type;

public ArmourBase(EntityEquipmentSlot type, String name){

	this(type, name , IMaterialArtifact);


}


public ArmourBase(EntityEquipmentSlot type, String name, ArmorMaterial mat ) {

	super(type, name, mat);
}









}
