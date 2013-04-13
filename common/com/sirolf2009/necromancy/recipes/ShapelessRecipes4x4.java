package com.sirolf2009.necromancy.recipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class ShapelessRecipes4x4 implements IRecipe {

    public ShapelessRecipes4x4(ItemStack par1ItemStack, List par2List) {
        recipeOutput = par1ItemStack;
        recipeItems = par2List;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return recipeOutput;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting) {
        return recipeOutput.copy();
    }

    @Override
    public int getRecipeSize() {
        return recipeItems.size();
    }

    private final ItemStack recipeOutput;
    private final List recipeItems;

    @Override
    public boolean matches(InventoryCrafting var1, World par2World) {
        ArrayList var2 = new ArrayList(recipeItems);
        int var3 = 0;
        do {
            if (var3 >= 4) {
                break;
            }
            for (int var4 = 0; var4 < 4; var4++) {
                ItemStack var5 = var1.getStackInRowAndColumn(var4, var3);
                if (var5 == null) {
                    continue;
                }
                boolean var6 = false;
                Iterator var7 = var2.iterator();
                do {
                    if (!var7.hasNext()) {
                        break;
                    }
                    ItemStack var8 = (ItemStack) var7.next();
                    if (var5.itemID != var8.itemID || var8.getItemDamage() != -1 && var5.getItemDamage() != var8.getItemDamage()) {
                        continue;
                    }
                    var6 = true;
                    var2.remove(var8);
                    break;
                } while (true);
                if (!var6)
                    return false;
            }

            var3++;
        } while (true);
        return var2.isEmpty();
    }
}
