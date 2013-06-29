package com.sirolf2009.necromancy.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.sirolf2009.necromancy.lib.ReferenceNecromancy;

import cpw.mods.fml.client.FMLClientHandler;

public class ModelScytheSpecial extends ModelBase {
    // fields
    ModelRenderer HandleMiddle;
    ModelRenderer HandleBottom;
    ModelRenderer HandleTop;
    ModelRenderer Joint;
    ModelRenderer Blade;
    ModelRenderer BladeBaseRight;
    ModelRenderer BladeBaseLeft;

    private IModelCustom modelTutBox;

    public ModelScytheSpecial() {
        modelTutBox = AdvancedModelLoader.loadModel("/mods/necromancy/models/scythe.obj");
    }

    public void render() {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(ReferenceNecromancy.LOC_RESOURCES_TEXTURES_MODELS + "/SwordMetal.jpg");
        modelTutBox.renderPart("Blade_Blade_Material");
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/necromancy/models/cloth.jpg");
        modelTutBox.renderPart("Joint2_Joint2_Material");
        modelTutBox.renderPart("Joint1_Joint1_Material");
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(ReferenceNecromancy.LOC_RESOURCES_TEXTURES_MODELS + "/GunTex.jpg");
        modelTutBox.renderPart("Handle_Handle_Material");
    }
}
