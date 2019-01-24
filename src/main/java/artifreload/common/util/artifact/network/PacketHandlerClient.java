package artifreload.common.util.artifact.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemRecord;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.EnumParticleTypes;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import artifreload.common.block.EBlock.TEPedestal;
import artifreload.common.util.artifact.event.ClientEventHandler;


public class PacketHandlerClient implements IMessageHandler<SToCMessage, IMessage> {


public static final int ORE_RADAR = 23;
public static final int OBSCURITY = 28;
public static final int CAKE_PARTICLES = 29;
public static final int PLAY_RECORD = 30;
public static final int PEDESTAL = 256;
public static final int ANTI_BUILDER = 4097;
public EnumParticleTypes particleType;
public static String recordName ;
public PacketHandlerClient() {

}

public IMessage onMessage(SToCMessage packet, MessageContext context)
{

	EntityPlayer p = Minecraft.getMinecraft().player;
	World world = p.world;
	ByteArrayInputStream stream = new ByteArrayInputStream(packet.getData());
	DataInputStream dis = new DataInputStream(stream);
	//System.out.println("Packet get");
	TileEntity te;
	try
	{
		int effectID = dis.readInt();
		switch(effectID) {
			case ORE_RADAR:
				int x = dis.readInt();
				int y = dis.readInt();
				int z = dis.readInt();
				//if(rand.nextBoolean() && rand.nextBoolean())
				if(y >= p.posY)
					drawParticle(p.world, x+.5, y+.5, z+.5, "radar", 0);
				else
					drawParticle(p.world, x+.5, y+.5, z+.5, "radar", 0);
				//System.out.println("Server particles");
				break;
			case OBSCURITY:
				p.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 600, 0));
				ClientEventHandler.cloaked = true;
				break;
			case PEDESTAL:
				//Update the display pedestal name on the client

				te = world.getTileEntity(BlockPos.ORIGIN);
				if(te instanceof TEPedestal) {
					TEPedestal ted = (TEPedestal)te;

					int nameLength = dis.readInt();
					String name = "";

					for(int s = 0; s < nameLength; s++) {
						name += dis.readChar();
					}

					ted.ownerName = name;
				}
				break;
			case CAKE_PARTICLES:
				//Spawn some particles for when the cake is placed down, and play a sound.

				Random rand = new Random();
				int cakeX = dis.readInt();
				int cakeY = dis.readInt();
				int cakeZ = dis.readInt();

				for (int i = 0; i < 20; ++i)
				{
					double pX = cakeX + rand.nextDouble();
					double pY = cakeY + rand.nextDouble()*0.5;
					double pZ = cakeZ + rand.nextDouble();
					double vX = rand.nextGaussian() * 0.02D;
					double vY = rand.nextGaussian() * 0.02D;
					double vZ = rand.nextGaussian() * 0.02D;

					Minecraft.getMinecraft().world.spawnParticle(particleType.FIREWORKS_SPARK, pX, pY, pZ, vX, vY, vZ);
				}

				break;
			case PLAY_RECORD:
				//Play the given record track, if it exists.

				int recordX = dis.readInt();
				int recordY = dis.readInt();
				int recordZ = dis.readInt();
				boolean play = dis.readBoolean();

				if(play) {
					String record = "";
					int length = dis.readInt();
					for(int i = 0; i < length; i++) {
						record += dis.readChar();
					}


					if(ItemRecord.getByNameOrId("records."+record = recordName) != null) {
						//Record exists; play it.
						p.world.playRecord(new BlockPos(p), recordName);
					}
					else {
						System.out.println("The record " + record + "doesn't exist!");
						p.world.playRecord(new BlockPos(p), null );
					}
				}
				else {
					//Stop the current record which is playing.
					p.world.playRecord(new BlockPos(p), null);
				}
				break;
			case ANTI_BUILDER:
				double tx = dis.readDouble();
				double ty = dis.readDouble();
				double tz = dis.readDouble();
				int a = dis.readInt();
				//if(rand.nextBoolean() && rand.nextBoolean())
				drawParticle(p.world, tx, ty, tz, "reset", a);
				break;
			default:
				return null;
		}
	}
	catch  (IOException e)
	{
		e.printStackTrace();
		System.out.println("Failed to read packet");
	}

	//Don't return anything.
	return null;
}

@SideOnly(Side.CLIENT)
private static void drawParticle(World worldObj, double srcX, double srcY, double srcZ, String par1Str, int age)
{
	double tx = srcX;
	double ty = srcY;
	double tz = srcZ;
	Particle particle = null;
	if(par1Str.equals("radar")) {
		particle = new RadarParticle(worldObj, tx, ty, tz, 3, 20);
	}
	if(par1Str.equals("reset")) {
		particle = new AntibuilderParticle(worldObj, tx, ty, tz, 1, age, 48);
	}
	if(particle != null)
		Minecraft.getMinecraft().effectRenderer.addEffect(particle);
}
}

