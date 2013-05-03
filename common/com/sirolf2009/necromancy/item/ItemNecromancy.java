package com.sirolf2009.necromancy.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.sirolf2009.necromancy.Necromancy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNecromancy extends Item {

    public ItemNecromancy(int par1) {
        super(par1);
        icons = new Icon[names.length];
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(Necromancy.tabNecromancy);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityLiving)
            if (stack.getItemDamage() == 0)
                if (player.inventory.consumeInventoryItem(Item.glassBottle.itemID)) {
                    stack.stackSize--;
                    if (!player.inventory.addItemStackToInventory(new ItemStack(Necromancy.necromanticItems, 1, 2))) {
                        player.dropPlayerItem(new ItemStack(Necromancy.necromanticItems, 1, 2));
                    }
                    return true;
                }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        return new StringBuilder().append("").append(names[par1ItemStack.getItemDamageForDisplay()]).toString();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 < names.length; var4++) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    public static ItemStack getItemStackFromName(String name) {
        for (int i = 0; i < names.length; i++)
            if (names[i].equalsIgnoreCase(name))
                return new ItemStack(Necromancy.necromanticItems, 1, i);
        return null;
    }

    public static ItemStack getItemStackFromName(String name, int amount) {
        for (int i = 0; i < names.length; i++)
            if (names[i].equalsIgnoreCase(name))
                return new ItemStack(Necromancy.necromanticItems, amount, i);
        return null;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        for (int index = 0; index < names.length; index++) {
            String path = names[index].replace(" ", "");
            icons[index] = iconRegister.registerIcon("necromancy:" + path);
        }
        tearBlood = iconRegister.registerIcon("necromancy:BloodTear");
        tearNormal = iconRegister.registerIcon("necromancy:Tear");
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return icons[par1];
    }

    private Icon[] icons;
    public static String names[] = { "Bone Needle", "Soul in a Jar", "Jar of Blood", "Brain on a Stick" };
    public static Icon tearNormal;
    public static Icon tearBlood;
}
