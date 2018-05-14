package artifreload.common.components.effects;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

import artifreload.common.components.BaseComponent;
import io.netty.buffer.Unpooled;


public class CBaking extends BaseComponent {


@Override
public String getRandomTrigger(Random rand, boolean isArmor) {
	String str = "";
	if(!isArmor) {
		str = "onItemRightClick";
	}
	return str;
}

@Override
public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
	PacketBuffer out = new PacketBuffer(Unpooled.buffer());
	out.writeInt(PacketHandlerServer.BAKING);
	out.writeInt(player.inventory.currentItem);
	CToSMessage packet = new CToSMessage(out);
	DragonArtifacts.artifactNetworkWrapper.sendToServer(packet);
	itemStack.stackTagCompound.setInteger("onItemRightClickDelay", 20);
	return itemStack;
}

@Override
public void addInformation(ItemStack itemStack, EntityPlayer player, List list, String trigger, boolean advTooltip) {
	list.add(StatCollector.translateToLocal("effect.Places cakes on solid surfaces") + " " + StatCollector.translateToLocal("tool." + trigger));
}

@Override
public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advTooltip) {
	list.add(StatCollector.translateToLocal("effect.Places cakes on solid surfaces"));
}

@Override
public String getPreAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "Baker's";
			break;
		case 1:
			str = "Confectioner's";
			break;
	}
	return str;
}

@Override
public String getPostAdj(Random rand) {
	String str = "";
	switch(rand.nextInt(2)) {
		case 0:
			str = "of Baking";
			break;
		case 1:
			str = "of Icing";
			break;
	}
	return str;
}

@Override
public int getTextureBitflags() {
	return Flags.AMULET | Flags.STAFF | Flags.FIGURINE | Flags.WAND | Flags.TRINKET;
}

@Override
public int getNegTextureBitflags() {
	return ~this.getTextureBitflags();
}
}

