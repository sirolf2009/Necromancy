package com.sirolf2009.necromancy.client.renderer;

import java.util.Random;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.block.BlockNecromancy;
import com.sirolf2009.necromancy.block.BlockScent;
import com.sirolf2009.necromancy.tileentity.TileEntityScent;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderScent implements ISimpleBlockRenderingHandler {

	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static int renderPass;
	private Random rand = new Random();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		TileEntityScent tileEntityScent = (TileEntityScent) world.getBlockTileEntity(x, y, z);
		Tessellator.instance.setColorRGBA(tileEntityScent.getRed(), tileEntityScent.getGreen(), tileEntityScent.getBlue(), (int)Math.floor(tileEntityScent.getAlpha()*0.2));
		tileEntityScent.iconTimer--;
		if(tileEntityScent.iconTimer <= 0) {
			tileEntityScent.iconTimer= rand.nextInt(5000)+5000;
			for(int i = 0; i < 6; i++) {
				tileEntityScent.icons[i] = BlockNecromancy.scent.getIcon(0, rand.nextInt(4));
			}
		}
		renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
		renderer.lockBlockBounds = true;
		block.setBlockBounds(0, 0, 0, 0, 0, 0);
		renderer.renderFaceXPos(block, x, y, z, tileEntityScent.icons[0]);
		renderer.renderFaceYPos(block, x, y, z, tileEntityScent.icons[1]);
		renderer.renderFaceZPos(block, x, y, z, tileEntityScent.icons[2]);
		renderer.renderFaceXNeg(block, x, y, z, tileEntityScent.icons[3]);
		renderer.renderFaceYNeg(block, x, y, z, tileEntityScent.icons[4]);
		renderer.renderFaceZNeg(block, x, y, z, tileEntityScent.icons[5]);
		renderer.lockBlockBounds = false;
		return true;
	}
	
	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}

}
