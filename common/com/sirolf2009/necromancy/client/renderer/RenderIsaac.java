package com.sirolf2009.necromancy.client.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import com.sirolf2009.necromancy.entity.EntityIsaacBody;

public class RenderIsaac extends RenderLiving {

    public RenderIsaac(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Override
    public void doRenderLiving(EntityLiving el, double d1, double d2, double d3, float f1, float f2) {
        renderIsaacNormal(el, d1, d2, d3, f1, f2);
    }

    @Override
    public void doRender(Entity el, double d1, double d2, double d3, float f1, float f2) {
        renderIsaacNormal((EntityLiving)el, d1, d2, d3, f1, f2);
    }

    public void renderIsaacNormal(EntityLiving em, double d1, double d2, double d3, float f1, float f2) {
        super.doRenderLiving(em, d1, d2, d3, f1, f2);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return new ResourceLocation("necromancy:textures/entities/Isaac.png");
    }
}
