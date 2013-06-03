package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.Necromancy;

public class EntityIsaacHead extends EntityIsaacBlood implements IRangedAttackMob, IMob {

    public EntityIsaacHead(World par1World) {
        super(par1World);
    }

    @Override
    public int getMaxHealth() {
        return 40;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource par1DamageSource) {
    }

    @Override
    protected int getDropItemId() {
        return Necromancy.isaacsHead.itemID;
    }
}
