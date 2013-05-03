package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityIsaacBody extends EntityIsaacBlood implements IRangedAttackMob, IMob {

    public EntityIsaacBody(World par1World) {
        super(par1World);
    }

    @Override
    public int getMaxHealth() {
        return 30;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource par1DamageSource) {
    }
}
