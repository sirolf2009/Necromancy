package com.sirolf2009.necromancy.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.sirolf2009.necromancy.lib.ConfigurationNecromancy;
import com.sirolf2009.necromancy.tileentity.TileEntityAltar;
import com.sirolf2009.necromancy.tileentity.TileEntitySewing;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockNecromancy {

    public static Block altar;
    public static Block altarBlock;
    public static Block sewing;
    public static Block scentBurner;
    public static BlockBloodFlowing bloodFlowing;
    public static BlockBloodStationary bloodStill;

    public static void initBlocks() {
        altar = new BlockAltar(ConfigurationNecromancy.AltarID).setHardness(4);
        altar.setUnlocalizedName("Summoning Altar");
        GameRegistry.registerBlock(altar, "Summoning Altar");
        GameRegistry.registerTileEntity(TileEntityAltar.class, "Altar");
        LanguageRegistry.addName(altar, "Summoning Altar");

        altarBlock = new BlockAltarBlock(ConfigurationNecromancy.AltarBlockID).setHardness(4);
        altarBlock.setUnlocalizedName("Altar Building Block");
        GameRegistry.registerBlock(altarBlock, "Altar Building Block");
        LanguageRegistry.addName(altarBlock, "Altar Building Block");

        sewing = new BlockSewing(ConfigurationNecromancy.SewingID, Material.iron).setHardness(4);
        sewing.setUnlocalizedName("Sewing Machine");
        GameRegistry.registerBlock(sewing, "Sewing Machine");
        GameRegistry.registerTileEntity(TileEntitySewing.class, "Sewing");
        LanguageRegistry.addName(sewing, "Sewing Machine");

        bloodFlowing = new BlockBloodFlowing(ConfigurationNecromancy.BloodFlowingID);
        bloodStill = new BlockBloodStationary(ConfigurationNecromancy.BloodStationaryID);
        bloodFlowing.setUnlocalizedName("FlowingBlood");
        bloodStill.setUnlocalizedName("StationaryBlood");
        GameRegistry.registerBlock(bloodFlowing, "FlowingBlood");
        GameRegistry.registerBlock(bloodStill, "StationaryBlood");
        LanguageRegistry.addName(bloodFlowing, "Flowing Blood");
        LanguageRegistry.addName(bloodStill, "Still Blood");

        /*
         * skullWall = new BlockSkullWall(SkullWallID);
         * GameRegistry.registerBlock(skullWall, "skullWall");
         * LanguageRegistry.addName(skullWall, "Skull Wall");
         * GameRegistry.registerTileEntity(TileEntitySkullWall.class,
         * "SkullWall");
         */
    }

}
