package com.sirolf2009.necromancy.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.achievement.AchievementNecromancy;
import com.sirolf2009.necromancy.block.BlockNecromancy;

public class ItemNecronomicon extends Item {

    public ItemNecronomicon(int par1) {
        super(par1);
        setCreativeTab(Necromancy.tabNecromancy);
        setMaxStackSize(1);
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps
     * to check if is on a player hand and update it's contents.
     */
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int xPos, int yPos, int zPos, int par7, float par8, float par9, float par10) {
        if (par3World.getBlockId(xPos, yPos, zPos) == Block.planks.blockID) {
            if (par3World.getBlockId(xPos + 1, yPos, zPos) == Block.cobblestone.blockID && par3World.getBlockId(xPos + 2, yPos, zPos) == Block.cobblestone.blockID) {
                par3World.setBlock(xPos, yPos, zPos, BlockNecromancy.altar.blockID, 3, 0);
                par3World.setBlock(xPos + 1, yPos, zPos, BlockNecromancy.altarBlock.blockID, 3, 0);
                par3World.setBlock(xPos + 2, yPos, zPos, BlockNecromancy.altarBlock.blockID, 3, 0);
                par2EntityPlayer.addStat(AchievementNecromancy.AltarAchieve, 1);
                return true;
            }
            if (par3World.getBlockId(xPos - 1, yPos, zPos) == Block.cobblestone.blockID && par3World.getBlockId(xPos - 2, yPos, zPos) == Block.cobblestone.blockID) {
                par3World.setBlock(xPos, yPos, zPos, BlockNecromancy.altar.blockID, 1, 0);
                par3World.setBlock(xPos - 1, yPos, zPos, BlockNecromancy.altarBlock.blockID, 1, 0);
                par3World.setBlock(xPos - 2, yPos, zPos, BlockNecromancy.altarBlock.blockID, 1, 0);
                par2EntityPlayer.addStat(AchievementNecromancy.AltarAchieve, 1);
                return true;
            }
            if (par3World.getBlockId(xPos, yPos, zPos + 1) == Block.cobblestone.blockID && par3World.getBlockId(xPos, yPos, zPos + 2) == Block.cobblestone.blockID) {
                par3World.setBlock(xPos, yPos, zPos, BlockNecromancy.altar.blockID, 0, 0);
                par3World.setBlock(xPos, yPos, zPos + 1, BlockNecromancy.altarBlock.blockID, 0, 0);
                par3World.setBlock(xPos, yPos, zPos + 2, BlockNecromancy.altarBlock.blockID, 0, 0);
                par2EntityPlayer.addStat(AchievementNecromancy.AltarAchieve, 1);
                return true;
            }
            if (par3World.getBlockId(xPos, yPos, zPos - 1) == Block.cobblestone.blockID && par3World.getBlockId(xPos, yPos, zPos - 2) == Block.cobblestone.blockID) {
                par3World.setBlock(xPos, yPos, zPos, BlockNecromancy.altar.blockID, 2, 0);
                par3World.setBlock(xPos, yPos, zPos - 1, BlockNecromancy.altarBlock.blockID, 2, 0);
                par3World.setBlock(xPos, yPos, zPos - 2, BlockNecromancy.altarBlock.blockID, 2, 0);
                par2EntityPlayer.addStat(AchievementNecromancy.AltarAchieve, 1);
                return true;
            }
        }

        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }

    public double page = 0;
}
