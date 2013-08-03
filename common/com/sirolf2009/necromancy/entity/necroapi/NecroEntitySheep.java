package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.BodyPartLocation;
import com.sirolf2009.necroapi.NecroEntityQuadruped;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntitySheep extends NecroEntityQuadruped {

    public NecroEntitySheep() {
        super("Sheep", 12);
        headItem = ItemBodyPart.getItemStackFromName("Sheep Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Sheep Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Sheep Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Sheep Legs", 1);
        texture = new ResourceLocation("textures/entity/sheep/sheep.png");
    }

    @Override
    public void initRecipes() {
        initDefaultRecipes(Block.cloth);
    }

    @Override
    public BodyPart[] initHead(ModelBase model) {
        BodyPart head = new BodyPart(this, model, 0, 0);
        head.addBox(-4.0F, -4.0F, -4.0F, 6, 6, 8, 0.0F);
        head.setTextureSize(textureWidth, textureHeight);
        return new BodyPart[] { head };
    }

    @Override
    public BodyPart[] initTorso(ModelBase model) {
        float[] headPos = { 4.0F, 12 - size, -14.0F };
        float[] armLeftPos = { -1.0F, 6.0F, -10.0F };
        float[] armRightPos = { 5F, 6.0F, -10.0F };
        BodyPart torso = new BodyPart(this, armLeftPos, armRightPos, headPos, model, 28, 8);
        torso.addBox(0.0F, -10.0F, -6.0F, 8, 16, 6, 0.0F);
        torso.setTextureSize(textureWidth, textureHeight);
        return new BodyPart[] { torso };
    }
    
    @Override
	public void setAttributes(EntityLivingBase minion, BodyPartLocation location) {
		if(location == BodyPartLocation.Head) {
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D); //health
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0D); //followrange
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D); //speed
			head[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); //damage
		} else if(location == BodyPartLocation.Torso) {
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0D); //health
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D); //speed
			torso[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); //damage
		} else if(location == BodyPartLocation.ArmLeft) {
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D); //health
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0575D); //speed
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); //damage
		} else if(location == BodyPartLocation.ArmRight) {
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D); //health
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0575D); //speed
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); //damage
		} else if(location == BodyPartLocation.Legs) {
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D); //health
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.115D); //speed
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); //damage
		}
	}
}
