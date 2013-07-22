package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityIsaacBlood extends EntityIsaacNormal implements IRangedAttackMob, IMob {

    public EntityIsaacBlood(World par1World) {
        super(par1World);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }
    
    protected void func_110147_ax() {
        super.func_110147_ax();
        // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(75.0D);
        // Follow Range - default 32.0D - min 0.0D - max 2048.0D
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
        // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D);
        // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
        // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving, float par2) {
        EntityTear tear = new EntityTear(worldObj, this, par1EntityLiving, 1.6F, (float)(14 - worldObj.difficultySetting * 4));
        worldObj.spawnEntityInWorld(tear);
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
