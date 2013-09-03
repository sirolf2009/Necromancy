package com.sirolf2009.necromancy.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.tileentity.TileEntityScentBurner;
import com.sirolf2009.necromancy.tileentity.TileEntitySewing;

public class BlockScentBurner extends BlockContainer {

    public static int guiID = 2;

    public BlockScentBurner(int par1) {
        super(par1, Material.wood);
        setCreativeTab(Necromancy.tabNecromancy);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity != null && !player.isSneaking()) {
            player.openGui(Necromancy.Instance, guiID, world, x, y, z);
            return true;
        }
        super.onBlockActivated(world, x, y, z, player, idk, what, these, are);
        return false;
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

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityScentBurner();
    }

}
