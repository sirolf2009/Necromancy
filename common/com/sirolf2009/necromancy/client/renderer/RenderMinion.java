package com.sirolf2009.necromancy.client.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;

import com.sirolf2009.necromancy.client.model.ModelMinion;

public class RenderMinion extends RenderLiving {
    public static ModelBase model = new ModelMinion();

    public RenderMinion() {
        super(model, 1.0F);
        setRenderPassModel(model);
    }
}
