package com.sirolf2009.necromancy.core.handler;

import java.util.List;
import java.util.Random;

import org.bouncycastle.util.encoders.Hex;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureVillagePieceWeight;

import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.block.BlockAltar;
import com.sirolf2009.necromancy.block.BlockSewing;
import com.sirolf2009.necromancy.client.gui.GuiAltar;
import com.sirolf2009.necromancy.client.gui.GuiSewing;
import com.sirolf2009.necromancy.core.proxy.ClientProxy;
import com.sirolf2009.necromancy.generation.villagecomponent.ComponentVillageCemetery;
import com.sirolf2009.necromancy.inventory.ContainerAltar;
import com.sirolf2009.necromancy.inventory.ContainerNecronomiconCrafting;
import com.sirolf2009.necromancy.inventory.ContainerSewing;
import com.sirolf2009.necromancy.item.ItemBodyPart;
import com.sirolf2009.necromancy.item.ItemNecroSkull;
import com.sirolf2009.necromancy.item.ItemNecromancy;
import com.sirolf2009.necromancy.tileentity.TileEntityAltar;
import com.sirolf2009.necromancy.tileentity.TileEntitySewing;

import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class PacketHandler implements IPacketHandler, IGuiHandler, ICraftingHandler, IVillageCreationHandler, IVillageTradeHandler {

    @Override
    public Object getServerGuiElement(int ID, net.minecraft.entity.player.EntityPlayer player, net.minecraft.world.World world, int x, int y, int z) {
        if (ID == BlockAltar.guiID)
            return new ContainerAltar(player.inventory, (TileEntityAltar) player.worldObj.getBlockTileEntity(x, y, z));
        if (ID == BlockSewing.guiID)
            return new ContainerSewing(player.inventory, player.worldObj.getBlockTileEntity(x, y, z));
        if (ID == 2)
            return new ContainerNecronomiconCrafting(player.inventory, world, x, y, z);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == BlockAltar.guiID)
            return new GuiAltar(player.inventory, (TileEntityAltar) player.worldObj.getBlockTileEntity(x, y, z));
        if (ID == BlockSewing.guiID)
            return new GuiSewing(player.inventory, (TileEntitySewing) player.worldObj.getBlockTileEntity(x, y, z));
        return null;
    }

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
        if (item != null && item.getItemName().equals("item.Necronomicon")) {
            player.addStat(Necromancy.NecronomiconAchieve, 1);
        }
        if (item != null && item.getItemName().equals("tile.Sewing Machine")) {
            player.addStat(Necromancy.SewingAchieve, 1);
        }
        if (item != null && item.itemID == new ItemStack(Necromancy.bucketBlood).itemID) {
            player.inventory.addItemStackToInventory(new ItemStack(net.minecraft.item.Item.glassBottle, 8));
        }
        if (item != null && item.itemID == ItemNecromancy.getItemStackFromName("Jar of Blood").itemID && item.getItemDamage() == ItemNecromancy.getItemStackFromName("Jar of Blood").getItemDamage()) {
            player.inventory.addItemStackToInventory(new ItemStack(net.minecraft.item.Item.bucketEmpty));
        }
        if (item != null && item.getItemName().equals("tile.skullWall")) {
            Necromancy.logger.info(craftMatrix.getStackInSlot(0) + " is in " + craftMatrix.getStackInSlot(0).getItemName());
            item.stackTagCompound.setString("Base", craftMatrix.getStackInSlot(1).getItemName());
            item.stackTagCompound.setString("Skull1", ItemNecroSkull.skullTypes[craftMatrix.getStackInSlot(1).getItemDamage()]);
            item.stackTagCompound.setString("Skull2", ItemNecroSkull.skullTypes[craftMatrix.getStackInSlot(4).getItemDamage()]);
            item.stackTagCompound.setString("Skull3", ItemNecroSkull.skullTypes[craftMatrix.getStackInSlot(5).getItemDamage()]);

        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item) {
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if(packet.data[0] == 0) {
            ClientProxy.mc.thePlayer.getEntityData().setBoolean("aggressive", packet.data[1] == 1);
        } else if(packet.data[0] == 1) { //we're making friends :D
            EntityPlayer playerEntity = (EntityPlayer) ClientProxy.mc.theWorld.getEntityByID(packet.data[1] & 0xFF);
            playerEntity.getEntityData().setString(playerEntity.username, "friend");
        } else if(packet.data[0] == 2) { //who needs friends anyway
            EntityPlayer playerEntity = (EntityPlayer) ClientProxy.mc.theWorld.getEntityByID(packet.data[1] & 0xFF);
            System.out.println("player found by packet data "+playerEntity);
            playerEntity.getEntityData().setString(playerEntity.username, "enemy");
        }
    }

    @Override
    public StructureVillagePieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieceWeight(ComponentVillageCemetery.class, 5, 1);
    }

    @Override
    public Class<?> getComponentClass() {
        return ComponentVillageCemetery.class;
    }

    @Override
    public Object buildComponent(StructureVillagePieceWeight villagePiece, ComponentVillageStartPiece startPiece, @SuppressWarnings("rawtypes") List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
        ComponentVillageCemetery cemetery = ComponentVillageCemetery.func_74919_a(startPiece, pieces, random, p1, p2, p3, p4, p5);
        return cemetery;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 6), new ItemStack(Item.book), new ItemStack(Necromancy.necronomicon)));
        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, new Random().nextInt(3)), null, new ItemStack(Necromancy.bodyparts, 1, random.nextInt(ItemBodyPart.necroEntities.size()-1))));
        recipeList.add(new MerchantRecipe(new ItemStack(Necromancy.bodyparts, 1, random.nextInt(ItemBodyPart.necroEntities.size()-1)), null, new ItemStack(Item.emerald, new Random().nextInt(3))));
        recipeList.add(new MerchantRecipe(new ItemStack(Necromancy.bodyparts, 1, random.nextInt(ItemBodyPart.necroEntities.size()-1)), null, new ItemStack(Item.emerald, new Random().nextInt(3))));
    }
}