package com.sirolf2009.necromancy.client.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.sirolf2009.necromancy.item.ItemNecromancy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTearBlood extends Render {

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        Icon icon = ItemNecromancy.tearBlood;
        if (icon != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) par2, (float) par4, (float) par6);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.loadTexture("/gui/items.png");
            Tessellator tessellator = Tessellator.instance;

            if (icon == ItemPotion.func_94589_d("potion_splash")) {
                int i = PotionHelper.func_77915_a(((EntityPotion) par1Entity).getPotionDamage(), false);
                float f2 = (i >> 16 & 255) / 255.0F;
                float f3 = (i >> 8 & 255) / 255.0F;
                float f4 = (i & 255) / 255.0F;
                GL11.glColor3f(f2, f3, f4);
                GL11.glPushMatrix();
                this.func_77026_a(tessellator, ItemPotion.func_94589_d("potion_contents"));
                GL11.glPopMatrix();
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }

            this.func_77026_a(tessellator, icon);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
    }

    private void func_77026_a(Tessellator par1Tessellator, Icon par2Icon) {
        float f = par2Icon.getMinU();
        float f1 = par2Icon.getMaxU();
        float f2 = par2Icon.getMinV();
        float f3 = par2Icon.getMaxV();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
        par1Tessellator.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
        par1Tessellator.addVertexWithUV(f4 - f5, f4 - f6, 0.0D, f1, f2);
        par1Tessellator.addVertexWithUV(0.0F - f5, f4 - f6, 0.0D, f, f2);
        par1Tessellator.draw();
    }
}
