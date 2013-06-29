package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.lib.ReferenceNecromancy;

public class EntityNightCrawler extends EntityMob {

    public EntityNightCrawler(World par1World) {
        super(par1World);
        setSize(0.6F, 1F);
        texture = ReferenceNecromancy.LOC_RESOURCES_TEXTURES_ENTITIES + "/nightcrawler.png";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (entityToAttack != null && rand.nextInt(50) == 0) {
            worldObj.playSoundEffect(posX, posY, posZ, "nightcrawler.scream", 1.0F, 1.0F);
            worldObj.playSoundEffect(posX, posY, posZ, "nightcrawler.howl", 1.0F, 1.0F);
        }
        findPlayerToAttack();
        if (entityToAttack != null && worldObj.isRemote) {
            ((EntityLiving) entityToAttack).getLookHelper().setLookPositionWithEntity(this, 10.0F, ((EntityLiving) entityToAttack).getVerticalFaceSpeed());
            entityToAttack.rotationYaw = getAngle(entityToAttack.posX, posX, entityToAttack.posZ, posZ);
            ((EntityLiving) entityToAttack).getLookHelper().onUpdateLook();
            worldObj.spawnParticle("portal", entityToAttack.posX + (rand.nextDouble() - 0.5D) / 2, entityToAttack.posY + (rand.nextDouble() - 2D) / 2, entityToAttack.posZ + (rand.nextDouble() - 0.5D) / 2, (entityToAttack.posX - posX) * 0.01, (entityToAttack.posY - posY) * 0.01, (entityToAttack.posZ - posZ) * 0.01);
            worldObj.spawnParticle("portal", posX + (rand.nextDouble() - 0.5D) / 2, posY + (rand.nextDouble() - 2D) / 2, posZ + (rand.nextDouble() - 0.5D) / 2, (posX - entityToAttack.posX) * 0.01, (posY - entityToAttack.posY) * 0.01, (posZ - entityToAttack.posZ) * 0.01);
        }
    }

    public float getAngle(double x1, double x2, double y1, double y2) {
        float angle1 = (float) (x1 > x2 ? x1 - x2 : x1 - x2);
        float angle2 = (float) (y1 > y2 ? y2 - y1 : y2 - y1);
        float angle = (float) Math.toDegrees(Math.atan2(angle1, angle2));
        return angle;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
        super.attackEntityFrom(par1DamageSource, par2);
        Entity entity = par1DamageSource.getEntity();
        if (entity instanceof EntityPlayer) {
            entityToAttack = entity;
        }
        return true;
    }

    @Override
    protected int getDropItemId() {
        return Item.enderPearl.itemID;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return "nightcrawler.say1";
    }

    @Override
    public int getMaxHealth() {
        return 20;
    }

}
