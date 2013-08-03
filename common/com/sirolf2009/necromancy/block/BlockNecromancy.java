package com.sirolf2009.necromancy.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.sirolf2009.necromancy.item.ItemGeneric;
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
    public static BlockBlood blood;
    
    public static Fluid fluidBlood;

    public static void initBlocks() {
        altar = new BlockAltar(ConfigurationNecromancy.AltarID).setHardness(4);
        altar.setUnlocalizedName("Summoning Altar");
        GameRegistry.registerBlock(altar, "Summoning Altar");
        GameRegistry.registerTileEntity(TileEntityAltar.class, "Summoning Altar");
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
        
        fluidBlood = new Fluid("Blood");
        FluidRegistry.registerFluid(fluidBlood);
        fluidBlood.setBlockID(ConfigurationNecromancy.BloodID);

        blood = new BlockBlood(ConfigurationNecromancy.BloodID, fluidBlood);
        blood.setUnlocalizedName("FlowingBlood");
        GameRegistry.registerBlock(blood, "FlowingBlood");
        LanguageRegistry.addName(blood, "Flowing Blood");
        
        initRecipes();
    }
    
    public static void initRecipes() {
        GameRegistry.addRecipe(new ItemStack(BlockNecromancy.sewing, 1), new Object[] { "III", "ISB", "III", 'I', Item.ingotIron, 'S', Item.silk, 'B', ItemGeneric.getItemStackFromName("Bone Needle") });
    }

}
