package com.sirolf2009.necromancy.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.client.model.ModelIsaacBlood;
import com.sirolf2009.necromancy.client.model.ModelNightCrawler;
import com.sirolf2009.necromancy.client.model.ModelTeddy;
import com.sirolf2009.necromancy.client.renderer.ItemNecronomiconRenderer;
import com.sirolf2009.necromancy.client.renderer.ItemScytheRenderer;
import com.sirolf2009.necromancy.client.renderer.RenderIsaacNormal;
import com.sirolf2009.necromancy.client.renderer.RenderMinion;
import com.sirolf2009.necromancy.client.renderer.RenderNightCrawler;
import com.sirolf2009.necromancy.client.renderer.RenderTeddy;
import com.sirolf2009.necromancy.client.renderer.tileentity.TileEntityAltarRenderer;
import com.sirolf2009.necromancy.client.renderer.tileentity.TileEntitySewingRenderer;
import com.sirolf2009.necromancy.client.renderer.tileentity.TileEntitySkullWallRenderer;
import com.sirolf2009.necromancy.entity.EntityIsaacNormal;
import com.sirolf2009.necromancy.entity.EntityMinion;
import com.sirolf2009.necromancy.entity.EntityNecroFX;
import com.sirolf2009.necromancy.entity.EntityNightCrawler;
import com.sirolf2009.necromancy.entity.EntityTeddy;
import com.sirolf2009.necromancy.tileentity.TileEntityAltar;
import com.sirolf2009.necromancy.tileentity.TileEntitySewing;
import com.sirolf2009.necromancy.tileentity.TileEntitySkullWall;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
    static public Minecraft mc;

    @Override
    @SideOnly(Side.CLIENT)
    public void init() {
        super.init();
        mc = FMLClientHandler.instance().getClient();
        RenderingRegistry.registerEntityRenderingHandler(EntityMinion.class, new RenderMinion());
        RenderingRegistry.registerEntityRenderingHandler(EntityTeddy.class, new RenderTeddy(new ModelTeddy(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(EntityIsaacNormal.class, new RenderIsaacNormal(new ModelIsaacBlood(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(EntityNightCrawler.class, new RenderNightCrawler(new ModelNightCrawler(), 0.3F));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new TileEntityAltarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySewing.class, new TileEntitySewingRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkullWall.class, new TileEntitySkullWallRenderer());
        MinecraftForgeClient.registerItemRenderer(Necromancy.AltarID, new TileEntityAltarRenderer());
        MinecraftForgeClient.registerItemRenderer(Necromancy.SewingID, new TileEntitySewingRenderer());
        MinecraftForgeClient.registerItemRenderer(Necromancy.SkullWallID, new TileEntitySkullWallRenderer());
        MinecraftForgeClient.registerItemRenderer(Necromancy.scythe.itemID, new ItemScytheRenderer());
        MinecraftForgeClient.registerItemRenderer(Necromancy.necronomicon.itemID, new ItemNecronomiconRenderer());
    }

    public static void spawnParticle(String name, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null) {
            if (name.equals("skull")) {
                mc.effectRenderer.addEffect(new EntityNecroFX(mc.theWorld, posX, posY, posZ, motionX, motionY, motionZ, 0.5F));
            }
        }
    }

    public void log(Object msg) {
        System.out.println(this.getClass() + "	" + msg);
    }
}