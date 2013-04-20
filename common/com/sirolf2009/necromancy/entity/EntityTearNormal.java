package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityTearNormal extends EntityArrow implements IProjectile {

    public EntityTearNormal(World par1World, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5) {
        super(par1World, par2EntityLiving, par3EntityLiving, par4, par5);
    }

    @Override
    public void setThrowableHeading(double d0, double d1, double d2, float f, float f1) {
    }

}
