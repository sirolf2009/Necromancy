package com.sirolf2009.necromancy.creativetab;

import net.minecraft.item.Item;

import com.sirolf2009.necromancy.item.ItemNecromancy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class CreativeTabNecro extends net.minecraft.creativetab.CreativeTabs {

    int display;

    public CreativeTabNecro(int par1, String par2Str, int item) {
        super(par1, par2Str);
        display = item;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {
        if (display == 1)
            return ItemNecromancy.necronomicon.itemID;
        else
            return Item.skull.itemID;
    }
}
