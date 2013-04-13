package com.sirolf2009.necromancy.block;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.IBlockLiquid;

import com.sirolf2009.necromancy.Necromancy;

public class BlockBloodFlowing extends BlockFlowing implements IBlockLiquid {

    public BlockBloodFlowing(int par1) {
        super(par1, Material.water);
        blockHardness = 100F;
        this.setLightOpacity(3);
        this.setCreativeTab(Necromancy.tabNecromancy);
        this.setUnlocalizedName("BloodFlowing");
    }

    @Override
    public int stillLiquidId() {
        return Necromancy.bloodStill.blockID;
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
}
