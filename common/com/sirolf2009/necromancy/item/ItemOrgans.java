package com.sirolf2009.necromancy.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.Icon;

import com.sirolf2009.necromancy.Necromancy;

public class ItemOrgans extends ItemFood {

    public ItemOrgans(int par1) {
        super(par1, 2, true);
        setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
        setHasSubtypes(true);
        setCreativeTab(Necromancy.tabNecromancy);
        icons = new Icon[names.length];
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        return new StringBuilder().append("").append(names[par1ItemStack.getItemDamageForDisplay()]).toString();
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        for (int index = 0; index < names.length; index++) {
            icons[index] = iconRegister.registerIcon("necromancy:" + names[index]);
        }
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return icons[par1];
    }

    private Icon[] icons;

    public static String names[] = { "Brains", "Heart", "Muscle", "Lungs", "Skin" };
}
