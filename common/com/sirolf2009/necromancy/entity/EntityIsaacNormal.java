package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.lib.Reference;

public class EntityIsaacNormal extends EntityAnimal implements IMob, IRangedAttackMob {

    public EntityIsaacNormal(World par1World) {
        super(par1World);
        tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.25F, true));
        tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.22F, 32.0F));
        tasks.addTask(3, new EntityAIWander(this, 0.16F));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(5, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
        isImmuneToFire = true;
        setSize(0.6F, 1.8F);
        texture = Reference.LOC_RESOURCES_TEXTURES_ENTITIES + "/Isaac.png";
    }

    @Override
    public int getMaxHealth() {
        return 10;
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = par1DamageSource.getEntity();

            if (entity instanceof EntityPlayer)
            {
                entityToAttack = entity;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void attackEntityWithRangedAttack(EntityLiving par1EntityLiving, float par2) {
        EntityTear tear = new EntityTear(worldObj, this, par1EntityLiving, 1.6F, 14 - worldObj.difficultySetting * 4);
        worldObj.spawnEntityInWorld(tear);
    }
    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource par1DamageSource) {
        super.onDeath(par1DamageSource);
        if (!worldObj.isRemote) {
            EntityIsaacBlood isaac = new EntityIsaacBlood(worldObj);
            isaac.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
            worldObj.spawnEntityInWorld(isaac);
        }
    }
    

    
    @Override
    protected int getDropItemId() {
        return 0;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {}

    @Override
    protected void dropRareDrop(int par1) {}
    
    protected String getLivingSound() {
        return "";
    }
    protected String getHurtSound() {
        return "";
    }
    protected String getDeathSound() {
        return "";
    }
    protected void playStepSound(int par1, int par2, int par3, int par4) {}

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable) {
        return null;
    }

}
