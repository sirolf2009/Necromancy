package com.sirolf2009.necromancy.client.renderer;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.sirolf2009.necromancy.client.model.ModelMinion;

public class RenderMinion extends RenderLiving {
    public static ModelMinion model = new ModelMinion();

    public RenderMinion() {
        super(model, 1.0F);
        model.renderer = this;
        setRenderPassModel(model);
    }
    
    public void bindTexture(ResourceLocation par1ResourceLocation) {
        func_110776_a(par1ResourceLocation);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return new ResourceLocation("textures/entity/zombie/zombie.png");
    }
}
