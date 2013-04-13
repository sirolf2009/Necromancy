package com.sirolf2009.necroapi;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class BodyPart extends ModelRenderer {

    /**
     * The base constructor for body parts, call this if you are creating arms
     * or heads
     */
    public BodyPart(NecroEntityBase base, ModelBase par1ModelBase, int par2, int par3) {
        super(par1ModelBase, par2, par3);
        entity = base;
        name = base.mobName;
        textureHeight = base.textureHeight;
        textureWidth = base.textureWidth;
    }

    public String name;
    public NecroEntityBase entity;

    /** call this if you are creating legs */
    public BodyPart(NecroEntityBase base, float torsoPos[], ModelBase par1ModelBase, int par2, int par3) {
        this(base, par1ModelBase, par2, par3);
        this.torsoPos = new float[3];
        this.torsoPos = torsoPos;
    }

    public float torsoPos[];

    /** call this if you are creating torsos */
    public BodyPart(NecroEntityBase base, float armLeftPos[], float armRightPos[], float headPos[], ModelBase par1ModelBase, int par2, int par3) {
        this(base, par1ModelBase, par2, par3);
        this.armLeftPos = new float[3];
        this.armLeftPos = armLeftPos;
        this.armRightPos = new float[3];
        this.armRightPos = armRightPos;
        this.headPos = new float[3];
        this.headPos = headPos;
    }

    public float armLeftPos[];
    public float armRightPos[];
    public float headPos[];

}
