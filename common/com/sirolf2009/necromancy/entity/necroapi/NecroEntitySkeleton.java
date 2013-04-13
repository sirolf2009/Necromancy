package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.NecroEntityBiped;
import com.sirolf2009.necromancy.client.model.ModelMinion;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntitySkeleton extends NecroEntityBiped {

    public NecroEntitySkeleton() {
        super("SKELETON");
        headItem = new ItemStack(Item.skull, 1, 0);
        torsoItem = ItemBodyPart.getItemStackFromName("Skeleton Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Skeleton Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Skeleton Legs", 1);
        /*
         * headRecipe = new Object[] {"SSSS", "SBFS", "SEES", 'S', new
         * ItemStack(organs, 1, 4), //skin 'E', Item.spiderEye, 'F', Item.bone,
         * 'B', new ItemStack(organs, 1, 0)}; //brains
         */texture = "/mob/skeleton.png";
    }

    @Override
    public BodyPart[] initArmLeft(ModelMinion model) {
        BodyPart armLeft = new BodyPart(this, model, 40, 16);
        armLeft.addBox(2.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        armLeft.mirror = true;
        return new BodyPart[] { armLeft };
    }

    @Override
    public BodyPart[] initArmRight(ModelMinion model) {
        BodyPart armRight = new BodyPart(this, model, 40, 16);
        armRight.addBox(0.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        return new BodyPart[] { armRight };
    }

    @Override
    public BodyPart[] initLegs(ModelMinion model) {
        float[] torsoPos = { -4F, -2F, -2F };
        BodyPart legLeft = new BodyPart(this, torsoPos, model, 0, 16);
        legLeft.addBox(-3.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        legLeft.setRotationPoint(0.0F, 12.0F, 0.0F);
        BodyPart legRight = new BodyPart(this, torsoPos, model, 0, 16);
        legRight.addBox(1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        legRight.setRotationPoint(0.0F, 12.0F, 0.0F);
        legLeft.mirror = true;
        return new BodyPart[] { legLeft, legRight };
    }

}
