package com.sirolf2009.necromancy.creativetab;

import com.sirolf2009.necromancy.Necromancy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class CreativeTabNecro extends net.minecraft.creativetab.CreativeTabs {

    public CreativeTabNecro(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {
        return Necromancy.necronomicon.itemID;
    }
}
