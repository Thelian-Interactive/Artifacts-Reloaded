package artifreload.api;

import java.util.HashMap;

import net.minecraft.item.ItemArmor;

@Deprecated
public interface IItemIconAPI {

/**
 * Minecraft uses json now.
 * @Deprecated
 *
 *
 */
	 /*
/**
	* Internal use only.
	* @see #registerArtifactIcon(String icon, String type)
	* @see #registerArtifactIcon(String icon, String overlay, String type)

HashMap icons = new HashMap();

/**
	* Internal use only.
	* @see #registerModelTexture(String icon, ItemArmor.ArmorMaterial material, String modelTexture)
	* @see #registerModelTexture(String icon, ItemArmor.ArmorMaterial material, String modelTexture, String modelOverlay)

HashMap<String, HashMap<ItemArmor.ArmorMaterial, String>> armorModels = new HashMap<String, HashMap<ItemArmor.ArmorMaterial, String>>();

/**
	* Registers a single, flat, icon to be used by the artifact factory
	* @param type the item type.  Valid strings are amulet, dagger, figurine,
	* ring, staff, sword, trinket, wand
	* @param icon the icon registration string (to be passed to the IconRegister)
	* @see #registerArtifactIcon(String icon, String overlay, String type)
	* @Note Dagger, Sword, Staff, and Wand icons should be diagonal, pointing up to the right.

void registerArtifactIcon(String type, String icon) throws Exception;

/**
	* Registers two icons to be used by the artifact factory.  The overlay is grayscale
	* and gets a colorized multiplier during rendering.
	* @param type the item type.  Valid strings are amulet, dagger, figurine,
	* ring, staff, sword, trinket, wand
	* @param icon the base icon registration string (to be passed to the IconRegister)
	* @param overlay the overlay icon registration string
	* @see #registerArtifactIcon(String icon, String type)
	* @Note Dagger, Sword, Staff, and Wand icons should be diagonal, pointing up to the right.

void registerArtifactIcon(String type, String icon, String overlay) throws Exception;

/**
	* Registers an armor model texture to be used by the artifact factory.
	* @param material The armor material type.
	* @param icon The icon for the item which this model will render for (should be the icon passed in for {@link #registerArtifactIcon(String, String, String)},
	* not the overlay).
	* @param modelTexture The model texture. It should be the filepath to the texture from your mod's assets/textures folder.
	* @see #registerModelTexture(String icon, ItemArmor.ArmorMaterial material, String modelTexture, String modelColor)

void registerModelTexture(String icon, ItemArmor.ArmorMaterial material, String modelTexture);

/**
	* Registers an armor model texture to be used by the artifact factory, with a coloured layer as well.
	* @param material The armor material type.
	* @param icon The icon for the item which this model will render for (should be the icon passed in for {@link #registerArtifactIcon(String, String, String)},
	* not the overlay).
	* @param modelTexture The model texture. It should be the filepath to the texture from your mod's assets/textures folder.
	* @param modelColor A second model texture that renders under the first, and is coloured based on the artifact's overlay colour.
	* It should have the same type of filepath as the modelTexture.
	* @see #registerModelTexture(String icon, ItemArmor.ArmorMaterial material, String modelTexture)

void registerModelTexture(String icon, ItemArmor.ArmorMaterial material, String modelTexture, String modelColor);

/**
	* Internal use only
	*/


}

