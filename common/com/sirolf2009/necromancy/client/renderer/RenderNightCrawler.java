package com.sirolf2009.necromancy.client.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.sirolf2009.necromancy.entity.EntityNightCrawler;
import com.sirolf2009.necromancy.lib.ReferenceNecromancy;

public class RenderNightCrawler extends RenderLiving {

    public RenderNightCrawler(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Override
    public void doRenderLiving(EntityLiving el, double d1, double d2, double d3, float f1, float f2) {
        renderTeddy((EntityNightCrawler) el, d1, d2, d3, f1, f2);
    }

    @Override
    public void doRender(Entity el, double d1, double d2, double d3, float f1, float f2) {
        renderTeddy((EntityNightCrawler) el, d1, d2, d3, f1, f2);
    }

    public void renderTeddy(EntityNightCrawler em, double d1, double d2, double d3, float f1, float f2) {
        super.doRenderLiving(em, d1, d2, d3, f1, f2);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return ReferenceNecromancy.TEXTURES_ENTITIES_NIGHTCRAWLER;
    }
}
