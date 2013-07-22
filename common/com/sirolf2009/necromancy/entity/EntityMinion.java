package com.sirolf2009.necromancy.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.ISaddleAble;
import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necroapi.NecroEntityRegistry;
import com.sirolf2009.necromancy.client.model.ModelMinion;
import com.sirolf2009.necromancy.core.proxy.ClientProxy;
import com.sirolf2009.necromancy.item.ItemGeneric;
import com.sirolf2009.necromancy.tileentity.TileEntityAltar;

import cpw.mods.fml.common.FMLCommonHandler;

public class EntityMinion extends EntityTameable {

	private boolean isAgressive;

	public EntityMinion(World par1World, BodyPart[][] bodypart, String owner) {
		this(par1World);
		setBodyParts(bodypart);
		setTamed(true);
		setOwner(owner);
	}

	public EntityMinion(World par1World) {
		super(par1World);
		getNavigator().setAvoidsWater(true);
		this.getNavigator().setAvoidsWater(true);
		setSize(0.6F, 1.8F);
		ticksExisted = 0;
		tasks.addTask(0, aiMinion);
		tasks.addTask(1, aiSit);
		tasks.addTask(2, aiControlledByPlayer);
		tasks.addTask(4, new EntityAITempt(this, 0.3F, ItemGeneric.getItemStackFromName("Brain on a Stick").getItem().itemID, false));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		dataWatcher.addObject(20, "UNDEFINED");
		dataWatcher.addObject(21, "UNDEFINED");
		dataWatcher.addObject(22, "UNDEFINED");
		dataWatcher.addObject(23, "UNDEFINED");
		dataWatcher.addObject(24, "UNDEFINED");
		dataWatcher.addObject(25, Byte.valueOf((byte) 0));
		onBodyChange();
	}

	protected void func_110147_ax() {
		super.func_110147_ax();
		if(!this.isAltarMob)
			updateBodyParts();
	}

	protected void updateAttributes() {
		double health = (head != null && head[0] != null ? head[0].health : 0) + (torso != null && torso[0] != null ? torso[0].health : 0) + (armLeft != null && armLeft[0] != null ? armLeft[0].health : 0) + (armRight != null && armRight[0] != null ? armRight[0].health : 0) + (leg != null && leg[0] != null ? leg[0].health : 0);
		double followRange = (head != null && head[0] != null ? head[0].followRange : 0) + (torso != null && torso[0] != null ? torso[0].followRange : 0) + (armLeft != null && armLeft[0] != null ? armLeft[0].followRange : 0) + (armRight != null && armRight[0] != null ? armRight[0].followRange : 0) + (leg != null && leg[0] != null ? leg[0].followRange : 0);
		double knockbackResistance = (head != null && head[0] != null ? head[0].knockbackResistance : 0) + (torso != null && torso[0] != null ? torso[0].knockbackResistance : 0) + (armLeft != null && armLeft[0] != null ? armLeft[0].knockbackResistance : 0) + (armRight != null && armRight[0] != null ? armRight[0].knockbackResistance : 0) + (leg != null && leg[0] != null ? leg[0].knockbackResistance : 0);
		double moveSpeed = (head != null && head[0] != null ? head[0].moveSpeed : 0) + (torso != null && torso[0] != null ? torso[0].moveSpeed : 0) + (armLeft != null && armLeft[0] != null ? armLeft[0].moveSpeed : 0) + (armRight != null && armRight[0] != null ? armRight[0].moveSpeed : 0) + (leg != null && leg[0] != null ? leg[0].moveSpeed : 0);
		double attackStrength = (head != null && head[0] != null ? head[0].attackStrength : 0) + (torso != null && torso[0] != null ? torso[0].attackStrength : 0) + (armLeft != null && armLeft[0] != null ? armLeft[0].attackStrength : 0) + (armRight != null && armRight[0] != null ? armRight[0].attackStrength : 0) + (leg != null && leg[0] != null ? leg[0].attackStrength : 0);
		this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(health > Double.MAX_VALUE ? Double.MAX_VALUE : health);
		this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(followRange > 2048.0D ? 2048.0D : followRange);
		this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(knockbackResistance > 1.0D ? 1.0D : knockbackResistance);
		this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(moveSpeed > Double.MAX_VALUE ? Double.MAX_VALUE : moveSpeed);
		//this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(attackStrength > Double.MAX_VALUE ? Double.MAX_VALUE : attackStrength);
	}

