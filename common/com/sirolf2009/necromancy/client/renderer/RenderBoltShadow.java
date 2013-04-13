package com.sirolf2009.necromancy.client.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.sirolf2009.necromancy.entity.EntityBoltShadow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBoltShadow extends Render {
    private float field_77002_a;

    public RenderBoltShadow(float par1) {
        field_77002_a = par1;
    }

    public void doRenderBolt(EntityBoltShadow par1Entity, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float var10 = field_77002_a;
        GL11.glScalef(var10 / 1.0F, var10 / 1.0F, var10 / 1.0F);
        byte var11 = 46;
        this.loadTexture("/gui/items.png");
        Tessellator var12 = Tessellator.instance;
        float var13 = (var11 % 16 * 16 + 0) / 256.0F;
        float var14 = (var11 % 16 * 16 + 16) / 256.0F;
        float var15 = (var11 / 16 * 16 + 0) / 256.0F;
        float var16 = (var11 / 16 * 16 + 16) / 256.0F;
        float var17 = 1.0F;
        float var18 = 0.5F;
        float var19 = 0.25F;
        GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        var12.startDrawingQuads();
        var12.setNormal(0.0F, 1.0F, 0.0F);
        var12.addVertexWithUV(0.0F - var18, 0.0F - var19, 0.0D, var13, var16);
        var12.addVertexWithUV(var17 - var18, 0.0F - var19, 0.0D, var14, var16);
        var12.addVertexWithUV(var17 - var18, 1.0F - var19, 0.0D, var14, var15);
        var12.addVertexWithUV(0.0F - var18, 1.0F - var19, 0.0D, var13, var15);
        var12.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method,
     * always casting down its argument and then handing it off to a worker
     * function which does the actual work. In all probabilty, the class Render
     * is generic (Render<T extends Entity) and this method has signature public
     * void doRender(T entity, double d, double d1, double d2, float f, float
     * f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderBolt((EntityBoltShadow) par1Entity, par2, par4, par6, par8, par9);
    }
}
