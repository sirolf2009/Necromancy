package com.sirolf2009.necromancy;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraft.stats.Achievement;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necroapi.NecroEntityRegistry;
import com.sirolf2009.necromancy.block.BlockAltar;
import com.sirolf2009.necromancy.block.BlockAltarBlock;
import com.sirolf2009.necromancy.block.BlockBloodFlowing;
import com.sirolf2009.necromancy.block.BlockBloodStationary;
import com.sirolf2009.necromancy.block.BlockSewing;
import com.sirolf2009.necromancy.core.handler.EventHandler;
import com.sirolf2009.necromancy.core.handler.PacketHandler;
import com.sirolf2009.necromancy.core.proxy.CommonProxy;
import com.sirolf2009.necromancy.creativetab.CreativeTabNecro;
import com.sirolf2009.necromancy.entity.EntityIsaacBlood;
import com.sirolf2009.necromancy.entity.EntityIsaacBody;
import com.sirolf2009.necromancy.entity.EntityIsaacHead;
import com.sirolf2009.necromancy.entity.EntityIsaacNormal;
import com.sirolf2009.necromancy.entity.EntityMinion;
import com.sirolf2009.necromancy.entity.EntityNightCrawler;
import com.sirolf2009.necromancy.entity.EntityTear;
import com.sirolf2009.necromancy.entity.EntityTearBlood;
import com.sirolf2009.necromancy.entity.EntityTeddy;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityCaveSpider;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityChicken;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityCow;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityCreeper;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityEnderman;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityIsaac;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityMooshroom;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityPig;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityPigZombie;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntitySheep;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntitySkeleton;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntitySpider;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntitySquid;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityVillager;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityWitch;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityZombie;
import com.sirolf2009.necromancy.generation.WorldGenerator;
import com.sirolf2009.necromancy.item.ItemBodyPart;
import com.sirolf2009.necromancy.item.ItemBucketBlood;
import com.sirolf2009.necromancy.item.ItemIsaacsHead;
import com.sirolf2009.necromancy.item.ItemNecroSkull;
import com.sirolf2009.necromancy.item.ItemNecromancy;
import com.sirolf2009.necromancy.item.ItemNecronomicon;
import com.sirolf2009.necromancy.item.ItemOrgans;
import com.sirolf2009.necromancy.item.ItemScythe;
import com.sirolf2009.necromancy.item.ItemSpawner;
import com.sirolf2009.necromancy.lib.Reference;
import com.sirolf2009.necromancy.tileentity.TileEntityAltar;
import com.sirolf2009.necromancy.tileentity.TileEntitySewing;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "NecromancyMod" }, packetHandler = PacketHandler.class)
public class Necromancy {
    public static final CreativeTabs tabNecromancy = new CreativeTabNecro(CreativeTabs.getNextID(), "Necromancy", 1).setBackgroundImageName("creative_necro_gui.png");
    public static final CreativeTabs tabNecromancyBodyParts = new CreativeTabNecro(CreativeTabs.getNextID(), "BodyParts", 2).setBackgroundImageName("creative_necro_gui.png");
    public static Achievement NecronomiconAchieve;
    public static Achievement SpawnAchieve;
    public static Achievement TeddyAchieve;
    public static Achievement SewingAchieve;
    public static Achievement AltarAchieve;
    AchievementPage achievePage;
    public static List<String> specialFolk = new ArrayList<String>();

    public static int ItemID;
    public static int OrgansID;
    public static int NecronomiconID;
    public static int ScytheID;
    public static int BodyPartID;
    public static int SkullID;
    public static int IsaacsHeadID;
    public static int SpawnerID;
    public static int AltarID;
    public static int AltarBlockID;
    public static int SewingID;
    public static int BloodStationaryID;
    public static int BloodFlowingID;
    // public static int SkullWallID;
    public static int BucketBloodID;
    public static int NecroVillagerID;
    public static int TeddyID;
    public static int IsaacID;
    public static int maxSpawn = -1;
    public static boolean Christmas = false;
    public static boolean renderSpecialScythe = false;

