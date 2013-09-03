package com.sirolf2009.necromancy.lib;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ConfigurationNecromancy {

    public static int AltarID;
    public static int AltarBlockID;
    public static int SewingID;
    public static int ScentBurnerID;
    public static int ScentID;

    public static int BloodID;
    public static int BucketBloodID;

    public static int ScytheID;
    public static int ScytheBoneID;

    public static int GenericItemID;
    public static int NecronomiconID;
    public static int OrgansID;
    public static int BodyPartID;
    public static int SpawnerID;

    public static int IsaacsHeadID;

    public static boolean SearchMinionID;
    public static int MinionID;
    public static boolean SearchTeddyID;
    public static int TeddyID;
    public static boolean SearchIsaacID;
    public static int IsaacID;
    public static boolean SearchIsaacBloodID;
    public static int IsaacBloodID;
    public static boolean SearchIsaacHeadID;
    public static int IsaacHeadID;
    public static boolean SearchIsaacBodyID;
    public static int IsaacBodyID;
    public static boolean SearchNecroVillagerID;
    public static int NecroVillagerID;

    public static boolean Christmas;
    public static boolean RenderSpecialScythe;
    public static boolean InitDuringPreInit;

    public static void initProperties(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        AltarID = config.getBlock("Altar", 2621).getInt();
        AltarBlockID = config.getBlock("Altar Building Block", 2622).getInt();
        SewingID = config.getBlock("Sewing", 2623).getInt();
        BloodID = config.getBlock("Blood", 2625).getInt();
        ScentBurnerID = config.getBlock("Scent Burner", 2626).getInt();
        ScentID = config.getBlock("Scent", 2627).getInt();

        BucketBloodID = config.getItem("BloodStationary", 2621).getInt();
        ScytheID = config.getItem("Scythe", 2622).getInt();
        ScytheBoneID = config.getItem("Bone Scythe", 2629).getInt();
        GenericItemID = config.getItem("item", "main items", 2623).getInt();
        NecronomiconID = config.getItem("item", "necronomicon", 2624).getInt();
        OrgansID = config.getItem("item", "organs", 2625).getInt();
        BodyPartID = config.getItem("item", "bodyparts", 2626).getInt();
        SpawnerID = config.getItem("item", "spawner", 2628).getInt();

        SearchMinionID = config.get("Entity", "Search for free Minion ID", true).getBoolean(true);
        MinionID = SearchMinionID ? EntityRegistry.findGlobalUniqueEntityId() : config.get("entity", "Minion ID", 123).getInt();
        SearchTeddyID = config.get("Entity", "Search for free Teddy ID", true).getBoolean(true);
        TeddyID = SearchTeddyID ? EntityRegistry.findGlobalUniqueEntityId() : config.get("entity", "Teddy ID", 124).getInt();
        SearchIsaacID = config.get("Entity", "Search for free Isaac ID", true).getBoolean(true);
        IsaacID = SearchIsaacID ? EntityRegistry.findGlobalUniqueEntityId() : config.get("entity", "Isaac ID", 125).getInt();
        SearchIsaacBloodID = config.get("Entity", "Search for free Isaac Blood ID", true).getBoolean(true);
        IsaacBloodID = SearchIsaacBloodID ? EntityRegistry.findGlobalUniqueEntityId() : config.get("entity", "Isaac Blood ID", 126).getInt();
        SearchIsaacBodyID = config.get("Entity", "Search for free Isaac Body ID", true).getBoolean(true);
        IsaacBodyID = SearchIsaacBodyID ? EntityRegistry.findGlobalUniqueEntityId() : config.get("entity", "Isaac Body ID", 127).getInt();
        SearchIsaacHeadID = config.get("Entity", "Search for free Isaac Head ID", true).getBoolean(true);
        IsaacHeadID = SearchIsaacHeadID ? EntityRegistry.findGlobalUniqueEntityId() : config.get("entity", "Isaac Head ID", 128).getInt();
        SearchNecroVillagerID = config.get("Entity", "Search for free Necro Villager ID", true).getBoolean(true);
        NecroVillagerID = SearchNecroVillagerID ? EntityRegistry.findGlobalUniqueEntityId() : config.get("entity", "Necro Villager ID", 129).getInt();

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
