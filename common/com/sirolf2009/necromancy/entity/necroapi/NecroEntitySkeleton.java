package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.ISkull;
import com.sirolf2009.necroapi.NecroEntityBiped;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntitySkeleton extends NecroEntityBiped implements ISkull {

    public NecroEntitySkeleton() {
        super("Skeleton");
        headItem = new ItemStack(Item.skull);
        torsoItem = ItemBodyPart.getItemStackFromName("Skeleton Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Skeleton Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Skeleton Legs", 1);
        texture = new ResourceLocation("/mob/skeleton.png");
    }

    @Override
    public void initRecipes() {
        initDefaultRecipes(Item.bone);
    }

    @Override
    public BodyPart[] initArmLeft(ModelBase model) {
        BodyPart armLeft = new BodyPart(this, model, 40, 16);
        armLeft.addBox(2.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        armLeft.mirror = true;
        return new BodyPart[] { armLeft };
    }

    @Override
    public BodyPart[] initArmRight(ModelBase model) {
        BodyPart armRight = new BodyPart(this, model, 40, 16);
        armRight.addBox(0.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        return new BodyPart[] { armRight };
    }

    @Override
    public BodyPart[] initLegs(ModelBase model) {
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

    @Override
    public String getSkullModelTexture() {
        return "/mob/skeleton.png";
    }

    @Override
    public String getSkullIconTexture() {
        return "skull_skeleton";
    }

}
