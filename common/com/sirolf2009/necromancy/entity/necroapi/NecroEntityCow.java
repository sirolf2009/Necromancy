package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necromancy.client.model.ModelMinion;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntityCow extends com.sirolf2009.necroapi.NecroEntityQuadruped {

    public NecroEntityCow() {
        super("COW", 12);
        headItem = ItemBodyPart.getItemStackFromName("Cow Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Cow Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Cow Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Cow Legs", 1);
        texture = "/mob/cow.png";
    }

    @Override
    public BodyPart[] initHead(ModelMinion model) {
        BodyPart head = new BodyPart(this, model, 0, 0);
        head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 6, 0.0F);
        head.setTextureOffset(22, 0).addBox(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        head.setTextureOffset(22, 0).addBox(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        head.setTextureSize(textureWidth, textureHeight);
        return new BodyPart[] { head };
    }

    @Override
    public BodyPart[] initTorso(ModelMinion model) {
        float[] headPos = { 4.0F, 16 - size, -14.0F };
        float[] armLeftPos = { -1.0F, 12.0F, -10.0F };
        float[] armRightPos = { 5F, 12.0F, -10.0F };
        BodyPart body = new BodyPart(this, armLeftPos, armRightPos, headPos, model, 18, 4);
        body.addBox(-2.0F, -12.0F, -12.0F, 12, 18, 10, 0.0F);
        body.setTextureOffset(52, 0).addBox(2.0F, 2.0F, -13.0F, 4, 6, 1);
        body.setTextureSize(textureWidth, textureHeight);
        return new BodyPart[] { body };
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
