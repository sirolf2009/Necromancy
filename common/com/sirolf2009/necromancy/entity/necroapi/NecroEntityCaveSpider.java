package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necromancy.item.ItemBodyPart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NecroEntityCaveSpider extends NecroEntitySpider {

    public NecroEntityCaveSpider() {
        super("CaveSpider");
        headItem = ItemBodyPart.getItemStackFromName("Cave_Spider Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Cave_Spider Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Cave_Spider Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Cave_Spider Legs", 1);
        texture = new ResourceLocation("/mob/cavespider.png");
    }

    @Override
    public void initRecipes() {
        initDefaultRecipes(Item.silk);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preRender(Entity entity, BodyPart[] parts, String location, ModelBase model) {
        GL11.glPushMatrix();
        GL11.glScalef(0.7F, 0.7F, 0.7F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void postRender(Entity entity, BodyPart[] parts, String location, ModelBase model) {
        GL11.glPopMatrix();
    }

}
