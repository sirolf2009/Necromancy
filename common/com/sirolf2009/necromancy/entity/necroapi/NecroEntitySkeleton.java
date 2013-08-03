package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.BodyPartLocation;
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
        texture = new ResourceLocation("textures/entity/skeleton/skeleton.png");
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

    @Override
	public void setAttributes(EntityLivingBase minion, BodyPartLocation location) {
		if(location == BodyPartLocation.Head) {
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(2.0D); //health
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0D); //followrange
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D); //speed
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); //damage
		} else if(location == BodyPartLocation.Torso) {
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(12.0D); //health
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D); //speed
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); //damage
		} else if(location == BodyPartLocation.ArmLeft) {
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(2.0D); //health
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D); //speed
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D); //damage
		} else if(location == BodyPartLocation.ArmRight) {
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(2.0D); //health
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D); //speed
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D); //damage
		} else if(location == BodyPartLocation.Legs) {
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(2.0D); //health
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D); //speed
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); //damage
		}
	}
}
