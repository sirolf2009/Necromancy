package com.sirolf2009.necromancy.achievement;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class AchievementNecromancy {

    public static Achievement NecronomiconAchieve;
    public static Achievement SpawnAchieve;
    public static Achievement TeddyAchieve;
    public static Achievement SewingAchieve;
    public static Achievement AltarAchieve;
    public static AchievementPage achievePage;
    
    public static void initAchievements() {
        NecronomiconAchieve = new Achievement(666, "NecronomiconAchieve", 0, 0, Item.book, null).registerAchievement();
        SewingAchieve = new Achievement(666 + 1, "SewingAchieve", -2, 0, Item.book, NecronomiconAchieve).registerAchievement();
        AltarAchieve = new Achievement(666 + 2, "AltarAchieve", 0, 2, Item.book, NecronomiconAchieve).registerAchievement();
        SpawnAchieve = new Achievement(666 + 3, "SpawnAchieve", 2, 4, Item.book, AltarAchieve).registerAchievement().setSpecial();

        addAchievementLocalizations();
        achievePage = new AchievementPage("Necromancy", new Achievement[] { NecronomiconAchieve, SpawnAchieve, AltarAchieve, SewingAchieve });
        AchievementPage.registerAchievementPage(achievePage);
    }
    
    public static void addAchievementLocalizations() {
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

    private static void addAchievementName(String ach, String name) {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
    }

    private static void addAchievementDesc(String ach, String desc) {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
    }

}
