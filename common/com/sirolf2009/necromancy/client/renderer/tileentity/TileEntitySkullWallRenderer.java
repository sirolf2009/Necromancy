package com.sirolf2009.necromancy.client.renderer.tileentity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.util.vector.Vector3f;

import com.sirolf2009.necromancy.tileentity.TileEntitySkullWall;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntitySkullWallRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    ModelSkeletonHead skull = new ModelSkeletonHead(0, 0, 64, 32);
    Map<String, Vector3f> xPositivePositions = new HashMap<String, Vector3f>();

    public TileEntitySkullWallRenderer() {

    }

    public void updateLocs() {
        xPositivePositions.put("top1", new Vector3f(-16, -12, -4F));
        xPositivePositions.put("top2", new Vector3f(-16, -12, -12F));
        xPositivePositions.put("left", new Vector3f(-16, -4, -16F));
        xPositivePositions.put("middle", new Vector3f(-16, -4, -8F));
        xPositivePositions.put("right", new Vector3f(-16, -4, -8F));
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.render((float) par2, (float) par4, (float) par6, ((TileEntitySkullWall) par1TileEntity).getBlockMetadata() & 7, ((TileEntitySkullWall) par1TileEntity).getSkullType(), (TileEntitySkullWall) par1TileEntity);
    }

    public void render(float xPos, float yPos, float zPos, int meta, String type, TileEntitySkullWall entity) {
        /*
         * updateLocs(); bindTextureByName("/mob/skeleton.png");
         * 
         * GL11.glPushMatrix(); GL11.glDisable(GL11.GL_CULL_FACE);
         * GL11.glDisable(GL11.GL_LIGHTING);
         * 
         * WorldClient world = ClientProxy.mc.theWorld; float f4 = 0.0625F;
         * GL11.glEnable(GL12.GL_RESCALE_NORMAL); GL11.glScalef(-1.0F, -1.0F,
         * -1.0F); GL11.glTranslatef(-xPos, -yPos, -zPos);
         * GL11.glEnable(GL11.GL_ALPHA_TEST); GL11.glColor3f(200, 200, 200);
         * 
         * // x positive side if (world.getBlockId(entity.xCoord + 1,
         * entity.yCoord, entity.zCoord) == 0) {
         * translate(xPositivePositions.get("middle")); skull.render((Entity)
         * null, 0.0F, 0.0F, 0.0F, 90.0F, 0.0F, f4); if
         * (world.getBlockId(entity.xCoord + 1, entity.yCoord + 1,
         * entity.zCoord) == 0 && world.getBlockId(entity.xCoord, entity.yCoord
         * + 1, entity.zCoord) == Necromancy.skullWall.blockID) {
         * translate(xPositivePositions.get("top1")); skull.render((Entity)
         * null, 0.0F, 0.0F, 0.0F, 90.0F, 0.0F, f4);
         * translate(xPositivePositions.get("top2")); skull.render((Entity)
         * null, 0.0F, 0.0F, 0.0F, 90.0F, 0.0F, f4); } if
         * (world.getBlockId(entity.xCoord + 1, entity.yCoord, entity.zCoord +
         * 1) == 0 && world.getBlockId(entity.xCoord, entity.yCoord,
         * entity.zCoord + 1) == Necromancy.skullWall.blockID) {
         * translate(xPositivePositions.get("left")); skull.render((Entity)
         * null, 0.0F, 0.0F, 0.0F, 90.0F, 0.0F, f4); } }
         * GL11.glEnable(GL11.GL_LIGHTING); GL11.glPopMatrix();
         */
    }

    public void translate(Vector3f vector) {
        skull.skeletonHead.rotationPointX = vector.x;
        skull.skeletonHead.rotationPointY = vector.y;
        skull.skeletonHead.rotationPointZ = vector.z;
    }

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
        float f4 = 0.0625F;
        skull.render((Entity) null, 0.0F, 0.0F, 0.0F, 180.0F, 0.0F, f4);
    }
}
