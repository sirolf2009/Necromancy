package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.sirolf2009.necroapi.ISkull;
import com.sirolf2009.necroapi.NecroEntityBiped;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntityPigZombie extends NecroEntityBiped implements ISkull {

    public NecroEntityPigZombie() {
        super("PIGZOMBIE");
        headItem = ItemBodyPart.getItemStackFromName("Pigzombie Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Pigzombie Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Pigzombie Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Pigzombie Legs", 1);
        texture = "/mob/pigzombie.png";
        textureHeight = 64;
    }

    @Override
    public void initRecipes() {
        headRecipe = new Object[] { "SSSS", "SBFS", "SEES", 'S', new ItemStack(organs, 1, 4), // skin
        'E', Item.spiderEye, 'F', Item.rottenFlesh, 'B', new ItemStack(organs, 1, 0) }; // brains
        torsoRecipe = new Object[] { " LL ", "BHUB", "LEEL", "BLLB", 'L', new ItemStack(organs, 1, 4), // skin
        'E', Item.rottenFlesh, 'H', new ItemStack(organs, 1, 1), // heart
        'U', new ItemStack(organs, 1, 3), // lungs
        'B', Item.bone };
        armRecipe = new Object[] { "LLLL", "BMEB", "LLLL", 'L', new ItemStack(organs, 1, 4), // skin
        'E', Item.rottenFlesh, 'M', new ItemStack(organs, 1, 2), // muscle
        'B', Item.bone };
        legRecipe = new Object[] { "LBBL", "LMML", "LEEL", "LBBL", 'L', new ItemStack(organs, 1, 4), // skin
        'E', Item.rottenFlesh, 'M', new ItemStack(organs, 1, 2), // muscle
        'B', Item.bone };
    }

    @Override
    public String getSkullTexture() {
        return "mob/pigzombie.png";
    }
}
