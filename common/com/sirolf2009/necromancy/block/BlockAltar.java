package com.sirolf2009.necromancy.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.entity.EntityMinion;
import com.sirolf2009.necromancy.tileentity.TileEntityAltar;

public class BlockAltar extends BlockContainer {
    public static int guiID = 0;

    public BlockAltar(int i) {
        super(i, Material.rock);
        setCreativeTab(Necromancy.tabNecromancy);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {
        TileEntityAltar tileEntity = (TileEntityAltar) world.getBlockTileEntity(x, y, z);
        if (tileEntity == null)
            return false;
        else if (player.isSneaking() && (tileEntity.canSpawn() || player.capabilities.isCreativeMode)) {
            tileEntity.spawn(player);
            return true;
        } else {
            player.openGui(Necromancy.Instance, guiID, world, x, y, z);
            return true;
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World par1World, int x, int y, int z, int par5) {
        TileEntityAltar var7 = (TileEntityAltar) par1World.getBlockTileEntity(x, y, z);
        Random rand = new Random();
        if (var7 != null) {
            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
                ItemStack var9 = var7.getStackInSlot(var8);
                log(var9);

                if (var9 != null) {
                    float var10 = rand.nextFloat() * 0.8F + 0.1F;
                    float var11 = rand.nextFloat() * 0.8F + 0.1F;
                    float var12 = rand.nextFloat() * 0.8F + 0.1F;

                    while (var9.stackSize > 0) {
                        int var13 = rand.nextInt(21) + 10;

                        if (var13 > var9.stackSize) {
                            var13 = var9.stackSize;
                        }

                        var9.stackSize -= var13;
                        EntityItem var14 = new EntityItem(par1World, x + var10, y + var11, z + var12, new ItemStack(var9.itemID, var13, var9.getItemDamage()));

                        float var15 = 0.05F;
                        var14.motionX = (float) rand.nextGaussian() * var15;
                        var14.motionY = (float) rand.nextGaussian() * var15 + 0.2F;
                        var14.motionZ = (float) rand.nextGaussian() * var15;
                        par1World.spawnEntityInWorld(var14);
                    }
                }
            }
        }
        par1World.removeBlockTileEntity(x, y, z);
        par1World.setBlock(x, y, z, Block.planks.blockID, 0, 0);
        switch (par5) {
            case 2:
                par1World.setBlock(x, y, z - 1, Block.cobblestone.blockID, 0, 0);
                par1World.setBlock(x, y, z - 2, Block.cobblestone.blockID, 0, 0);
                break;
            case 0:
                par1World.setBlock(x, y, z + 1, Block.cobblestone.blockID, 0, 0);
                par1World.setBlock(x, y, z + 2, Block.cobblestone.blockID, 0, 0);
                break;
            case 1:
                par1World.setBlock(x - 1, y, z, Block.cobblestone.blockID, 0, 0);
                par1World.setBlock(x - 2, y, z, Block.cobblestone.blockID, 0, 0);
                break;
            case 3:
                par1World.setBlock(x + 1, y, z, Block.cobblestone.blockID, 0, 0);
                par1World.setBlock(x + 2, y, z, Block.cobblestone.blockID, 0, 0);
                break;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityAltar();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public void log(Object msg) {
        System.out.println(BlockAltar.class + "	" + msg);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return 0;
    }

    EntityMinion minion;
}
