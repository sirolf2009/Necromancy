package com.sirolf2009.necromancy.block;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraftforge.liquids.ILiquid;

import com.sirolf2009.necromancy.Necromancy;

public class BlockBloodStationary extends BlockStationary implements ILiquid {

    public BlockBloodStationary(int id) {
        super(id, Material.water);
        blockHardness = 100F;
        this.setLightOpacity(3);
        this.setUnlocalizedName("BloodStationary");
        this.setCreativeTab(Necromancy.tabNecromancy);
        this.disableStats();
    }

    @Override
    public int stillLiquidId() {
        return blockID;
    }

    @Override
    public boolean isMetaSensitive() {
        return false;
    }

    @Override
    public int stillLiquidMeta() {
        return 0;
    }

}
