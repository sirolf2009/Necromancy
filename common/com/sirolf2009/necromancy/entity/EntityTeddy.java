package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.core.proxy.ClientProxy;
import com.sirolf2009.necromancy.lib.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class EntityTeddy extends EntityTameable {

    enum EntityState {
        WALKING, DEFENDING, SITTING
    };

    EntityState entityState;

    public EntityTeddy(World par1World) {
        super(par1World);
        setTamed(true);
        setSitting(true);
        moveSpeed = 0.3F;
        texture = Reference.LOC_RESOURCES_TEXTURES_ENTITIES + "/teddy.png";
        setSize(0.6F, 0.8F);
        setAIMoveSpeed(0.15F);
        entityState = EntityState.WALKING;
        getNavigator().setAvoidsWater(true);
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, aiSit);
        tasks.addTask(3, new EntityAIFollowOwner(this, 0.3F, 8F, 5F));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10F));
        tasks.addTask(6, new EntityAIScareEntities(this, 10F, 7F, moveSpeed, EntityMob.class));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("state", getStateIndex());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setStateIndex(par1NBTTagCompound.getInteger("state"));
    }

    protected int getStateIndex() {
        switch (entityState) {
            case WALKING:
                return 0;
            case DEFENDING:
                return 1;
            case SITTING:
                return 2;
        }
        return -1;
    }

    protected void setStateIndex(int index) {
        switch (index) {
            case 0:
                entityState = EntityState.WALKING;
            case 1:
                entityState = EntityState.DEFENDING;
            case 2:
                entityState = EntityState.SITTING;
        }
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public int getMaxHealth() {
        return 8;
    }

    @Override
    protected String getLivingSound() {
        return "mob.zombie";
    }

    @Override
    protected String getHurtSound() {
        return "mob.zombiehurt";
    }

    @Override
    protected String getDeathSound() {
        return "mob.zombiedeath";
    }

    @Override
    protected int getDropItemId() {
        return Item.leather.itemID;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3);
    }

    @Override
    public boolean interact(EntityPlayer par1EntityPlayer) {
        setOwner(par1EntityPlayer.username);

        switch (entityState) {
            case WALKING:
                entityState = EntityState.DEFENDING;
                setSitting(true);
                moveSpeed = 0.1F;
                break;

            case DEFENDING:
                entityState = EntityState.SITTING;
                setSitting(true);
                break;

            case SITTING:
                entityState = EntityState.WALKING;
                setSitting(false);
                moveSpeed = 0.3F;
                break;
        }
        if (getOwner() instanceof EntityPlayerMP && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            ClientProxy.mc.thePlayer.addChatMessage(new StringBuilder().append("Animated Teddy is now ").append(entityState).toString().toLowerCase());
        }
        return true;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }

}
