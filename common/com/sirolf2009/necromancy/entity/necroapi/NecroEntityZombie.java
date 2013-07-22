package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.sirolf2009.necroapi.ISkull;
import com.sirolf2009.necroapi.NecroEntityBiped;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntityZombie extends NecroEntityBiped implements ISkull {

    public NecroEntityZombie() {
        super("Zombie");
        headItem = new ItemStack(Item.skull, 1, 2);
        torsoItem = ItemBodyPart.getItemStackFromName("Zombie Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Zombie Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Zombie Legs", 1);
        texture = new ResourceLocation("/mob/zombie.png");
        textureHeight = 64;
    }

    @Override
    public void initRecipes() {
        initDefaultRecipes(Item.rottenFlesh);
    }

    @Override
    public String getSkullModelTexture() {
        return "/mob/zombie.png";
    }

    @Override
    public String getSkullIconTexture() {
        return "skull_zombie";
    }
}
