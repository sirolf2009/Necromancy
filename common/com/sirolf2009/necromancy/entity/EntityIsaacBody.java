package com.sirolf2009.necromancy.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.lib.ReferenceNecromancy;

public class EntityIsaacBody extends EntityMob implements IMob {

    public EntityIsaacBody(World par1World) {
        super(par1World);
        isImmuneToFire = true;
        setSize(0.6F, 1.8F);
        moveSpeed = 0.3F;
        texture = ReferenceNecromancy.LOC_RESOURCES_TEXTURES_ENTITIES + "/Isaac.png";
    }

    @Override
    public int getMaxHealth() {
        return 50;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource par1DamageSource) {
    }
}
