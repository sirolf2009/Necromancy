package com.sirolf2009.necromancy.client.renderer.tileentity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.item.ItemNecroSkull;

public class TileEntityNecroSkullRenderer extends TileEntitySkullRenderer {

    private Map<Integer, Map> width = new HashMap<Integer, Map>();
    private Map<Integer, ModelSkeletonHead> Height = new HashMap<Integer, ModelSkeletonHead>();
    private ModelSkeletonHead skull = new ModelSkeletonHead(0, 0, 64, 32);
    private ModelSkeletonHead skull2 = new ModelSkeletonHead(0, 0, 64, 64);

    @Override
    public void func_82393_a(float par1, float par2, float par3, int par4, float par5, int par6, String par7Str) {
        ModelSkeletonHead modelskeletonhead;
        if ((modelskeletonhead = getSkullModel(par6)) == null)
            return;

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);

        if (par4 != 1) {
            switch (par4) {
                case 2:
                    GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.74F);
                    break;
                case 3:
                    GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.26F);
                    par5 = 180.0F;
                    break;
                case 4:
                    GL11.glTranslatef(par1 + 0.74F, par2 + 0.25F, par3 + 0.5F);
                    par5 = 270.0F;
                    break;
                case 5:
                default:
                    GL11.glTranslatef(par1 + 0.26F, par2 + 0.25F, par3 + 0.5F);
                    par5 = 90.0F;
            }
        } else {
            GL11.glTranslatef(par1 + 0.5F, par2, par3 + 0.5F);
        }

        float f4 = 0.0625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        modelskeletonhead.render((Entity) null, 0.0F, 0.0F, 0.0F, par5, 0.0F, f4);
        GL11.glPopMatrix();
    }

    public ModelSkeletonHead getSkullModel(int meta) {
        bindTextureByName(ItemNecroSkull.modelTextures[meta]);
        BufferedImage bimg;
        try {
            bimg = ImageIO.read(Necromancy.class.getResource(ItemNecroSkull.modelTextures[meta]));
            int width2 = bimg.getWidth();
            int height2 = bimg.getHeight();
            if (width.containsKey(width2))
                if (width.get(width2).containsKey(height2))
                    return (ModelSkeletonHead) width.get(width2).get(height2);
            Height.put(height2, new ModelSkeletonHead(0, 0, width2, height2));
            width.put(width2, Height);
            return (ModelSkeletonHead) width.get(width2).get(height2);
        } catch (IOException e) {
            Necromancy.loggerNecromancy.log(Level.SEVERE, "Skull texture for " + ItemNecroSkull.skullTypes[meta] + " not found");
        }
        return null;
    }
}
