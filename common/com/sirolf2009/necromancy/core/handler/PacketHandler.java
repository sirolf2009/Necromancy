package com.sirolf2009.necromancy.core.handler;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
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
import com.sirolf2009.necromancy.generation.villagecomponent.ComponentVillageCemetery;
import com.sirolf2009.necromancy.inventory.ContainerAltar;
import com.sirolf2009.necromancy.inventory.ContainerNecronomiconCrafting;
import com.sirolf2009.necromancy.inventory.ContainerSewing;
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
            player.addStat(Necromancy.SewingAchieve, 1);
            item.stackTagCompound.setString("Skull1", "");
            for(int i=0;i<8;i++)
                System.out.println("slot "+i+": "+craftMatrix.getStackInSlot(i));
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item) {
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
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
    public Object buildComponent(StructureVillagePieceWeight villagePiece, ComponentVillageStartPiece startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
        ComponentVillageCemetery cemetery = ComponentVillageCemetery.func_74919_a(startPiece, pieces, random, p1, p2, p3, p4, p5);
        return cemetery;
    }

    @Override
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        recipeList.add(new MerchantRecipe(new ItemStack(net.minecraft.item.Item.emerald, 6), new ItemStack(net.minecraft.item.Item.book), new ItemStack(Necromancy.necronomicon)));
        recipeList.add(new MerchantRecipe(new ItemStack(net.minecraft.item.Item.emerald, 1), new ItemStack(net.minecraft.item.Item.rottenFlesh, 6), new ItemStack(Necromancy.necromanticItems, 1, random.nextInt(ItemNecromancy.names.length))));
    }
}