package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntitySlimeSmall extends NecroEntityBase {

    public NecroEntitySlimeSmall() {
        super("SLIME_SMALL");
        headItem = ItemBodyPart.getItemStackFromName("Small Slime Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Small Slime Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Small Slime Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Small Slime Legs", 1);
        texture = new ResourceLocation("/mob/slime.png");
    }

    @Override
    public BodyPart[] initHead(ModelBase model) {
        BodyPart slimeBodies = new BodyPart(this, model, 0, 0);
        slimeBodies.addBox(-4.0F, 16.0F, -4.0F, 8, 8, 8);
        BodyPart head = new BodyPart(this, model, 0, 16);
        head.addBox(-3.0F, 17.0F, -3.0F, 6, 6, 6);
        BodyPart slimeRightEye = new BodyPart(this, model, 32, 0);
        slimeRightEye.addBox(-3.25F, 18.0F, -3.5F, 2, 2, 2);
        BodyPart slimeLeftEye = new BodyPart(this, model, 32, 4);
        slimeLeftEye.addBox(1.25F, 18.0F, -3.5F, 2, 2, 2);
        BodyPart slimeMouth = new BodyPart(this, model, 32, 8);
        slimeMouth.addBox(0.0F, 21.0F, -3.5F, 1, 1, 1);
        head.setTextureSize(textureWidth, textureHeight);
        return new BodyPart[] { slimeBodies, head, slimeRightEye, slimeLeftEye, slimeMouth };
    }

    @Override
    public BodyPart[] initTorso(ModelBase model) {
        float[] headPos = { 4.0F, 16, -14.0F };
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
