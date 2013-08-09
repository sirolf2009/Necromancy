package com.sirolf2009.necromancy.entity;

import java.awt.Color;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.WorldType;

import com.sirolf2009.necroapi.NecroEntityRegistry;
import com.sirolf2009.necromancy.Necromancy;
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
import com.sirolf2009.necromancy.lib.ConfigurationNecromancy;
import com.sirolf2009.necromancy.lib.ReferenceNecromancy;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityNecromancy {

    public static int TeddyID;
    public static int IsaacID;

    public static void initEntities() {
        TeddyID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityTeddy.class, "teddyNecro", TeddyID, new Color(99, 69, 29).getRGB(), Color.red.getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.teddyNecro.name", "en_US", "Teddy Bear");

        EntityRegistry.registerGlobalEntityID(EntityNightCrawler.class, "NightCrawler", EntityRegistry.findGlobalUniqueEntityId(), new Color(6, 6, 6).getRGB(), new Color(13, 13, 13).getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.NightCrawler.name", "en_US", "Night Crawler");
        EntityRegistry.addSpawn(EntityNightCrawler.class, 1, 1, 1, EnumCreatureType.monster, WorldType.base12Biomes);

        IsaacID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityIsaacNormal.class, "IsaacNormal", IsaacID, new Color(6, 6, 6).getRGB(), new Color(204, 153, 153).getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.IsaacNormal.name", "en_US", "Isaac");

        EntityRegistry.registerGlobalEntityID(EntityIsaacBlood.class, "IsaacBlood", EntityRegistry.findGlobalUniqueEntityId(), new Color(16, 6, 6).getRGB(), new Color(214, 153, 153).getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.IsaacBlood.name", "en_US", "Isaac Blood Mode");

        EntityRegistry.registerGlobalEntityID(EntityIsaacHead.class, "IsaacHead", EntityRegistry.findGlobalUniqueEntityId(), new Color(26, 6, 6).getRGB(), new Color(214, 153, 153).getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.IsaacHead.name", "en_US", "Isaac's Head");

        EntityRegistry.registerGlobalEntityID(EntityIsaacBody.class, "IsaacBody", EntityRegistry.findGlobalUniqueEntityId(), new Color(36, 6, 6).getRGB(), new Color(214, 153, 153).getRGB());
        LanguageRegistry.instance().addStringLocalization("entity.IsaacBody.name", "en_US", "Isaac's Body");

        EntityRegistry.registerGlobalEntityID(EntityMinion.class, "minionNecro", EntityRegistry.findGlobalUniqueEntityId());
        LanguageRegistry.instance().addStringLocalization("entity.minionNecro.name", "en_US", "Minion");

        EntityRegistry.registerModEntity(EntityTear.class, "TearNormal", 6, Necromancy.Instance, 144, 2, true);
        EntityRegistry.registerModEntity(EntityTearBlood.class, "TearBlood", 7, Necromancy.Instance, 144, 2, true);
        
        VillagerRegistry.instance().registerVillagerSkin(ConfigurationNecromancy.NecroVillagerID, ReferenceNecromancy.TEXTURES_ENTITIES_NECROMANCER);
        VillagerRegistry.instance().registerVillageTradeHandler(ConfigurationNecromancy.NecroVillagerID, Necromancy.PacketHandler);

        NecroEntityRegistry.RegisterEntity(new NecroEntitySkeleton());
        NecroEntityRegistry.RegisterEntity(new NecroEntityZombie());
        NecroEntityRegistry.RegisterEntity(new NecroEntityPig());
        NecroEntityRegistry.RegisterEntity(new NecroEntityCow());
        NecroEntityRegistry.RegisterEntity(new NecroEntityPigZombie());
        NecroEntityRegistry.RegisterEntity(new NecroEntityCreeper());
        NecroEntityRegistry.RegisterEntity(new NecroEntitySpider());
        NecroEntityRegistry.RegisterEntity(new NecroEntityEnderman());
        NecroEntityRegistry.RegisterEntity(new NecroEntityIsaac());
        NecroEntityRegistry.RegisterEntity(new NecroEntityChicken());
        NecroEntityRegistry.RegisterEntity(new NecroEntityMooshroom());
        NecroEntityRegistry.RegisterEntity(new NecroEntityVillager());
        NecroEntityRegistry.RegisterEntity(new NecroEntityWitch());
        NecroEntityRegistry.RegisterEntity(new NecroEntitySquid());
        NecroEntityRegistry.RegisterEntity(new NecroEntityCaveSpider());
        NecroEntityRegistry.RegisterEntity(new NecroEntitySheep());
    }
}
