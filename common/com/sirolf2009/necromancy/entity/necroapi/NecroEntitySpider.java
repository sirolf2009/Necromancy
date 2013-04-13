package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.ISaddleAble;
import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necromancy.client.model.ModelMinion;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntitySpider extends NecroEntityBase implements ISaddleAble {

    public NecroEntitySpider() {
        super("SPIDER");
        headItem = ItemBodyPart.getItemStackFromName("Spider Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Spider Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Spider Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Spider Legs", 1);
        texture = "/mob/spider.png";
        hasArms = false;
    }

    @Override
    public BodyPart[] initHead(ModelMinion model) {
        BodyPart spiderHead = new BodyPart(this, model, 32, 4);
        spiderHead.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        return new BodyPart[] { spiderHead };
    }

    @Override
    public BodyPart[] initTorso(ModelMinion model) {
        float[] headPos = { 4.0F, 8, -10.0F };
        float[] armLeftPos = { -1.0F, 12.0F, -10.0F };
        float[] armRightPos = { 5F, 12.0F, -10.0F };
        BodyPart spiderNeck = new BodyPart(this, armLeftPos, armRightPos, headPos, model, 0, 0);
        spiderNeck.addBox(1.0F, 5.0F, -6.0F, 6, 6, 6, 0.0F);
        BodyPart spiderBody = new BodyPart(this, armLeftPos, armRightPos, headPos, model, 0, 12);
        spiderBody.addBox(-1.0F, 4.0F, 0.0F, 10, 8, 12, 0.0F);
        return new BodyPart[] { spiderBody, spiderNeck };
    }

    @Override
    public BodyPart[] initLegs(ModelMinion model) {
        float[] torsoPos = { -4F, -2F, 0F };
        BodyPart spiderLeg1 = new BodyPart(this, torsoPos, model, 18, 0);
        spiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        spiderLeg1.setRotationPoint(-4.0F, 15.0F, 2.0F);
        BodyPart spiderLeg2 = new BodyPart(this, torsoPos, model, 18, 0);
        spiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        spiderLeg2.setRotationPoint(4.0F, 15.0F, 2.0F);
        BodyPart spiderLeg3 = new BodyPart(this, torsoPos, model, 18, 0);
        spiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        spiderLeg3.setRotationPoint(-4.0F, 15.0F, 1.0F);
        BodyPart spiderLeg4 = new BodyPart(this, torsoPos, model, 18, 0);
        spiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        spiderLeg4.setRotationPoint(4.0F, 15.0F, 1.0F);
        BodyPart spiderLeg5 = new BodyPart(this, torsoPos, model, 18, 0);
        spiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        spiderLeg5.setRotationPoint(-4.0F, 15.0F, 0.0F);
        BodyPart spiderLeg6 = new BodyPart(this, torsoPos, model, 18, 0);
        spiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        spiderLeg6.setRotationPoint(4.0F, 15.0F, 0.0F);
        BodyPart spiderLeg7 = new BodyPart(this, torsoPos, model, 18, 0);
        spiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        spiderLeg7.setRotationPoint(-4.0F, 15.0F, -1.0F);
        BodyPart spiderLeg8 = new BodyPart(this, torsoPos, model, 18, 0);
        spiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        spiderLeg8.setRotationPoint(4.0F, 15.0F, -1.0F);
        return new BodyPart[] { spiderLeg1, spiderLeg2, spiderLeg3, spiderLeg4, spiderLeg5, spiderLeg6, spiderLeg7, spiderLeg8 };
    }

    @Override
    public BodyPart[] initArmLeft(ModelMinion model) {
        return null;
    }

    @Override
    public BodyPart[] initArmRight(ModelMinion model) {
        return null;
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
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity, BodyPart[] part, String location) {
        if (location.equals("legs")) {
            float var8 = (float) Math.PI / 4F;
            part[0].rotateAngleZ = -var8;
            part[1].rotateAngleZ = var8;
            part[2].rotateAngleZ = -var8 * 0.74F;
            part[3].rotateAngleZ = var8 * 0.74F;
            part[4].rotateAngleZ = -var8 * 0.74F;
            part[5].rotateAngleZ = var8 * 0.74F;
            part[6].rotateAngleZ = -var8;
            part[7].rotateAngleZ = var8;
            float var9 = -0.0F;
            float var10 = 0.3926991F;
            part[0].rotateAngleY = var10 * 2.0F + var9;
            part[1].rotateAngleY = -var10 * 2.0F - var9;
            part[2].rotateAngleY = var10 * 1.0F + var9;
            part[3].rotateAngleY = -var10 * 1.0F - var9;
            part[4].rotateAngleY = -var10 * 1.0F + var9;
            part[5].rotateAngleY = var10 * 1.0F - var9;
            part[6].rotateAngleY = -var10 * 2.0F + var9;
            part[7].rotateAngleY = var10 * 2.0F - var9;
            float var11 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
            float var12 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * par2;
            float var13 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + (float) Math.PI / 2F) * 0.4F) * par2;
            float var14 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + (float) Math.PI * 3F / 2F) * 0.4F) * par2;
            float var15 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
            float var16 = Math.abs(MathHelper.sin(par1 * 0.6662F + (float) Math.PI) * 0.4F) * par2;
            float var17 = Math.abs(MathHelper.sin(par1 * 0.6662F + (float) Math.PI / 2F) * 0.4F) * par2;
            float var18 = Math.abs(MathHelper.sin(par1 * 0.6662F + (float) Math.PI * 3F / 2F) * 0.4F) * par2;
            part[0].rotateAngleY += var11;
            part[1].rotateAngleY += -var11;
            part[2].rotateAngleY += var12;
            part[3].rotateAngleY += -var12;
            part[4].rotateAngleY += var13;
            part[5].rotateAngleY += -var13;
            part[6].rotateAngleY += var14;
            part[7].rotateAngleY += -var14;
            part[0].rotateAngleZ += var15;
            part[1].rotateAngleZ += -var15;
            part[2].rotateAngleZ += var16;
            part[3].rotateAngleZ += -var16;
            part[4].rotateAngleZ += var17;
            part[5].rotateAngleZ += -var17;
            part[6].rotateAngleZ += var18;
            part[7].rotateAngleZ += -var18;
        }
    }

    @Override
    public String getSaddleTex() {
        return "/necromancy/spiderSaddle.png";
    }

    @Override
    public int riderHeight() {
        return 0;
    }
}
