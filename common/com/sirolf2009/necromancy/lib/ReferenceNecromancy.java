package com.sirolf2009.necromancy.lib;

import net.minecraft.util.ResourceLocation;

public class ReferenceNecromancy {
    public static final String MOD_ID = "necromancy";
    public static final String MOD_NAME = "Necromancy";
    public static final String MOD_VERSION = "@VERSION@";
    public static final String MOD_FINGERPRINT = "@FINGERPRINT@";

    public static final String LOC_RESOURCES_SOUNDS = "/sounds";
    public static final String LOC_RESOURCES_TEXTURES = "/textures";
    public static final String LOC_RESOURCES_TEXTURES_BLOCKS = LOC_RESOURCES_TEXTURES + "/blocks";
    public static final String LOC_RESOURCES_TEXTURES_ENTITIES = LOC_RESOURCES_TEXTURES + "/entities";
    public static final String LOC_RESOURCES_TEXTURES_GUIS = LOC_RESOURCES_TEXTURES + "/guis";
    public static final String LOC_RESOURCES_TEXTURES_ITEMS = LOC_RESOURCES_TEXTURES + "/items";
    public static final String LOC_RESOURCES_TEXTURES_ITEMS_BODYPARTS = LOC_RESOURCES_TEXTURES_ITEMS + "/bodyparts";
    public static final String LOC_RESOURCES_TEXTURES_MODELS = LOC_RESOURCES_TEXTURES + "/models";
    public static final String LOC_MODELS = "/models";

    public static final ResourceLocation TEXTURES_ENTITIES_NECROMANCER = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_ENTITIES+"/villagerNecro.png");
    public static final ResourceLocation TEXTURES_ENTITIES_NIGHTCRAWLER = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_ENTITIES+"/nightcrawler.png");
    public static final ResourceLocation TEXTURES_ENTITIES_TEDDY = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_ENTITIES+"/teddy.png");
    public static final ResourceLocation TEXTURES_ENTITIES_TEAR = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_ENTITIES+"/TearBlood.png");
    public static final ResourceLocation TEXTURES_ENTITIES_TEARBLOOD = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_ENTITIES+"/Tear.png");

    public static final ResourceLocation TEXTURES_MODELS_SCYTHE = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_MODELS+"/scythe.png");
    public static final ResourceLocation TEXTURES_MODELS_SCYTHEBONE = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_MODELS+"/scytheBone.png");
    public static final ResourceLocation TEXTURES_MODELS_NECRONOMICON = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_MODELS+"/necronomicon.png");
    public static final ResourceLocation TEXTURES_MODELS_ALTAR = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_MODELS+"/altarTexture.png");
    public static final ResourceLocation TEXTURES_MODELS_SEWING = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_MODELS+"/sewingTexture.png");

    public static final ResourceLocation TEXTURES_GUI_ALTAR = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_GUIS+"/altarGui.png");
    public static final ResourceLocation TEXTURES_GUI_SEWING = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_GUIS+"/sewingGui.png");
    
    public static final ResourceLocation TEXTURES_PARTICLES = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES+"/particles.png");
    
    public static final ResourceLocation TEXTURES_MISC_CHRISTMASHAT = new ResourceLocation("necromancy:"+LOC_RESOURCES_TEXTURES_ENTITIES+"/ChristmasHat.png");
}
