package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necromancy.item.ItemBodyPart;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NecroEntityMooshroom extends NecroEntityCow {

    public NecroEntityMooshroom() {
        super("Mooshroom", 12);
        headItem = ItemBodyPart.getItemStackFromName("Mooshroom Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Mooshroom Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Mooshroom Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Mooshroom Legs", 1);
        texture = "/mob/redcow.png";
    }

    @Override
    public void initRecipes() {
        initDefaultRecipes(Block.mushroomRed);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preRender(Entity entity, BodyPart[] parts, String location, ModelBase model) {
        RenderBlocks renderBlocks = new RenderBlocks();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/terrain.png");
        GL11.glEnable(GL11.GL_CULL_FACE);
        if (location.equals("torso")) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0F, -1.0F, 1.0F);
            GL11.glTranslatef(0.4F, 0.4F, 0.0F);
            GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
            renderBlocks.renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
            GL11.glTranslatef(0.1F, 0.0F, -0.4F);
            GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
            renderBlocks.renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
            GL11.glPopMatrix();
        }
        if (location.equals("head")) {
            GL11.glPushMatrix();
            head[0].postRender(0.0625F);
            GL11.glScalef(1.0F, -1.0F, 1.0F);
            GL11.glTranslatef(0.0F, 0.75F, -0.1F);
            GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);
            renderBlocks.renderBlockAsItem(Block.mushroomRed, 0, 1.0F); // head
            GL11.glPopMatrix();
        }
        GL11.glDisable(GL11.GL_CULL_FACE);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
    }

}
