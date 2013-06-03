package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.item.Item;

import com.sirolf2009.necroapi.ISkull;
import com.sirolf2009.necroapi.NecroEntityBiped;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntityPigZombie extends NecroEntityBiped implements ISkull {

    public NecroEntityPigZombie() {
        super("Pigzombie");
        headItem = ItemBodyPart.getItemStackFromName("Pigzombie Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Pigzombie Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Pigzombie Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Pigzombie Legs", 1);
        texture = "/mob/pigzombie.png";
        textureHeight = 64;
    }

    @Override
    public void initRecipes() {
        initDefaultRecipes(Item.porkCooked);
    }

    @Override
    public String getSkullModelTexture() {
        return "/mob/pigzombie.png";
    }

    @Override
    public String getSkullIconTexture() {
        return "necromancy:bodypart/Pigzombie/Head";
    }
}
