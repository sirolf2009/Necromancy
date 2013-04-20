package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.Necromancy;

public class EntityIsaacBlood extends EntityMob implements IRangedAttackMob {

    public EntityIsaacBlood(World par1World) {
        super(par1World);
        tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.22F, 32.0F));
        tasks.addTask(2, new EntityAIWander(this, 0.16F));
        tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(4, new EntityAIArrowAttack(this, moveSpeed, 20, 60, 15.0F));
        tasks.addTask(5, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        setSize(0.6F, 1.8F);
        texture = Necromancy.rscPath + "/entity/IsaacBlood.png";
    }
    
    public boolean isAIEnabled()
    {
             return true;
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLiving par1EntityLiving, float par2) {
        System.out.println("shooting tear");
        EntityTearNormal tear = new EntityTearNormal(this.worldObj, this, par1EntityLiving, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4));
        worldObj.spawnEntityInWorld(tear);
    }

    @Override
    public int getMaxHealth() {
        return 10;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource par1DamageSource) {
        super.onDeath(par1DamageSource);
        // TODO spawn head and body
    }

}
