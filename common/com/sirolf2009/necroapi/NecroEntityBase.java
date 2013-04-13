package com.sirolf2009.necroapi;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.sirolf2009.necromancy.client.model.ModelMinion;

/**
 * The base class for all the necro mobs
 * 
 * @author sirolf2009
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class NecroEntityBase {

    public NecroEntityBase(String mobName) {
        this.mobName = mobName;
        hasHead = true;
        hasTorso = true;
        hasArms = true;
        hasLegs = true;
        textureWidth = 64;
        textureHeight = 32;
        try {
            Class.forName("com.sirolf2009.necromancy.Necromancy");
            organs = Item.itemsList[organID + 256];
            isNecromancyInstalled = true;
            initRecipes();
        } catch (ClassNotFoundException e) {
            System.err.println(mobName + " could not be registered, the necromancy mod is not installed");
            isNecromancyInstalled = false;
        }
    }

    /** Define your recipes here */
    public void initRecipes() {
    }

    /**
     * Use this method to initialize all the ModelRenderers for your mobs head
     * 
     * @param model
     *            - the model
     */
    public BodyPart[] initHead(ModelMinion model) {
        return null;
    }

    /**
     * Use this method to initialize all the ModelRenderers for your mobs torso
     * 
     * @param model
     *            - the model
     */
    public BodyPart[] initTorso(ModelMinion model) {
        return null;
    }

    /**
     * Use this method to initialize all the ModelRenderers for your mobs legs
     * 
     * @param model
     *            - the model
     */
    public BodyPart[] initLegs(ModelMinion model) {
        return null;
    }

    /**
     * Use this method to initialize all the ModelRenderers for your mobs left
     * arm
     * 
     * @param model
     *            - the model
     */
    public BodyPart[] initArmLeft(ModelMinion model) {
        return null;
    }

    /**
     * The method used to initialize the parts and store them in variables
     * 
     * @param model
     *            = the model
     * @return this
     */
    public NecroEntityBase updateParts(ModelMinion model) {
        head = initHead(model);
        torso = initTorso(model);
        armLeft = initArmLeft(model);
        armRight = initArmRight(model);
        legs = initLegs(model);
        return this;
    }

    /**
     * Use this method to initialize all the ModelRenderers for your mobs right
     * arm
     * 
     * @param model
     *            - the model
     */
    public BodyPart[] initArmRight(ModelMinion model) {
        return null;
    }

    /**
     * Use this method to set the rotation for every bodypart
     * 
     * @param entity
     *            - the entity
     * @param part
     *            - the current parts being animated
     * @param location
     *            - the location of the parts (head/torso/armLeft/armRight/legs)
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity, BodyPart[] part, String location) {
    }

    /**
     * Called before rendering
     * 
     * @param entity
     *            - the entity
     * @param part
     *            - the current parts being rendered
     * @param location
     *            - the location of the parts (head/torso/armLeft/armRight/legs)
     * @param model
     *            - the model
     */
    public void preRender(Entity entity, BodyPart[] parts, String location, ModelMinion model) {
    }

    /**
     * Called after rendering
     * 
     * @param entity
     *            - the entity
     * @param part
     *            - the current parts being rendered
     * @param location
     *            - the location of the parts (head/torso/armLeft/armRight/legs)
     * @param model
     *            - the model
     */
    public void postRender(Entity entity, BodyPart[] parts, String location, ModelMinion model) {
    }

    /** The name for your mob */
    public String mobName;
    /** The location of the mobs texture file */
    public String texture;
    /** The item assigned to your mobs head */
    public ItemStack headItem;
    /** The item assigned to your mobs torso */
    public ItemStack torsoItem;
    /** The item assigned to your mobs arms */
    public ItemStack armItem;
    /** The item assigned to your mobs legs */
    public ItemStack legItem;
    /** The recipe assigned to your mobs head */
    public Object[] headRecipe;
    /** The recipe assigned to your mobs torso */
    public Object[] torsoRecipe;
    /** The recipe assigned to your mobs arms */
    public Object[] armRecipe;
    /** The recipe assigned to your mobs legs */
    public Object[] legRecipe;
    /** set to false if your mob doesn't have a head */
    public boolean hasHead;
    /** set to false if your mob doesn't have a torso */
    public boolean hasTorso;
    /** set to false if your mob doesn't have arms */
    public boolean hasArms;
    /** set to false if your mob doesn't have legs */
    public boolean hasLegs;
    /** The item ID for organs */
    public static int organID;
    /** The organs item (Brains, Heart, Muscle, Lungs, Skin) */
    public Item organs;
    /** The textures width */
    public int textureWidth;
    /** The textures height */
    public int textureHeight;
    /** i'm sure you can figure this one out... */
    protected boolean isNecromancyInstalled;
    /** Your entities head */
    public BodyPart[] head;
    /** Your entities torso */
    public BodyPart[] torso;
    /** Your entities armLeft */
    public BodyPart[] armLeft;
    /** Your entities armRight */
    public BodyPart[] armRight;
    /** Your entities legs */
    public BodyPart[] legs;
}
