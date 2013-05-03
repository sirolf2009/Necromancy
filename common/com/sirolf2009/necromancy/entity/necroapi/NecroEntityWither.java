package com.sirolf2009.necromancy.entity.necroapi;

import com.sirolf2009.necroapi.ISkull;
import com.sirolf2009.necroapi.NecroEntityBiped;
import com.sirolf2009.necromancy.item.ItemBodyPart;

public class NecroEntityWither extends NecroEntityBiped implements ISkull {

    public NecroEntityWither() {
        super("Wither");
        headItem = ItemBodyPart.getItemStackFromName("Wither Head", 1);
        torsoItem = ItemBodyPart.getItemStackFromName("Wither Torso", 1);
        armItem = ItemBodyPart.getItemStackFromName("Wither Arm", 1);
        legItem = ItemBodyPart.getItemStackFromName("Wither Legs", 1);
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
