package com.sirolf2009.necromancy.core.handler;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import com.sirolf2009.necromancy.core.proxy.ClientProxy;
import com.sirolf2009.necromancy.entity.EntityTear;
import com.sirolf2009.necromancy.entity.EntityTearBlood;
import com.sirolf2009.necromancy.item.ItemNecromancy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyHandlerNecro extends KeyHandler {

    public static KeyBinding tearNormal = new KeyBinding("Shoot Normal Tear", Keyboard.KEY_F);
    public static KeyBinding tearBlood = new KeyBinding("Shoot Blood Tear", Keyboard.KEY_G);
    public long lastShotNormal = 0;
    public long lastShotBlood = 0;

    public KeyHandlerNecro() {
        super(new KeyBinding[] { tearNormal, tearBlood }, new boolean[] { false, false });
    }

    @Override
    public String getLabel() {
        return "Necro Key Bindings";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
        if (tickEnd) {
            if (FMLClientHandler.instance().getClient().currentScreen == null && ClientProxy.mc.thePlayer.inventory.armorInventory[3] != null && ClientProxy.mc.thePlayer.inventory.armorInventory[3].getItem() == ItemNecromancy.isaacsHead) {
                if (kb.keyCode == tearNormal.keyCode && lastShotNormal + 1200 < System.currentTimeMillis()) {
                    EntityTear tearNormal = new EntityTear(ClientProxy.mc.thePlayer.worldObj, ClientProxy.mc.thePlayer, 2);
                    ClientProxy.mc.thePlayer.worldObj.spawnEntityInWorld(tearNormal);
                    lastShotNormal = System.currentTimeMillis();
                }
                if (kb.keyCode == tearBlood.keyCode && lastShotBlood + 1900 < System.currentTimeMillis()) {
                    EntityTearBlood tearBlood = new EntityTearBlood(ClientProxy.mc.thePlayer.worldObj, ClientProxy.mc.thePlayer, 2);
                    ClientProxy.mc.thePlayer.worldObj.spawnEntityInWorld(tearBlood);
                    lastShotBlood = System.currentTimeMillis();
                }
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

}
