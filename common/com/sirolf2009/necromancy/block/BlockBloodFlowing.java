package com.sirolf2009.necromancy.block;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraftforge.liquids.IBlockLiquid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBloodFlowing extends BlockFlowing implements IBlockLiquid {

    public BlockBloodFlowing(int par1) {
        super(par1, Material.water);
        blockHardness = 100F;
        this.setLightOpacity(3);
        this.setUnlocalizedName("BloodFlowing");
    }

    @Override
    public int stillLiquidId() {
        return BlockNecromancy.bloodStill.blockID;
    }

    @Override
    public boolean isMetaSensitive() {
        return false;
    }

    @Override
    public int stillLiquidMeta() {
        return 0;
    }

    @Override
    public boolean willGenerateSources() {
        return false;
    }

    @Override
    public int getFlowDistance() {
        return 3;
    }

    @Override
    public byte[] getLiquidRGB() {
        return new byte[] { 102, 0, 0 };
    }

    @Override
    public NBTTagCompound getLiquidProperties() {
        return null;
    }

    @Override
    public String getLiquidBlockTextureFile() {
        return "necromancy:blood";
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        theIcon = new Icon[] { par1IconRegister.registerIcon("necromancy:blood"), par1IconRegister.registerIcon("necromancy:blood_flow") };
        iconForWorldRender = theIcon;
        iconForInvRender = iconForWorldRender[0];
    }

    @SideOnly(Side.CLIENT)
    public static Icon func_94424_b(String par0Str) {
        return par0Str == "blood" ? BlockNecromancy.bloodFlowing.iconForWorldRender[0] : BlockNecromancy.bloodFlowing.iconForWorldRender[1];
    }

    public Icon iconForInvRender;
    public Icon[] iconForWorldRender;
}
