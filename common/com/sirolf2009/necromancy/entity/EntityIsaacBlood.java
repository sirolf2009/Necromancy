package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.lib.Reference;

public class EntityIsaacBlood extends EntityIsaacNormal implements IRangedAttackMob, IMob {

    public EntityIsaacBlood(World par1World) {
        super(par1World);
        texture = Reference.LOC_RESOURCES_TEXTURES_ENTITIES + "/IsaacBlood.png";
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLiving par1EntityLiving, float par2) {
        EntityTearBlood tear = new EntityTearBlood(worldObj, this, par1EntityLiving, 1.6F, 14 - worldObj.difficultySetting * 4);
        worldObj.spawnEntityInWorld(tear);
    }

    @Override
    public int getMaxHealth() {
        return 75;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource par1DamageSource) {
        if (!worldObj.isRemote) {
            EntityIsaacHead head = new EntityIsaacHead(worldObj);
            EntityIsaacBody body = new EntityIsaacBody(worldObj);
            head.setLocationAndAngles(posX, posY + 1, posZ, rotationYaw, rotationPitch);
            body.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
            worldObj.spawnEntityInWorld(head);
            worldObj.spawnEntityInWorld(body);
        }
    }

    @Override
    protected int getDropItemId() {
        return 0;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
    }

    @Override
    protected void dropRareDrop(int par1) {
    }

    @Override
    protected String getLivingSound() {
        return "";
    }

    @Override
    protected String getHurtSound() {
        return "";
    }

    @Override
    protected String getDeathSound() {
        return "";
    }

    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4) {
    }

}
