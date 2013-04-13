package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.NecroEntityQuadruped;
import com.sirolf2009.necromancy.client.model.ModelMinion;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntityPig extends NecroEntityQuadruped {

    public NecroEntityPig() {
        super("PIG", 6);
        headItem = ItemBodyPart.getItemStackFromName("Pig Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Pig Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Pig Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Pig Legs", 1);
        texture = "/mob/pig.png";
    }

    @Override
    public BodyPart[] initHead(ModelMinion model) {
        BodyPart head = new BodyPart(this, model, 0, 0);
        head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        head.setTextureSize(textureWidth, textureHeight);
        BodyPart snout = new BodyPart(this, model, 16, 16);
        snout.addBox(-2.0F, 0.0F, -5.0F, 4, 3, 1, 0.0F);
        snout.setTextureSize(textureWidth, textureHeight);
        return new BodyPart[] { head, snout };
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
}
