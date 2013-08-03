package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import com.sirolf2009.necroapi.BodyPartLocation;
import com.sirolf2009.necroapi.ISkull;
import com.sirolf2009.necroapi.NecroEntityBiped;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntityPigZombie extends NecroEntityBiped {

    public NecroEntityPigZombie() {
        super("Pig Zombie");
        headItem = ItemBodyPart.getItemStackFromName("Pigzombie Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Pigzombie Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Pigzombie Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Pigzombie Legs", 1);
        texture = new ResourceLocation("textures/entity/zombie_pigman.png");
        textureHeight = 64;
    }

    @Override
    public void initRecipes() {
        initDefaultRecipes(Item.porkCooked);
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
			armLeft[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(2.5D); //damage
		} else if(location == BodyPartLocation.ArmRight) {
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(2.0D); //health
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D); //speed
			armRight[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(2.5D); //damage
		} else if(location == BodyPartLocation.Legs) {
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111267_a).func_111128_a(2.0D); //health
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D); //followrange
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D); //knockback res
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D); //speed
			legs[0].attributes.func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); //damage
		}
	}
}
