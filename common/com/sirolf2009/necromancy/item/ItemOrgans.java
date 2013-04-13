package com.sirolf2009.necromancy.item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class ItemOrgans extends ItemFood {

    public ItemOrgans(int par1) {
        super(par1, 2, true);
        setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
        setHasSubtypes(true);
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        return new StringBuilder().append("").append(names[par1ItemStack.getItemDamageForDisplay()]).toString();
    }

    public static String names[] = { "Brains", "Heart", "Muscle", "Lungs", "Skin" };
}
