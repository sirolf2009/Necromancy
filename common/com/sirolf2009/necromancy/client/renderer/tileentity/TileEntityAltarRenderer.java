package com.sirolf2009.necromancy.client.renderer.tileentity;

import java.util.Random;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.client.model.ModelAltar;
import com.sirolf2009.necromancy.entity.EntityMinion;
import com.sirolf2009.necromancy.tileentity.TileEntityAltar;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityAltarRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    public TileEntityAltarRenderer() {
        entity = null;
        model = new ModelAltar();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        if (tileentity instanceof TileEntityAltar) {
            renderAltar((TileEntityAltar) tileentity, d, d1, d2, f);
            TileEntityAltar altar = (TileEntityAltar) tileentity;

            entity = altar.getMinion();

            if (altar.hasContainerChanged()) {
                entity = (EntityMinion) altar.getPreviewEntity();
                entity.getModel().updateModel(entity, true);
            }

            int i = 0;
            if (altar.worldObj != null) {
                i = altar.getBlockMetadata();
            }
            if (entity != null) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float) d + 1.5F, (float) d1 + 1.0F, (float) d2 + 0.5F);
                switch (i) {
                    case 2: // '\0'
                        GL11.glTranslatef(-1F, 0.2F, -2.2F);
                        GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
                        GL11.glTranslatef(0.0F, -0.4F, 0.0F);
                        break;

                    case 3: // '\001'
                        GL11.glTranslatef(1.2F, 0.2F, 0.0F);
                        GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
                        GL11.glTranslatef(0.0F, -0.4F, 0.0F);
                        break;

                    case 1: // '\003'
                        GL11.glTranslatef(-3.2F, 0.2F, 0.0F);
                        GL11.glRotatef(90F, 0.0F, 0.1F, 0.0F);
                        GL11.glRotatef(180F, 0.0F, 0.1F, 0.0F);
                        GL11.glRotatef(-90F, 0.1F, 0.0F, 0.0F);
                        GL11.glTranslatef(0.0F, -0.4F, 0.0F);
                        break;

                    case 0: // '\002'
                        GL11.glTranslatef(-1F, 0.2F, 3F);
                        GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
                        GL11.glTranslatef(0.0F, 0.4F, 0.0F);
                        break;
                }
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glTranslated(0, 0, 0.050000000000000003D * Math.sin(0.001D * System.currentTimeMillis()) + 0.10000000000000001D);
                entity.setWorld(altar.getWorldObj());
                entity.setLocationAndAngles(d, d1, d2, 0.0F, 0.0F);
                RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                GL11.glPopMatrix();
            }
        } else {
            System.out.println("Tried to use Altar Renderer to render a Tile Entity that isn't actually an Altar.");
        }
    }

    private void renderAltar(TileEntityAltar entity, double x, double y, double z, float f) {
        bindTextureByName(Necromancy.rscPath + "/model/altarTexture.png");
        GL11.glPushMatrix();
        GL11.glEnable(32826);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) x, (float) y + 2.0F, (float) z + 1.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if (entity.getBlockMetadata() >= 0) {
            int i = entity.getBlockMetadata();
            int j = 0;
            if (i == 0) {
                j = 90;
            }
            if (i == 1) {
                j = 180;
            }
            if (i == 2) {
                j = 270;
            }
            if (i == 3) {
                j = 0;
            }
            GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
            model.render();
            GL11.glDisable(32826);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public ModelAltar model;
    private EntityMinion entity;
    Random rand = new Random();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case ENTITY:
                renderAltar(1.5F, 1F, 0.5F, 1F, 1F, 180F, 1);
                break;
            case EQUIPPED:
                renderAltar(0.5F, 1F, 0.5F, 1F, 250F, 180F, 1);
                break;
            case INVENTORY:
                renderAltar(-0.5F, 0.5F, 0, 1F, -180F, 180F, 0.55F);
                break;
            default:
                break;
        }

    }

    private void renderAltar(float posX, float posY, float posZ, float rotX, float rotY, float rotZ, float scale) {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Necromancy.rscPath + "/model/altarTexture.png");
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(posX, posY, posZ); // size
        GL11.glRotatef(rotX, 1, 0, 0);
        GL11.glRotatef(rotY, 0, 1, 0);
        GL11.glRotatef(rotZ, 0, 0, 1);
        GL11.glScalef(scale, scale, scale);
        model.render();
        GL11.glPopMatrix(); // end
    }
}
