package artifreload.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;


public class ClayBall extends EntityThrowable {

public ClayBall(World worldIn)
{
	super(worldIn);
}

public ClayBall(World worldIn, EntityLivingBase throwerIn)
{
	super(worldIn, throwerIn);
}

public ClayBall(World worldIn, double x, double y, double z)
{
	super(worldIn, x, y, z);
}

public static void registerFixesClayBall(DataFixer fixer)
{
	EntityThrowable.registerFixesThrowable(fixer, "Snowball");
}

 static void setThrowableHeading(double d1, double v, double d3, float v1, float v2) {

}

public void handleStatusUpdate(byte id)
{
	if (id == 3)
	{
		for (int i = 0; i < 8; ++i)
		{
			this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}
}

/**
 * Called when this EntityThrowable hits a block or entity.
 */
protected void onImpact(RayTraceResult result)
{
	if (result.entityHit != null)
	{
		int i = 0;

		if (result.entityHit instanceof EntityBlaze)
		{
			i = 3;
		}

		result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
	}

	if (!this.world.isRemote)
	{
		this.world.setEntityState(this, (byte)3);
		this.setDead();
	}
}
}