    public static Item necroArmorHead;
    public static Item necroArmorTorso;
    public static Item necroArmorLeggings;
    public static Item necroArmorBoots;
    public static Item necromanticItems;
    public static Item necronomicon;
    public static Item scythe;
    public static Item bucketBlood;
    public static Item organs;
    public static Item bodyparts;
    public static Item spawner;
    // public static Item skull;
    public static Item isaacsHead;
    public static Block altar;
    public static Block altarBlock;
    public static Block sewing;
    public static BlockBloodFlowing bloodFlowing;
    public static BlockBloodStationary bloodStill;
    // public static Block skullWall;

    public static EnumArmorMaterial isaac = EnumHelper.addArmorMaterial("Isaac", Integer.MAX_VALUE, new int[] { 0, 0, 0, 0 }, 0);

    public static Logger logger;

    public static PacketHandler packetHandler = new PacketHandler();
    public static EventHandler eventHandler = new EventHandler();

    @SidedProxy(clientSide = "com.sirolf2009.necromancy.core.proxy.ClientProxy", serverSide = "com.sirolf2009.necromancy.core.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("necromancy")
    public static Necromancy instance;

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
        logger = Logger.getLogger("necromancy");
        logger.setParent(FMLLog.getLogger());

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        AltarID = config.getBlock("Altar", 2621).getInt();
        AltarBlockID = config.getBlock("Altar Building Block", 2622).getInt();
        SewingID = config.getBlock("Sewing", 2623).getInt();
        BloodStationaryID = config.getBlock("BloodStationary", 2625).getInt();
        BloodFlowingID = BloodStationaryID - 1;
        // SkullWallID = config.getBlock("Skull Wall", 2626).getInt();

        BucketBloodID = config.getItem("BloodStationary", 2621).getInt();
        ScytheID = config.getItem("Scythe", 2622).getInt();
        ItemID = config.getItem("main items", "item", 2623).getInt();
        NecronomiconID = config.getItem("necronomicon", "item", 2624).getInt();
        OrgansID = config.getItem("organs", "item", 2625).getInt();
        BodyPartID = config.getItem("bodyparts", "item", 2626).getInt();
        IsaacsHeadID = config.getItem("Isaac's Severed Head", "armor", 2627).getInt();
        SpawnerID = config.getItem("spawner", "item", 2628).getInt();

        Christmas = config.get("Other", "christmas hats", false).getBoolean(false);
        renderSpecialScythe = config.get("Other", "special scythes (only for a select number of people)", true).getBoolean(false);
        NecroVillagerID = config.get("Other", "NecroVillagerID", 666).getInt();
        if (config.hasChanged()) {
            config.save();
        }

        NecroEntityBase.organID = OrgansID;

        MinecraftForge.EVENT_BUS.register(eventHandler);
    }

