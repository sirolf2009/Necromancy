package com.sirolf2009.necromancy.entity.necroapi;

import net.minecraft.item.ItemStack;

import com.sirolf2009.necroapi.ISkull;
import com.sirolf2009.necroapi.NecroEntityBiped;
import com.sirolf2009.necroapi.NecroEntityRegistry;
import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntityWither extends NecroEntityBiped implements ISkull {

    public NecroEntityWither() {
        super("Wither");
        headItem = new ItemStack(Necromancy.skull, 1, NecroEntityRegistry.registeredSkullEntities.size());
        torsoItem = ItemBodyPart.getItemStackFromName("Zombie Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Zombie Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Zombie Legs", 1);
        texture = "/mob/wither.png";
    }

    @Override
    public String getSkullModelTexture() {
        return "/mob/wither.png";
    }

    @Override
    public String getSkullIconTexture() {
        return "skull_wither";
    }

}
