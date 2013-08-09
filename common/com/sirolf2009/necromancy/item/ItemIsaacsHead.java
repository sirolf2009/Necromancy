package com.sirolf2009.necromancy.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.sirolf2009.necromancy.Necromancy;

public class ItemIsaacsHead extends ItemArmor {

    public ItemIsaacsHead(int par1, EnumArmorMaterial material, int par3, int par4) {
        super(par1, material, par3, par4);
        setUnlocalizedName("IsaacsHead");
        setCreativeTab(Necromancy.tabNecromancy);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
        return "/armor/Isaac_1.png";
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("necromancy:isaacsseveredhead");
    }

}
