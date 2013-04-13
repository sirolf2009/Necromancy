package com.sirolf2009.necromancy.item;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.core.proxy.ClientProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ItemScythe extends ItemSword {

    static EnumToolMaterial toolScythe = net.minecraftforge.common.EnumHelper.addToolMaterial("BLOODSCYTHE", 0, 666, 7F, 3, 9);

    public ItemScythe(int par1) {
        super(par1, toolScythe);
        setCreativeTab(Necromancy.tabNecromancy);
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
        par1ItemStack.damageItem(1, par3EntityLiving);
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT && par2EntityLiving.getHealth() <= 0)
            if (((EntityPlayer) par3EntityLiving).inventory.consumeInventoryItem(Item.glassBottle.itemID)) {
                ((EntityPlayer) par3EntityLiving).inventory.addItemStackToInventory(new ItemStack(Necromancy.necromanticItems, 1, 5));
                Random rand = new Random();
                for (int i = 0; i < 30; i++) {
                    ClientProxy.spawnParticle("skull", par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, rand.nextDouble() / 360 * 10, rand.nextDouble() / 360 * 10, rand.nextDouble() / 360 * 10);
                }
                par2EntityLiving.motionY = 10000;
            }
        return true;
    }
}
