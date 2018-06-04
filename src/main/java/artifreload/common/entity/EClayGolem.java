package artifreload.common.entity;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import artifreload.common.block.IBlock.IClayLayer;


public class EClayGolem extends EntityGolem implements IRangedAttackMob {

private static final DataParameter<Byte> PUMPKIN_EQUIPPED = EntityDataManager.<Byte>createKey(EntitySnowman.class, DataSerializers.BYTE);


public EClayGolem(World worldIn) {

	super(worldIn);
	this.setSize(0.7F, 0.9F);
}

protected static void registerFixesClayGolem(DataFixer fixer) {

	EntityLiving.registerFixesMob(fixer, EClayGolem.class);
}

protected void initEntityAI(){

	this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0D, 1.00000001E-5F));
	this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
	this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
	this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
	this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	this.tasks.addTask(7, new EntityAILookIdle(this));
	this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
	this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true, true));
	this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, true, false));
}

protected void applyEntityAttributes()
{
	super.applyEntityAttributes();
	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
}

protected void entityInit() {

	super.entityInit();
	this.dataManager.register(PUMPKIN_EQUIPPED, Byte.valueOf((byte) 16));
}


/**
 * (abstract) Protected helper method to write subclass entity data to NBT.
 */
public void writeEntityToNBT(NBTTagCompound compound)
{
	super.writeEntityToNBT(compound);
	compound.setBoolean("Pumpkin", this.isPumpkinEquipped());
}

/**
 * (abstract) Protected helper method to read subclass entity data from NBT.
 */
public void readEntityFromNBT(NBTTagCompound compound)
{
	super.readEntityFromNBT(compound);

	if (compound.hasKey("Pumpkin"))
	{
		this.setPumpkinEquipped(compound.getBoolean("Pumpkin"));
	}
}




protected int decreaseAirSupply(int air)
{
	return air;
}

protected void collideWithEntity(Entity par1Entity)
{
	if (par1Entity instanceof IMob && this.getRNG().nextInt(20) == 0)
	{
		this.setAttackTarget((EntityLiving)par1Entity);
	}

	super.collideWithEntity(par1Entity);
}

/**
	* Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	* use this to react to sunlight and start to burn.
	*/

public void onLivingUpdate() {

	super.onLivingUpdate();

	if (!this.world.isRemote) {
		int px = MathHelper.floor(this.posX);
		int py = MathHelper.floor(this.posY);
		int pz = MathHelper.floor(this.posZ);

		if (this.isWet()) {

			this.attackEntityFrom(DamageSource.DROWN, 1.0F);
		}

		if (!this.world.getGameRules().getBoolean("mobGriefing")) {
			return;
		}
		for (int l = 0; l < 4; ++l) {

			px = MathHelper.floor(this.posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
			py = MathHelper.floor(this.posY);
			pz = MathHelper.floor(this.posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
			BlockPos blockpos = new BlockPos(px, py, pz);

			if (this.world.getBlockState(blockpos).getMaterial() == Material.AIR && this.world.getBiome(blockpos).getTemperature(blockpos) > 0.8f && IClayLayer.canPlaceBlockAt(this.world, blockpos)) {

				this.world.setBlockState(blockpos, IClayLayer.getDefaultState());
			}

		}
	}
}

public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
{
	ClayBall clayBall = new ClayBall(this.world, this);
	double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
	double d1 = target.posX - this.posX;
	double d2 = d0 - clayBall.posY;
	double d3 = target.posZ - this.posZ;
	float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
	ClayBall.setThrowableHeading(d1, d2 + (double)f, d3, 1.6F, 12.0F);
	this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	this.world.spawnEntity(clayBall);
}

public float getEyeHeight()
{
	return 1.7F;
}

protected boolean processInteract(EntityPlayer player, EnumHand hand)
{
	ItemStack itemstack = player.getHeldItem(hand);

	if (itemstack.getItem() == Items.SHEARS && this.isPumpkinEquipped() && !this.world.isRemote)
	{
		this.setPumpkinEquipped(false);
		itemstack.damageItem(1, player);
	}

	return super.processInteract(player, hand);
}



@Nullable
protected ResourceLocation getLootTable()
{
	return LootTableList.ENTITIES_SNOWMAN;
}

/**
	* Returns the sound this mob makes when it is hurt.
	*/
protected SoundEvent getHurtSound(DamageSource damageSource)
{
	return SoundEvents.ENTITY_IRONGOLEM_HURT;
}

/**
	* Returns the sound this mob makes on death.
	*/
protected SoundEvent getDeathSound()
{
	return SoundEvents.ENTITY_IRONGOLEM_DEATH;
}

/**
	* Plays step sound at given x, y, z for the entity
	*/
@Nullable
@Override
protected SoundEvent getAmbientSound() {

	return SoundEvents.ENTITY_IRONGOLEM_STEP;
}

public boolean isPumpkinEquipped()
{
	return (((Byte)this.dataManager.get(PUMPKIN_EQUIPPED)).byteValue() & 16) != 0;
}

public void setPumpkinEquipped(boolean pumpkinEquipped)
{
	byte b0 = ((Byte)this.dataManager.get(PUMPKIN_EQUIPPED)).byteValue();

	if (pumpkinEquipped)
	{
		this.dataManager.set(PUMPKIN_EQUIPPED, Byte.valueOf((byte)(b0 | 16)));
	}
	else
	{
		this.dataManager.set(PUMPKIN_EQUIPPED, Byte.valueOf((byte)(b0 & -17)));
	}
}


/**
	* Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	* par2 - Level of Looting used to kill this mob.
	*/
protected void dropFewItems(boolean par1, int par2)
{
	int k = 6 + this.rand.nextInt(4);

	for (int l = 0; l < k; ++l)
	{
		this.dropItem((this.isBurning() ? Items.BRICK : Items.CLAY_BALL), 1);
	}
}

/**
	* Called when the mob's health reaches 0.
	*/

public int getMaxSpawnedInChunk()
{
	return 5;
}


@Override
public void setSwingingArms(boolean swingingArms) {

}
}

