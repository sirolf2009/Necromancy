package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityTearBlood extends EntityTear {

    public EntityTearBlood(World par1World) {
        super(par1World);
        setDamage(7);
    }

    public EntityTearBlood(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        setDamage(7);
    }

    public EntityTearBlood(World par1World, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5) {
        super(par1World, par2EntityLiving, par3EntityLiving, par4, par5);
        setDamage(7);
    }

    public EntityTearBlood(World par1World, EntityLiving par2EntityLiving, float par3) {
        super(par1World, par2EntityLiving, par3);
        setDamage(7);
    }

}
