package com.sirolf2009.necromancy;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraftforge.common.MinecraftForge;

import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necromancy.block.BlockNecromancy;
import com.sirolf2009.necromancy.core.handler.EventHandler;
import com.sirolf2009.necromancy.core.handler.PacketHandler;
import com.sirolf2009.necromancy.core.proxy.CommonProxy;
import com.sirolf2009.necromancy.creativetab.CreativeTabNecro;
import com.sirolf2009.necromancy.entity.EntityNecromancy;
import com.sirolf2009.necromancy.generation.WorldGenerator;
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
    
    public static final CreativeTabs tabNecromancy = new CreativeTabNecro(CreativeTabs.getNextID(), "Necromancy", 1).setBackgroundImageName("necro_gui.png");
    public static final CreativeTabs tabNecromancyBodyParts = new CreativeTabNecro(CreativeTabs.getNextID(), "BodyParts", 2).setBackgroundImageName("necro_gui.png");
    
    public static List<String> specialFolk = new ArrayList<String>();

    public static int maxSpawn = -1;

    public static Logger loggerNecromancy;

    public static PacketHandler PacketHandler = new PacketHandler();
    public static EventHandler EventHandler = new EventHandler();

    @SidedProxy(clientSide = "com.sirolf2009.necromancy.core.proxy.ClientProxy", serverSide = "com.sirolf2009.necromancy.core.proxy.CommonProxy")
    public static CommonProxy Proxy;

    @Mod.Instance("necromancy")
    public static Necromancy Instance;

    @Mod.EventHandler
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

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (!ConfigurationNecromancy.InitDuringPreInit) {
            init();
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ItemNecroSkull.initSkulls();
        Proxy.refreshTextures();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        EventHandler.initCommands(event);
    }

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        if (new File("server.properties").exists()) {
            PropertyManager manager = new PropertyManager(new File("server.properties"), null);
            maxSpawn = manager.getIntProperty("max_minion_spawn", -1);
        }
    }

    private void init() {
        LanguageRegistry.instance().addStringLocalization("itemGroup.Necromancy", "en_US", "Necromancy");
        LanguageRegistry.instance().addStringLocalization("itemGroup.BodyParts", "en_US", "Bodyparts");
        ItemNecromancy.initItems();
        EntityNecromancy.initEntities();
        BlockNecromancy.initBlocks();
        Proxy.init();
        VillagerRegistry.instance().registerVillageCreationHandler(PacketHandler);
        ArrayList<Class<PacketHandler>> villageComponentsList = new ArrayList<Class<PacketHandler>>();
        villageComponentsList.add(PacketHandler.class);

        GameRegistry.registerWorldGenerator(new WorldGenerator());
    }
}