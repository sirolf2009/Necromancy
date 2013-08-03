package com.sirolf2009.necromancy.item;

import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;

import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.lib.ConfigurationNecromancy;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemNecromancy {

    public static Item apprenticeArmorHead;
    public static Item apprenticeArmorTorso;
    public static Item apprenticeArmorLeggings;
    public static Item apprenticeArmorBoots;
    public static Item genericItems;
    public static Item necronomicon;
    public static Item scythe;
    public static Item scytheBone;
    public static Item bucketBlood;
    public static Item organs;
    public static Item bodyparts;
    public static Item spawner;
    // public static Item skull;
    public static Item isaacsHead;

    public static EnumArmorMaterial isaac = EnumHelper.addArmorMaterial("Isaac", Integer.MAX_VALUE, new int[] { 0, 0, 0, 0 }, 0);
    public static EnumArmorMaterial apprenticeRobes = EnumHelper.addArmorMaterial("apprenticeRobesNecromancy", Integer.MAX_VALUE, new int[] { 0, 0, 0, 0 }, 100);

    public static void initItems() {
        genericItems = new ItemGeneric(ConfigurationNecromancy.GenericItemID).setUnlocalizedName("ItemNecromancy");
        ThaumcraftApi.registerObjectTag(genericItems.itemID, 0, (new ObjectTags()).add(EnumTag.DEATH, 2));
        ThaumcraftApi.registerObjectTag(genericItems.itemID, 1, (new ObjectTags()).add(EnumTag.FLIGHT, 2).add(EnumTag.LIFE, 4).add(EnumTag.LIGHT, 2).add(EnumTag.CRYSTAL, 2));
        ThaumcraftApi.registerObjectTag(genericItems.itemID, 2, (new ObjectTags()).add(EnumTag.HEAL, 2).add(EnumTag.LIFE, 4).add(EnumTag.CRYSTAL, 2));
        ThaumcraftApi.registerObjectTag(genericItems.itemID, 3, (new ObjectTags()).add(EnumTag.FLESH, 8).add(EnumTag.KNOWLEDGE, 4));
        for (int x = 0; x < ItemGeneric.names.length; x++) {
            LanguageRegistry.addName(new ItemStack(genericItems, 1, x), ItemGeneric.names[x]);
        }
        
        necronomicon = new ItemNecronomicon(ConfigurationNecromancy.NecronomiconID).setUnlocalizedName("Necronomicon");
        LanguageRegistry.addName(necronomicon, "Necronomicon");
        ThaumcraftApi.registerObjectTag(necronomicon.itemID, -1, (new ObjectTags()).add(EnumTag.KNOWLEDGE, 666).add(EnumTag.DEATH, 666).add(EnumTag.DARK, 666).add(EnumTag.EVIL, 666).add(EnumTag.CONTROL, 666));
        
        scythe = new ItemScythe(ConfigurationNecromancy.ScytheID, ItemScythe.toolScythe).setUnlocalizedName("ItemScythe");
        LanguageRegistry.addName(scythe, "Blood Scythe");
        ThaumcraftApi.registerObjectTag(scythe.itemID, -1, (new ObjectTags()).add(EnumTag.WEAPON, 8).add(EnumTag.LIFE, 2));
        
        scytheBone = new ItemScythe(ConfigurationNecromancy.ScytheBoneID, ItemScythe.toolScytheBone).setUnlocalizedName("ItemScytheBone");
        LanguageRegistry.addName(scytheBone, "Bone Scythe");
        ThaumcraftApi.registerObjectTag(scytheBone.itemID, -1, (new ObjectTags()).add(EnumTag.WEAPON, 8).add(EnumTag.DEATH, 2));
        
        bucketBlood = new ItemBucketBlood(ConfigurationNecromancy.BucketBloodID, ConfigurationNecromancy.BloodID).setUnlocalizedName("BucketBlood");
        LanguageRegistry.addName(bucketBlood, "Blood Bucket");
        ThaumcraftApi.registerObjectTag(scytheBone.itemID, -1, (new ObjectTags()).add(EnumTag.METAL, 13).add(EnumTag.HEAL, 16).add(EnumTag.LIFE, 32));
        
        organs = new ItemOrgans(ConfigurationNecromancy.OrgansID).setUnlocalizedName("Organs");
        ThaumcraftApi.registerObjectTag(organs.itemID, 0, (new ObjectTags()).add(EnumTag.FLESH, 8).add(EnumTag.KNOWLEDGE, 4));
        ThaumcraftApi.registerObjectTag(organs.itemID, 1, (new ObjectTags()).add(EnumTag.FLESH, 8));
        ThaumcraftApi.registerObjectTag(organs.itemID, 2, (new ObjectTags()).add(EnumTag.FLESH, 2));
        ThaumcraftApi.registerObjectTag(organs.itemID, 3, (new ObjectTags()).add(EnumTag.FLESH, 8));
        ThaumcraftApi.registerObjectTag(organs.itemID, 4, (new ObjectTags()).add(EnumTag.EVIL, 2));
        for (int x = 0; x < ItemOrgans.names.length; x++) {
            LanguageRegistry.addName(new ItemStack(organs, 1, x), ItemOrgans.names[x]);
        }
        NecroEntityBase.organID = organs.itemID;
        
        bodyparts = new ItemBodyPart(ConfigurationNecromancy.BodyPartID).setUnlocalizedName("BodyParts");
        ThaumcraftApi.registerObjectTag(organs.itemID, -1, (new ObjectTags()).add(EnumTag.EVIL, 2).add(EnumTag.BEAST, 2).add(EnumTag.DEATH, 8));
        
        isaacsHead = new ItemIsaacsHead(ConfigurationNecromancy.IsaacsHeadID, isaac, Necromancy.Proxy.addArmour("Isaac"), 0);
        LanguageRegistry.addName(isaacsHead, "Isaac's Severed Head");

        spawner = new ItemSpawner(ConfigurationNecromancy.SpawnerID).setUnlocalizedName("NecroSpawner");
        LanguageRegistry.addName(new ItemStack(spawner, 1, 0), "Isaac's Soul Heart");
        
        initRecipes();
    }
    
    public static void initRecipes() {
        GameRegistry.addRecipe(new ItemStack(ItemNecromancy.necronomicon, 1), new Object[] { "LSL", "IBF", "LNL", Character.valueOf('B'), Item.book, Character.valueOf('L'), Item.leather, Character.valueOf('S'), ItemGeneric.getItemStackFromName("Jar of Blood"), Character.valueOf('I'), new ItemStack(Item.dyePowder, 1, 0), Character.valueOf('F'), Item.feather, Character.valueOf('N'), Item.netherStalkSeeds });
        GameRegistry.addRecipe(ItemGeneric.getItemStackFromName("Bone Needle"), new Object[] { "X", Character.valueOf('X'), new ItemStack(Item.dyePowder, 1, 15) });
        GameRegistry.addRecipe(new ItemStack(ItemNecromancy.scythe, 1), new Object[] { "IH", " S", " B", Character.valueOf('I'), Block.obsidian, 'H', Item.hoeIron, 'S', Item.stick, 'B', ItemGeneric.getItemStackFromName("Jar of Blood") });
        GameRegistry.addRecipe(new ItemStack(ItemNecromancy.scytheBone, 1), new Object[] { "IH", " S", " B", Character.valueOf('I'), Block.obsidian, 'H', ItemNecromancy.scythe, 'S', Item.bone, 'B', Item.diamond });
        GameRegistry.addRecipe(ItemGeneric.getItemStackFromName("Brain on a Stick"), new Object[] { "# ", " X", '#', Item.fishingRod, 'X', new ItemStack(ItemNecromancy.organs, 1, 0) });
        GameRegistry.addShapelessRecipe(ItemGeneric.getItemStackFromName("Jar of Blood", 8), new Object[] { new ItemStack(ItemNecromancy.bucketBlood), Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle });
        GameRegistry.addShapelessRecipe(new ItemStack(ItemNecromancy.bucketBlood), new Object[] { Item.bucketEmpty, ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood") });
    }

}
