package com.sirolf2009.necromancy.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;

import com.sirolf2009.necroapi.ISkull;
import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necroapi.NecroEntityRegistry;
import com.sirolf2009.necromancy.Necromancy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNecroSkull extends ItemSkull {

    public static String[] iconTextures;
    public static String[] modelTextures;
    public static String[] skullTypes;
    public Icon[] icons;

    public ItemNecroSkull(int par1) {
        super(par1);
        this.setCreativeTab(Necromancy.tabNecromancy);
    }

    public static void initSkulls() {
        Iterator<ISkull> itr = NecroEntityRegistry.registeredSkullEntities.values().iterator();
        iconTextures = new String[NecroEntityRegistry.registeredSkullEntities.size()];
        modelTextures = new String[NecroEntityRegistry.registeredSkullEntities.size()];
        skullTypes = new String[NecroEntityRegistry.registeredSkullEntities.size()];
        int i = 0;
        while (itr.hasNext()) {
            ISkull mob = itr.next();
            iconTextures[i] = mob.getSkullIconTexture();
            modelTextures[i] = mob.getSkullModelTexture();
            skullTypes[i] = ((NecroEntityBase) mob).mobName;
            // LanguageRegistry.addName(new ItemStack(Necromancy.skull, 1, i),
            // skullTypes[i]+" Skull");
            i++;
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye
     * returns 16 items)
     */
    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int j = 0; j < skullTypes.length; ++j) {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        if (par1 < 0 || par1 >= skullTypes.length) {
            par1 = 0;
        }

        return icons[par1];
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an
     * ItemStack so different stacks can have different names based on their
     * damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int i = par1ItemStack.getItemDamage();

        if (i < 0 || i >= skullTypes.length) {
            i = 0;
        }

        return super.getUnlocalizedName() + "." + skullTypes[i];
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        return par1ItemStack.getItemDamage() == 3 && par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("SkullOwner") ? StatCollector.translateToLocalFormatted("item.skull.player.name", new Object[] { par1ItemStack.getTagCompound().getString("SkullOwner") }) : super.getItemDisplayName(par1ItemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        icons = new Icon[iconTextures.length];

        for (int i = 0; i < iconTextures.length; ++i) {
            icons[i] = par1IconRegister.registerIcon(iconTextures[i]);
        }
    }
}
