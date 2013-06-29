package com.sirolf2009.necromancy;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necromancy.block.BlockNecromancy;
import com.sirolf2009.necromancy.core.handler.EventHandler;
import com.sirolf2009.necromancy.core.handler.PacketHandler;
import com.sirolf2009.necromancy.core.proxy.CommonProxy;
import com.sirolf2009.necromancy.creativetab.CreativeTabNecro;
import com.sirolf2009.necromancy.entity.EntityNecromancy;
import com.sirolf2009.necromancy.generation.WorldGenerator;
import com.sirolf2009.necromancy.item.ItemGeneric;
import com.sirolf2009.necromancy.item.ItemNecroSkull;
import com.sirolf2009.necromancy.item.ItemNecromancy;
import com.sirolf2009.necromancy.lib.ConfigurationNecromancy;
import com.sirolf2009.necromancy.lib.ReferenceNecromancy;

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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

@Mod(modid = ReferenceNecromancy.MOD_ID, name = ReferenceNecromancy.MOD_NAME, version = ReferenceNecromancy.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "NecromancyMod" }, packetHandler = PacketHandler.class)
public class Necromancy {
    
    public static final CreativeTabs tabNecromancy = new CreativeTabNecro(CreativeTabs.getNextID(), "Necromancy", 1).setBackgroundImageName("creative_necro_gui.png");
    public static final CreativeTabs tabNecromancyBodyParts = new CreativeTabNecro(CreativeTabs.getNextID(), "BodyParts", 2).setBackgroundImageName("creative_necro_gui.png");
    
    public static List<String> specialFolk = new ArrayList<String>();

    public static int maxSpawn = -1;

    public static Logger loggerNecromancy;

    public static PacketHandler PacketHandler = new PacketHandler();
    public static EventHandler EventHandler = new EventHandler();

    @SidedProxy(clientSide = "com.sirolf2009.necromancy.core.proxy.ClientProxy", serverSide = "com.sirolf2009.necromancy.core.proxy.CommonProxy")
    public static CommonProxy Proxy;

    @Mod.Instance("necromancy")
    public static Necromancy Instance;

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
        loggerNecromancy = Logger.getLogger("necromancy");
        loggerNecromancy.setParent(FMLLog.getLogger());

        ConfigurationNecromancy.initProperties(event);

        NecroEntityBase.organID = ConfigurationNecromancy.OrgansID;

        MinecraftForge.EVENT_BUS.register(EventHandler);

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

        if (ConfigurationNecromancy.InitDuringPreInit) {
            init();
        }

    }

    @Mod.Init
    public void init(FMLInitializationEvent event) {
        if (!ConfigurationNecromancy.InitDuringPreInit) {
            init();
        }
    }

    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent event) {
        ItemNecroSkull.initSkulls();
        Proxy.refreshTextures();
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {
        EventHandler.initCommands(event);
    }

    @ServerStarted
    public void serverStarted(FMLServerStartedEvent event) {
        if (new File("server.properties").exists()) {
            PropertyManager manager = new PropertyManager(new File("server.properties"), null);
            maxSpawn = manager.getIntProperty("max_minion_spawn", -1);
        }
    }

    private void init() {
        LanguageRegistry.instance().addStringLocalization("itemGroup.Necromancy", "en_US", "Necromancy");
        LanguageRegistry.instance().addStringLocalization("itemGroup.BodyParts", "en_US", "Bodyparts");
        EntityNecromancy.initEntities();
        ItemNecromancy.initItems();
        EntityNecromancy.initEntities();
        BlockNecromancy.initBlocks();
        initRecipes();
        Proxy.init();
        LiquidDictionary.getOrCreateLiquid("blood", new LiquidStack(BlockNecromancy.bloodStill, 1));
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("blood", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(ItemNecromancy.bucketBlood), new ItemStack(Item.bucketEmpty)));
        VillagerRegistry.instance().registerVillageCreationHandler(PacketHandler);
        ArrayList<Class<PacketHandler>> villageComponentsList = new ArrayList<Class<PacketHandler>>();
        villageComponentsList.add(PacketHandler.class);

        GameRegistry.registerWorldGenerator(new WorldGenerator());
    }

    private void initRecipes() {
        GameRegistry.addRecipe(new ItemStack(ItemNecromancy.necronomicon, 1), new Object[] { "LSL", "IBF", "LNL", Character.valueOf('B'), Item.book, Character.valueOf('L'), Item.leather, Character.valueOf('S'), ItemGeneric.getItemStackFromName("Jar of Blood"), Character.valueOf('I'), new ItemStack(Item.dyePowder, 1, 0), Character.valueOf('F'), Item.feather, Character.valueOf('N'), Item.netherStalkSeeds });
        GameRegistry.addRecipe(ItemGeneric.getItemStackFromName("Bone Needle"), new Object[] { "X", Character.valueOf('X'), new ItemStack(Item.dyePowder, 1, 15) });
        GameRegistry.addRecipe(new ItemStack(ItemNecromancy.scythe, 1), new Object[] { "IH", " S", " B", Character.valueOf('I'), Block.obsidian, 'H', Item.hoeIron, 'S', Item.stick, 'B', ItemGeneric.getItemStackFromName("Jar of Blood") });
        GameRegistry.addRecipe(new ItemStack(ItemNecromancy.scytheBone, 1), new Object[] { "IH", " S", " B", Character.valueOf('I'), Block.obsidian, 'H', ItemNecromancy.scythe, 'S', Item.bone, 'B', Item.diamond });
        GameRegistry.addRecipe(new ItemStack(BlockNecromancy.sewing, 1), new Object[] { "III", "ISB", "III", 'I', Item.ingotIron, 'S', Item.silk, 'B', ItemGeneric.getItemStackFromName("Bone Needle") });
        GameRegistry.addRecipe(ItemGeneric.getItemStackFromName("Brain on a Stick"), new Object[] { "# ", " X", '#', Item.fishingRod, 'X', new ItemStack(ItemNecromancy.organs, 1, 0) });
        // GameRegistry.addRecipe(new ItemStack(skullWall, 6), new Object[] {
        // "#S#", "#SS", "###", '#', Block.obsidian, 'S', skull });
        // GameRegistry.addRecipe(new ItemStack(skullWall, 6), new Object[] {
        // "#S#", "#SS", "###", '#', Block.obsidian, 'S', Item.skull });
        GameRegistry.addShapelessRecipe(ItemGeneric.getItemStackFromName("Jar of Blood", 8), new Object[] { new ItemStack(ItemNecromancy.bucketBlood), Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle, Item.glassBottle });
        GameRegistry.addShapelessRecipe(new ItemStack(ItemNecromancy.bucketBlood), new Object[] { Item.bucketEmpty, ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood"), ItemGeneric.getItemStackFromName("Jar of Blood") });
    }
}