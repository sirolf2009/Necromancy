package com.sirolf2009.necromancy.item;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.lib.ConfigurationNecromancy;

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
        // int skullID = Item.skull.itemID;
        // Item.itemsList[skullID] = null;
        genericItems = new ItemGeneric(ConfigurationNecromancy.ItemID).setUnlocalizedName("ItemNecromancy");
        necronomicon = new ItemNecronomicon(ConfigurationNecromancy.NecronomiconID).setUnlocalizedName("Necronomicon");
        for (int x = 0; x < ItemGeneric.names.length; x++) {
            LanguageRegistry.addName(new ItemStack(genericItems, 1, x), ItemGeneric.names[x]);
        }
        LanguageRegistry.addName(necronomicon, "Necronomicon");
        scythe = new ItemScythe(ConfigurationNecromancy.ScytheID, ItemScythe.toolScythe).setUnlocalizedName("ItemScythe");
        LanguageRegistry.addName(scythe, "Blood Scythe");
        scytheBone = new ItemScythe(ConfigurationNecromancy.ScytheBoneID, ItemScythe.toolScytheBone).setUnlocalizedName("ItemScytheBone");
        LanguageRegistry.addName(scytheBone, "Bone Scythe");
        bucketBlood = new ItemBucketBlood(ConfigurationNecromancy.BucketBloodID, ConfigurationNecromancy.BloodFlowingID).setUnlocalizedName("BucketBlood");
        LanguageRegistry.addName(bucketBlood, "Blood Bucket");
        organs = new ItemOrgans(ConfigurationNecromancy.OrgansID).setUnlocalizedName("Organs");
        for (int x = 0; x < ItemOrgans.names.length; x++) {
            LanguageRegistry.addName(new ItemStack(organs, 1, x), ItemOrgans.names[x]);
        }
        bodyparts = new ItemBodyPart(ConfigurationNecromancy.BodyPartID).setUnlocalizedName("BodyParts");
        // skull = new ItemNecroSkull(skullID);
        isaacsHead = new ItemIsaacsHead(ConfigurationNecromancy.IsaacsHeadID, isaac, Necromancy.Proxy.addArmour("Isaac"), 0);
        LanguageRegistry.addName(isaacsHead, "Isaac's Severed Head");

        spawner = new ItemSpawner(ConfigurationNecromancy.SpawnerID).setUnlocalizedName("NecroSpawner");
        LanguageRegistry.addName(new ItemStack(spawner, 1, 0), "Isaac's Soul Heart");
    }

}