	public void dataWatcherUpdate() {
		if (getBodyPartsNames()[0] != "UNDEFINED") {
			dataWatcher.updateObject(20, getBodyPartsNames()[0]);
		}
		if (getBodyPartsNames()[1] != "UNDEFINED") {
			dataWatcher.updateObject(21, getBodyPartsNames()[1]);
		}
		if (getBodyPartsNames()[2] != "UNDEFINED") {
			dataWatcher.updateObject(22, getBodyPartsNames()[2]);
		}
		if (getBodyPartsNames()[3] != "UNDEFINED") {
			dataWatcher.updateObject(23, getBodyPartsNames()[3]);
		}
		if (getBodyPartsNames()[4] != "UNDEFINED") {
			dataWatcher.updateObject(24, getBodyPartsNames()[4]);
		}
		setSaddled(getSaddled());
	}

	private void updateBodyParts() {
		head = getBodyPartFromlocationName("head", dataWatcher.getWatchableObjectString(20));
		torso = getBodyPartFromlocationName("torso", dataWatcher.getWatchableObjectString(21));
		armLeft = getBodyPartFromlocationName("armLeft", dataWatcher.getWatchableObjectString(22));
		armRight = getBodyPartFromlocationName("armRight", dataWatcher.getWatchableObjectString(23));
		leg = getBodyPartFromlocationName("legs", legType = dataWatcher.getWatchableObjectString(24));
		updateAttributes();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		updateBodyParts();
		par1NBTTagCompound.setString("head", getBodyPartsNames()[0]);
		par1NBTTagCompound.setString("body", getBodyPartsNames()[1]);
		par1NBTTagCompound.setString("armLeft", getBodyPartsNames()[2]);
		par1NBTTagCompound.setString("armRight", getBodyPartsNames()[3]);
		par1NBTTagCompound.setString("leg", getBodyPartsNames()[4]);
		par1NBTTagCompound.setBoolean("Saddle", getSaddled());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		head = getBodyPartFromlocationName("head", par1NBTTagCompound.getString("head"));
		torso = getBodyPartFromlocationName("torso", par1NBTTagCompound.getString("body"));
		armLeft = getBodyPartFromlocationName("armLeft", par1NBTTagCompound.getString("armLeft"));
		armRight = getBodyPartFromlocationName("armRight", par1NBTTagCompound.getString("armRight"));
		leg = getBodyPartFromlocationName("legs", par1NBTTagCompound.getString("leg"));
		setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
		dataWatcherUpdate();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (getOwner() != null)
			return par1Entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) getOwner()), 8);
		else
			return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 8);
	}

	@Override
	public boolean canBeSteered() {
		ItemStack var1 = ((EntityPlayer) riddenByEntity).getHeldItem();
		return var1 != null && var1.itemID == ItemGeneric.getItemStackFromName("Brain on a Stick").getItem().itemID;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		List<?> list = worldObj.selectEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(10D, 4.0D, 10D), null);
		Iterator<?> itr = list.iterator();
		while (itr.hasNext()) {
			Object obj = itr.next();
			if (obj instanceof EntityPlayer) {
				if (getOwner() != null) {
					NBTTagCompound nbt = getOwner().getEntityData();
					isAgressive = nbt.getBoolean("aggressive");
					if (nbt.getString(((EntityPlayer) obj).username).equals("enemy")) {
						setAttackTarget((EntityPlayer) obj);
					} else if (nbt.getString(((EntityPlayer) obj).username) == "" && isAgressive) {
						setAttackTarget((EntityPlayer) obj);
					}
				}
			}
		}
		if (rand.nextInt(100) == 0 || ticksExisted < 10) {
			if (!worldObj.isRemote) {
				dataWatcherUpdate();
			} else {
				updateBodyParts();
			}
		}
		if (ticksExisted < 10) {
			model.updateModel(this, true);
		}
		if (head == null) {
			this.setDead();
		}
	}

	public static BodyPart[] getBodyPartFromlocationName(String location, String name) {
		NecroEntityBase mob;
		if ((mob = NecroEntityRegistry.registeredEntities.get(name)) != null) {
			if (location.equals("head"))
				return mob.head == null ? mob.updateParts(ModelMinion.instance).head : mob.head;
				if (location.equals("torso"))
					return mob.torso == null ? mob.updateParts(ModelMinion.instance).torso : mob.torso;
					if (location.equals("armLeft"))
						return mob.armLeft == null ? mob.updateParts(ModelMinion.instance).armLeft : mob.armLeft;
						if (location.equals("armRight"))
							return mob.armRight == null ? mob.updateParts(ModelMinion.instance).armRight : mob.armRight;
							if (location.equals("legs"))
								return mob.legs == null ? mob.updateParts(ModelMinion.instance).legs : mob.legs;
		} else {
			System.err.println(location + " " + name + " not found!");
		}
		return null;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (!getSaddled() && !worldObj.isRemote && par1EntityPlayer.getHeldItem() != null && par1EntityPlayer.getHeldItem().getItem() == Item.saddle) {
			NecroEntityBase mob;
			if (torso != null && torso[0] != null && (mob = NecroEntityRegistry.registeredEntities.get(torso[0].name)) != null && mob instanceof ISaddleAble) {
				setSaddled(true);
				if (!par1EntityPlayer.capabilities.isCreativeMode) {
					par1EntityPlayer.inventory.consumeInventoryItem(Item.saddle.itemID);
				}
				return true;
			}
			return false;
		}
		if (this.getSaddled() && !worldObj.isRemote && (riddenByEntity == null || riddenByEntity == par1EntityPlayer) && (par1EntityPlayer.isSneaking() || riddenByEntity == par1EntityPlayer)) {
			ISaddleAble mob = (ISaddleAble) NecroEntityRegistry.registeredEntities.get(torso[0].name);
			par1EntityPlayer.mountEntity(this);
			if (riddenByEntity != null) {
				float lowestPoint = 0;
				float highestPoint = 0;
				for (Object model : leg[0].cubeList) {
					ModelBox cube = (ModelBox) model;
					if (cube.posY1 < lowestPoint) {
						lowestPoint = cube.posY1;
					}
					if (cube.posY2 > highestPoint) {
						highestPoint = cube.posY1;
					}
				}
				riddenByEntity.height = mob.riderHeight() + (highestPoint - lowestPoint);
			}
			return true;
		}
		if (riddenByEntity == null && par1EntityPlayer.username.equalsIgnoreCase(this.getOwnerName()) && !worldObj.isRemote) {
			aiSit.setSitting(!this.isSitting());
			isJumping = false;
			this.setPathToEntity((PathEntity) null);
			if (FMLCommonHandler.instance().getSide() != cpw.mods.fml.relauncher.Side.SERVER) {
				ClientProxy.mc.ingameGUI.getChatGUI().printChatMessage("Minion is " + (isSitting() ? "walking" : "sitting"));
			}
		} else if (riddenByEntity == null && !par1EntityPlayer.username.equalsIgnoreCase(this.getOwnerName()) && FMLCommonHandler.instance().getSide() == cpw.mods.fml.relauncher.Side.CLIENT && !worldObj.isRemote) {
			ClientProxy.mc.ingameGUI.getChatGUI().printChatMessage("<Minion> I obey only " + getOwnerName());
		}
		return true;
	}

	public boolean getSaddled() {
		return (dataWatcher.getWatchableObjectByte(25) & 1) != 0;
	}

	public void setSaddled(boolean par1) {
		if (par1) {
			dataWatcher.updateObject(25, Byte.valueOf((byte) 1));
		} else {
			dataWatcher.updateObject(25, Byte.valueOf((byte) 0));
		}
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	public void setBodyPart(String location, BodyPart[] bodypart) {
		if (location.equals("head")) {
			head = bodypart;
		} else if (location.equals("torso")) {
			torso = bodypart;
		} else if (location.equals("leg")) {
			leg = bodypart;
		} else if (location.equals("armLeft")) {
			armLeft = bodypart;
		} else if (location.equals("armRight")) {
			armRight = bodypart;
		} else {
			System.err.println("Trying to set an impossible body part!");
		}
		dataWatcherUpdate();
	}

	@Override
	public String toString() {
		return String.format("%s[\'%s\'/%d, l=\'%s\', x=%.1f, y=%.1f, z=%.1f, head=\'%s\', torso=\'%s\', armLeft=\'%s\', armRight=\'%s\', legs=\'%s\']", new Object[] { this.getClass().getSimpleName(), this.getEntityName(), Integer.valueOf(entityId), worldObj == null ? "~NULL~" : worldObj.getWorldInfo().getWorldName(), Double.valueOf(posX), Double.valueOf(posY), Double.valueOf(posZ), getBodyPartsNames()[0], getBodyPartsNames()[1], getBodyPartsNames()[2], getBodyPartsNames()[3], getBodyPartsNames()[4] });
	}

	public String[] getBodyPartsNames() {
		String list[] = { head != null && head.length > 0 && head[0] != null ? head[0].name : "UNDEFINED", torso != null && torso.length > 0 && torso[0] != null ? torso[0].name : "UNDEFINED", armLeft != null && armLeft.length > 0 && armLeft[0] != null ? armLeft[0].name : "UNDEFINED", armRight != null && armRight.length > 0 && armRight[0] != null ? armRight[0].name : "UNDEFINED", leg != null && leg.length > 0 && leg[0] != null ? leg[0].name : "UNDEFINED", };
		return list;
	}

	public BodyPart[][] getBodyParts() {
		BodyPart[][] list = { head, torso, armLeft, armRight, leg };
		return list;
	}

	public void onBodyChange() {
		if (model != null) {
			model.updateModel(this, isAltarMob);
		}
	}

	public ModelMinion getModel() {
		return model;
	}

	public void setModel(ModelMinion model) {
		this.model = model;
	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal var1) {
		return null;
	}

	public boolean isAltarMob() {
		return isAltarMob;
	}

	public void setAltarMob(boolean isAltarMob) {
		this.isAltarMob = isAltarMob;
	}

	public void setBodyParts(BodyPart[][] bodypart) {
		head = bodypart[0];
		torso = bodypart[1];
		armLeft = bodypart[2];
		armRight = bodypart[3];
		leg = bodypart[4];
		dataWatcherUpdate();
	}

	public void setAltar(TileEntityAltar tileEntityAltar) {
		altar = tileEntityAltar;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}

	@Override
	protected String getLivingSound() {
		return "mob." + aiMinion.getSound(this) + ".say";
	}

	@Override
	protected String getHurtSound() {
		return "mob." + aiMinion.getSound(this) + ".hurt";
	}

	@Override
	protected String getDeathSound() {
		return "mob." + aiMinion.getSound(this) + ".death";
	}

	@Override
	public void onDeath(DamageSource par1DamageSource) {
		getOwner().getEntityData().setInteger("minions", getOwner().getEntityData().getInteger("minions") - 1);
	}

	protected String legType = "";
	protected BodyPart[] head, torso, armLeft, armRight, leg;
	protected ModelMinion model = new ModelMinion();
	private boolean isAltarMob = true;
	private EntityAIMinion aiMinion = new EntityAIMinion(this);
	private final EntityAIControlledByPlayer aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.8F);
	public TileEntityAltar altar;
}
