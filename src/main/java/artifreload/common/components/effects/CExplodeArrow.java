package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;
import io.netty.buffer.Unpooled;


public class CExplodeArrow extends BaseComponent {


public String getRandomTrigger(Random rand, boolean isArmor) {
	if(isArmor) return "";

	return "onItemRightClick";
}

@Override
public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
	PacketBuffer out = new PacketBuffer(Unpooled.buffer());
	out.writeInt(PacketHandlerServer.EXPLODING_ARROWS);
	out.writeInt(player.getEntityId());
	out.writeInt(player.inventory.currentItem);
	CToSMessage packet = new CToSMessage(out);
	DragonArtifacts.artifactNetworkWrapper.sendToServer(packet);
	itemStack.stackTagCompound.setInteger("onItemRightClickDelay", 15);

	return itemStack;
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, String trigger, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Shoots exploding arrows") + " " + StatCollector.translateToLocal("tool."+trigger));
}

@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean advTooltip) {
	par3List.add(StatCollector.translateToLocal("effect.Shoots exploding arrows"));
}

@Override
public String getPreAdj(Random rand) {
	return "Sniping";
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Wrath";
			break;
		case 1:
			str = "of Fireworks";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.RING | Flags.STAFF | Flags.WAND;
}

@Override
public int getNegTextureBitflags() {
	return Flags.AMULET | Flags.TRINKET | Flags.BELT | Flags.ARMOR;
}
}

