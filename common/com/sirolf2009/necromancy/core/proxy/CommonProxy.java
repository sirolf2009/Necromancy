package com.sirolf2009.necromancy.core.proxy;

import com.sirolf2009.necromancy.Necromancy;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void init() {
        NetworkRegistry.instance().registerGuiHandler(Necromancy.Instance, Necromancy.PacketHandler);
        GameRegistry.registerCraftingHandler(Necromancy.PacketHandler);
    }

    public int addArmour(String path) {
        return 0;
    }

    public void refreshTextures() {
    };
}