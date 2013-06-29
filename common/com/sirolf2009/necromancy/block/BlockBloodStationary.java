package com.sirolf2009.necromancy.block;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.liquids.ILiquid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBloodStationary extends BlockStationary implements ILiquid {

    public BlockBloodStationary(int id) {
        super(id, Material.water);
        blockHardness = 100F;
        this.setLightOpacity(3);
        this.setUnlocalizedName("BloodStationary");
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

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        theIcon = new Icon[] { par1IconRegister.registerIcon("necromancy:blood"), par1IconRegister.registerIcon("necromancy:blood") };
        iconForInvRender = theIcon[0];
    }

    @SideOnly(Side.CLIENT)
    public static Icon func_94424_b(String par0Str) {
        return par0Str == "blood" ? BlockNecromancy.bloodFlowing.iconForWorldRender[0] : BlockNecromancy.bloodFlowing.iconForWorldRender[1];
    }

    public Icon iconForInvRender;

}
