package com.sirolf2009.necromancy.block;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.client.renderer.RenderScent;
import com.sirolf2009.necromancy.entity.EntityMinion;
import com.sirolf2009.necromancy.tileentity.TileEntityAltar;
import com.sirolf2009.necromancy.tileentity.TileEntityScent;
import com.sirolf2009.necromancy.tileentity.TileEntitySewing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockScent extends Block implements ITileEntityProvider {

	private Icon[] icons;

	public BlockScent(int i) {
		super(i, Material.air);
		this.setCreativeTab(Necromancy.tabNecromancy);
		func_111022_d("necromancy:scent");
	}

	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		par1World.markBlockForRenderUpdate(par2, par3, par4);
	}

	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityScent();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderScent.renderID;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean canRenderInPass(int pass) {
		RenderScent.renderPass = pass;
		return pass == 0;
	}

	@Override
	public int getRenderBlockPass()	{
		return 0;
	}

	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[4];
		for(int i = 0; i < 4; i++) {
			icons[i] = par1IconRegister.registerIcon("necromancy:scent"+i);
		}
	}

	public Icon getIcon(int par1, int par2) {
		return icons[par2];
	}
}
