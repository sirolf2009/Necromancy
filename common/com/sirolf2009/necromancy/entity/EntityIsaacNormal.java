package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.lib.Reference;

public class EntityIsaacNormal extends EntityMob implements IMob, IRangedAttackMob {

    public EntityIsaacNormal(World par1World) {
        super(par1World);
        isImmuneToFire = true;
        setSize(0.6F, 1.8F);
        moveSpeed = 0.25F;
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIRestrictSun(this));
        tasks.addTask(3, new EntityAIFleeSun(this, moveSpeed));
        tasks.addTask(5, new EntityAIWander(this, moveSpeed));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(6, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
        texture = Reference.LOC_RESOURCES_TEXTURES_ENTITIES + "/Isaac.png";
        if (!par1World.isRemote) {
            tasks.addTask(1, new EntityAIArrowAttack(this, moveSpeed, 18, 50F));
        }
    }

    @Override
    public int getMaxHealth() {
        return 20;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled() {
        return true;
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
}
