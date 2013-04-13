package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.NecroEntityBiped;
import com.sirolf2009.necromancy.client.model.ModelMinion;
import com.sirolf2009.necromancy.entity.EntityMinion;

/**
 * TODO Not used ATM will contain the texture of the player using the altar
 */
public class NecroEntityPlayer extends NecroEntityBiped {

    public NecroEntityPlayer() {
        super("PLAYER");
        headItem = new ItemStack(Item.diamond, 1);
        torsoItem = new ItemStack(Item.diamond, 1);
        armItem = new ItemStack(Item.diamond, 1);
        legItem = new ItemStack(Item.diamond, 1);
        texture = "/mob/char.png";
    }

    @Override
    public void preRender(Entity entity, BodyPart[] parts, String location, ModelMinion model) {
        texture = ((EntityMinion) entity).isAltarMob() ? ((EntityMinion) entity).altar.lastUser != null ? ((EntityMinion) entity).altar.lastUser.skinUrl : "/mob/char.png" : "/mob/char.png";
    }
}
