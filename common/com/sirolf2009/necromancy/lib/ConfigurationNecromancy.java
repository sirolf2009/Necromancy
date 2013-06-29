package com.sirolf2009.necromancy.lib;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigurationNecromancy {

    public static int AltarID;
    public static int AltarBlockID;

    public static int SewingID;

    public static int BloodStationaryID;
    public static int BloodFlowingID;
    public static int BucketBloodID;

    public static int ScytheID;
    public static int ScytheBoneID;

    public static int ItemID;
    public static int NecronomiconID;
    public static int OrgansID;
    public static int BodyPartID;
    public static int SpawnerID;

    public static int IsaacsHeadID;

    public static boolean Christmas;
    public static boolean RenderSpecialScythe;
    public static boolean InitDuringPreInit;
    public static int NecroVillagerID;

    public static void initProperties(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        AltarID = config.getBlock("Altar", 2621).getInt();
        AltarBlockID = config.getBlock("Altar Building Block", 2622).getInt();
        SewingID = config.getBlock("Sewing", 2623).getInt();
        BloodStationaryID = config.getBlock("BloodStationary", 2625).getInt();
        BloodFlowingID = BloodStationaryID - 1;

        BucketBloodID = config.getItem("BloodStationary", 2621).getInt();
        ScytheID = config.getItem("Scythe", 2622).getInt();
        ScytheBoneID = config.getItem("Bone Scythe", 2629).getInt();
        ItemID = config.getItem("item", "main items", 2623).getInt();
        NecronomiconID = config.getItem("item", "necronomicon", 2624).getInt();
        OrgansID = config.getItem("item", "organs", 2625).getInt();
        BodyPartID = config.getItem("item", "bodyparts", 2626).getInt();
        SpawnerID = config.getItem("item", "spawner", 2628).getInt();

        IsaacsHeadID = config.getItem("armor", "Isaac's Severed Head", 2627).getInt();

        Christmas = config.get("rendering", "christmas hats", false).getBoolean(false);

        RenderSpecialScythe = config.get("special scythes (only for a select number of people)", "Other", true).getBoolean(false);
        InitDuringPreInit = config.get("Init items during pre init", "Other", false).getBoolean(false);
        NecroVillagerID = config.get("NecroVillagerID", "Other", 666).getInt();

        if (config.hasChanged()) {
            config.save();
        }
    }

}
