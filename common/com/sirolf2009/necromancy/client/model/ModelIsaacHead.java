package com.sirolf2009.necromancy.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelIsaacHead extends ModelBase {
    // fields
    ModelRenderer neck4;
    ModelRenderer Neck3;
    ModelRenderer Neck2;
    ModelRenderer head;
    ModelRenderer Neck1;

    public ModelIsaacHead() {
        textureWidth = 64;
        textureHeight = 32;

        neck4 = new ModelRenderer(this, 0, 0);
        neck4.addBox(0F, 0F, 0F, 1, 3, 1);
        neck4.setRotationPoint(1F, 2F, 1F);
        neck4.setTextureSize(64, 32);
        neck4.mirror = true;
        setRotation(neck4, 0F, 0F, 0F);
        Neck3 = new ModelRenderer(this, 0, 0);
        Neck3.addBox(0F, 0F, 0F, 1, 1, 1);
        Neck3.setRotationPoint(0F, 2F, 1F);
        Neck3.setTextureSize(64, 32);
        Neck3.mirror = true;
        setRotation(Neck3, 0F, 0F, 0F);
        Neck2 = new ModelRenderer(this, 0, 0);
        Neck2.addBox(0F, 0F, 0F, 1, 1, 1);
        Neck2.setRotationPoint(0F, 2F, 0F);
        Neck2.setTextureSize(64, 32);
        Neck2.mirror = true;
        setRotation(Neck2, 0F, 0F, 0F);
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 10, 9, 8);
        head.setRotationPoint(0F, 1F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        Neck1 = new ModelRenderer(this, 0, 0);
        Neck1.addBox(0F, 0F, 0F, 1, 1, 1);
        Neck1.setRotationPoint(1F, 2F, -1F);
        Neck1.setTextureSize(64, 32);
        Neck1.mirror = true;
        setRotation(Neck1, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        neck4.render(f5);
        Neck3.render(f5);
        Neck2.render(f5);
        head.render(f5);
        Neck1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
