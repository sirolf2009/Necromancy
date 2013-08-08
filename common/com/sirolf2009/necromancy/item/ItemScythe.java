package com.sirolf2009.necromancy.item;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
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

    public static EnumToolMaterial toolScythe = net.minecraftforge.common.EnumHelper.addToolMaterial("BLOODSCYTHE", 0, 666, 7F, 2, 9);
    public static EnumToolMaterial toolScytheBone = net.minecraftforge.common.EnumHelper.addToolMaterial("BLOODSCYTHEBONE", 0, 666, 7F, 4, 9);

    public ItemScythe(int par1, EnumToolMaterial material) {
        super(par1, material);
        setCreativeTab(Necromancy.tabNecromancy);
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        System.out.println("hit entity, life = "+par2EntityLivingBase.func_110143_aJ());
        if (par2EntityLivingBase.func_110143_aJ() <= 0)
            if (((EntityPlayer) par3EntityLivingBase).inventory.consumeInventoryItem(Item.glassBottle.itemID)) {
                ((EntityPlayer) par3EntityLivingBase).inventory.addItemStackToInventory(ItemGeneric.getItemStackFromName("Soul in a Jar"));
                if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
                    Random rand = new Random();
                    for (int i = 0; i < 30; i++) {
                        ClientProxy.spawnParticle("skull", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, rand.nextDouble() / 360 * 10, rand.nextDouble() / 360 * 10, rand.nextDouble() / 360 * 10);
                    }
                }
                par2EntityLivingBase.motionY = 10000;
            }
        return false;
    }
}