    @Mod.Init
    public void init(FMLInitializationEvent event) {
        LanguageRegistry.instance().addStringLocalization("itemGroup.Necromancy", "en_US", "Necromancy");
        LanguageRegistry.instance().addStringLocalization("itemGroup.BodyParts", "en_US", "Bodyparts");
        initItems();
        initEntities();
        spawner = new ItemSpawner(SpawnerID).setUnlocalizedName("NecroSpawner");
        LanguageRegistry.addName(new ItemStack(spawner, 1, 0), "Isaac's Soul Heart");
        initBlocks();
        initRecipes();
        proxy.init();
        LiquidDictionary.getOrCreateLiquid("blood", new LiquidStack(bloodStill, 1));
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("blood", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketBlood), new ItemStack(Item.bucketEmpty)));
        VillagerRegistry.instance().registerVillageCreationHandler(packetHandler);
        ArrayList<Class<PacketHandler>> villageComponentsList = new ArrayList<Class<PacketHandler>>();
        villageComponentsList.add(PacketHandler.class);
        NecronomiconAchieve = new Achievement(666, "NecronomiconAchieve", 0, 0, necronomicon, null).registerAchievement();
        SewingAchieve = new Achievement(666 + 1, "SewingAchieve", -2, 0, sewing, NecronomiconAchieve).registerAchievement();
        AltarAchieve = new Achievement(666 + 2, "AltarAchieve", 0, 2, altar, NecronomiconAchieve).registerAchievement();
        SpawnAchieve = new Achievement(666 + 3, "SpawnAchieve", 2, 4, necromanticItems, AltarAchieve).registerAchievement().setSpecial();

        addAchievementLocalizations();
        achievePage = new AchievementPage("Necromancy", new Achievement[] { NecronomiconAchieve, SpawnAchieve, AltarAchieve, SewingAchieve });
        AchievementPage.registerAchievementPage(achievePage);

        GameRegistry.registerWorldGenerator(new WorldGenerator());

        try {
            URL url = new URL("https://dl.dropboxusercontent.com/u/50553915/necromancy/specialFolk.txt");
            Scanner s = new Scanner(url.openStream());
            while (s.hasNext()) {
                specialFolk.add(s.nextLine());
            }
            s.close();
        } catch (IOException e) {
            System.err.println("not connected to the internet, special scythes are de-activated");
        }
    }

    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent event) {
        ItemNecroSkull.initSkulls();
        proxy.refreshTextures();
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {
        eventHandler.initCommands(event);
    }

    @ServerStarted
    public void serverStarted(FMLServerStartedEvent event) {
        if (new File("server.properties").exists()) {
            PropertyManager manager = new PropertyManager(new File("server.properties"), null);
            maxSpawn = manager.getIntProperty("max_minion_spawn", -1);
        }
    }

    private void initEntities() {
        int modIDs = 0;

        TeddyID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityTeddy.class, "teddyNecro", TeddyID, new Color(99, 69, 29).getRGB(), Color.red.getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.teddyNecro.name", "en_US", "Teddy Bear");

        EntityRegistry.registerGlobalEntityID(EntityNightCrawler.class, "NightCrawler", EntityRegistry.findGlobalUniqueEntityId(), new Color(6, 6, 6).getRGB(), new Color(13, 13, 13).getRGB());
        EntityRegistry.registerModEntity(EntityNightCrawler.class, "NightCrawler", modIDs++, this, 80, 1, true);
        LanguageRegistry.instance().addStringLocalization("entity.NightCrawler.name", "en_US", "Night Crawler");
        EntityRegistry.addSpawn(EntityNightCrawler.class, 1, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.swampland);

        IsaacID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityIsaacNormal.class, "IsaacNormal", IsaacID, new Color(6, 6, 6).getRGB(), new Color(204, 153, 153).getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.IsaacNormal.name", "en_US", "Isaac");

        EntityRegistry.registerGlobalEntityID(EntityIsaacBlood.class, "IsaacBlood", EntityRegistry.findGlobalUniqueEntityId(), new Color(16, 6, 6).getRGB(), new Color(214, 153, 153).getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.IsaacBlood.name", "en_US", "Isaac Blood Mode");

        EntityRegistry.registerGlobalEntityID(EntityIsaacHead.class, "IsaacHead", EntityRegistry.findGlobalUniqueEntityId(), new Color(26, 6, 6).getRGB(), new Color(214, 153, 153).getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.IsaacHead.name", "en_US", "Isaac's Head");

        EntityRegistry.registerGlobalEntityID(EntityIsaacBody.class, "IsaacBody", EntityRegistry.findGlobalUniqueEntityId(), new Color(36, 6, 6).getRGB(), new Color(214, 153, 153).getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.IsaacBody.name", "en_US", "Isaac's Body");

        EntityRegistry.registerGlobalEntityID(EntityMinion.class, "minionNecro", EntityRegistry.findGlobalUniqueEntityId(), new Color(99, 69, 29).getRGB(), Color.red.getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.minionNecro.name", "en_US", "Minion");

        EntityRegistry.registerModEntity(EntityTear.class, "TearNormal", 6, this, 144, 2, true);
        EntityRegistry.registerModEntity(EntityTearBlood.class, "TearBlood", 7, this, 144, 2, true);

        NecroVillagerID = EntityRegistry.findGlobalUniqueEntityId();
        VillagerRegistry.instance().registerVillagerType(NecroVillagerID, Reference.LOC_RESOURCES_TEXTURES_ENTITIES + "/villagerNecro.png");
        VillagerRegistry.instance().registerVillageTradeHandler(NecroVillagerID, packetHandler);

        NecroEntityRegistry.RegisterEntity(new NecroEntitySkeleton());
        NecroEntityRegistry.RegisterEntity(new NecroEntityZombie());
        NecroEntityRegistry.RegisterEntity(new NecroEntityPig());
        NecroEntityRegistry.RegisterEntity(new NecroEntityCow());
        NecroEntityRegistry.RegisterEntity(new NecroEntityPigZombie());
        NecroEntityRegistry.RegisterEntity(new NecroEntityCreeper());
        NecroEntityRegistry.RegisterEntity(new NecroEntitySpider());
        NecroEntityRegistry.RegisterEntity(new NecroEntityEnderman());
        // NecroEntityRegistry.RegisterEntity(new NecroEntitySlimeSmall());
        NecroEntityRegistry.RegisterEntity(new NecroEntityIsaac());
        NecroEntityRegistry.RegisterEntity(new NecroEntityChicken());
        NecroEntityRegistry.RegisterEntity(new NecroEntityMooshroom());
        NecroEntityRegistry.RegisterEntity(new NecroEntityVillager());
        NecroEntityRegistry.RegisterEntity(new NecroEntityWitch());
        NecroEntityRegistry.RegisterEntity(new NecroEntitySquid());
        NecroEntityRegistry.RegisterEntity(new NecroEntityCaveSpider());
        NecroEntityRegistry.RegisterEntity(new NecroEntitySheep());
    }

    private void initBlocks() {
        altar = new BlockAltar(AltarID).setHardness(4);
        altar.setUnlocalizedName("Summoning Altar");
        GameRegistry.registerBlock(altar, "Summoning Altar");
        GameRegistry.registerTileEntity(TileEntityAltar.class, "Altar");
        LanguageRegistry.addName(altar, "Summoning Altar");

        altarBlock = new BlockAltarBlock(AltarBlockID).setHardness(4);
        altarBlock.setUnlocalizedName("Altar Building Block");
        GameRegistry.registerBlock(altarBlock, "Altar Building Block");
        LanguageRegistry.addName(altarBlock, "Altar Building Block");

        sewing = new BlockSewing(SewingID, Material.iron).setHardness(4);
        sewing.setUnlocalizedName("Sewing Machine");
        GameRegistry.registerBlock(sewing, "Sewing Machine");
        GameRegistry.registerTileEntity(TileEntitySewing.class, "Sewing");
        LanguageRegistry.addName(sewing, "Sewing Machine");

        bloodFlowing = new BlockBloodFlowing(BloodFlowingID);
        bloodStill = new BlockBloodStationary(BloodStationaryID);
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

    private void initItems() {
        // int skullID = Item.skull.itemID;
        // Item.itemsList[skullID] = null;
        necromanticItems = new ItemNecromancy(ItemID).setUnlocalizedName("ItemNecromancy");
        necronomicon = new ItemNecronomicon(NecronomiconID).setUnlocalizedName("Necronomicon");
        for (int x = 0; x < ItemNecromancy.names.length; x++) {
            LanguageRegistry.addName(new ItemStack(necromanticItems, 1, x), ItemNecromancy.names[x]);
        }
        LanguageRegistry.addName(necronomicon, "Necronomicon");
        scythe = new ItemScythe(ScytheID).setUnlocalizedName("ItemScythe");
        LanguageRegistry.addName(scythe, "Blood Scythe");
        bucketBlood = new ItemBucketBlood(BucketBloodID, BloodFlowingID).setUnlocalizedName("BucketBlood");
        LanguageRegistry.addName(bucketBlood, "Blood Bucket");
        organs = new ItemOrgans(OrgansID).setUnlocalizedName("Organs");
        for (int x = 0; x < ItemOrgans.names.length; x++) {
            LanguageRegistry.addName(new ItemStack(organs, 1, x), ItemOrgans.names[x]);
        }
        bodyparts = new ItemBodyPart(BodyPartID).setUnlocalizedName("BodyParts");
        // skull = new ItemNecroSkull(skullID);
        isaacsHead = new ItemIsaacsHead(IsaacsHeadID, isaac, proxy.addArmour("Isaac"), 0);
        LanguageRegistry.addName(isaacsHead, "Isaac's Severed Head");
    }

    private void initRecipes() {
        GameRegistry.addRecipe(new ItemStack(necronomicon, 1), new Object[] { "LSL", "IBF", "LNL", Character.valueOf('B'), Item.book, Character.valueOf('L'), Item.leather, Character.valueOf('S'), ItemNecromancy.getItemStackFromName("Jar of Blood"), Character.valueOf('I'), new ItemStack(Item.dyePowder, 1, 0), Character.valueOf('F'), Item.feather, Character.valueOf('N'), Item.netherStalkSeeds });
        GameRegistry.addRecipe(ItemNecromancy.getItemStackFromName("Bone Needle"), new Object[] { "X", Character.valueOf('X'), new ItemStack(Item.dyePowder, 1, 15) });
        GameRegistry.addRecipe(new ItemStack(scythe, 1), new Object[] { "IH", " S", " B", Character.valueOf('I'), Block.obsidian, 'H', Item.hoeIron, 'S', Item.stick, 'B', ItemNecromancy.getItemStackFromName("Jar of Blood") });
        GameRegistry.addRecipe(new ItemStack(sewing, 1), new Object[] { "III", "ISB", "III", 'I', Item.ingotIron, 'S', Item.silk, 'B', ItemNecromancy.getItemStackFromName("Bone Needle") });
        GameRegistry.addRecipe(ItemNecromancy.getItemStackFromName("Brain on a Stick"), new Object[] { "# ", " X", '#', Item.fishingRod, 'X', new ItemStack(organs, 1, 0) });
        // GameRegistry.addRecipe(new ItemStack(skullWall, 6), new Object[] {
        // "#S#", "#SS", "###", '#', Block.obsidian, 'S', skull });
        // GameRegistry.addRecipe(new ItemStack(skullWall, 6), new Object[] {
        // "#S#", "#SS", "###", '#', Block.obsidian, 'S', Item.skull });
        GameRegistry.addShapelessRecipe(ItemNecromancy.getItemStackFromName("Jar of Blood", 8), new Object[] { new ItemStack(bucketBlood), Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle });
        GameRegistry.addShapelessRecipe(new ItemStack(bucketBlood), new Object[] { Item.bucketEmpty, ItemNecromancy.getItemStackFromName("Jar of Blood"), ItemNecromancy.getItemStackFromName("Jar of Blood"), ItemNecromancy.getItemStackFromName("Jar of Blood"), ItemNecromancy.getItemStackFromName("Jar of Blood"), ItemNecromancy.getItemStackFromName("Jar of Blood"), ItemNecromancy.getItemStackFromName("Jar of Blood"), ItemNecromancy.getItemStackFromName("Jar of Blood"), ItemNecromancy.getItemStackFromName("Jar of Blood") });
    }

    public void addAchievementLocalizations() {
        addAchievementName("NecronomiconAchieve", "Hardly Evil");
        addAchievementDesc("NecronomiconAchieve", "Craft a Necronomicon");
        addAchievementName("SpawnAchieve", "First Spawn");
        addAchievementDesc("SpawnAchieve", "spawn a minion");
        addAchievementName("TeddyAchieve", "Cotton Menace");
        addAchievementDesc("TeddyAchieve", "Spawn a teddy");
        addAchievementName("SewingAchieve", "Stitches");
        addAchievementDesc("SewingAchieve", "Craft a Sewing Machine");
        addAchievementName("AltarAchieve", "Pure Evil");
        addAchievementDesc("AltarAchieve", "Create an Altar");
    }

    private void addAchievementName(String ach, String name) {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
    }

    private void addAchievementDesc(String ach, String desc) {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
    }
}