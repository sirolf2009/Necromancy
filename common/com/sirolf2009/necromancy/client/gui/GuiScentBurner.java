package com.sirolf2009.necromancy.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sirolf2009.necromancy.client.renderer.tileentity.TileEntityScentBurnerRenderer;
import com.sirolf2009.necromancy.core.proxy.ClientProxy;
import com.sirolf2009.necromancy.inventory.ContainerScentBurner;
import com.sirolf2009.necromancy.inventory.ContainerSewing;
import com.sirolf2009.necromancy.lib.ReferenceNecromancy;
import com.sirolf2009.necromancy.tileentity.TileEntityScentBurner;
import com.sirolf2009.necromancy.tileentity.TileEntitySewing;

public class GuiScentBurner extends GuiContainer {

    public GuiScentBurner(InventoryPlayer par1InventoryPlayer, TileEntityScentBurner par2TileEntitySewing) {
        super(new ContainerScentBurner(par1InventoryPlayer, par2TileEntitySewing));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("String", 88, 72, 1);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        ClientProxy.bindTexture(ReferenceNecromancy.TEXTURES_GUI_SCENTBURNER);
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
    }
}
