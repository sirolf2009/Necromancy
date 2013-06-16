package com.sirolf2009.necromancy.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommandMinion extends CommandBase {

    @Override
    public String getCommandName() {
        return "minion";
    }

    @Override
    public void processCommand(ICommandSender var1, String[] var2) {
        if (var2.length >= 2) {
            EntityPlayerMP player = func_82359_c(var1, var1.getCommandSenderName());
            NBTTagCompound nbt = player.getEntityData();
            Packet250CustomPayload packet = null;
            if (var2[0].equals("set")) {
                if (var2[1].equals("aggressive")) {
                    nbt.setBoolean("aggressive", true);
                    packet = new Packet250CustomPayload("NecromancyMod", new byte[] { 0, 1 });
                    var1.sendChatToPlayer("Minions are set to aggressive");
                } else if (var2[1].equals("passive")) {
                    nbt.setBoolean("aggressive", false);
                    packet = new Packet250CustomPayload("NecromancyMod", new byte[] { 0, 0 });
                    var1.sendChatToPlayer("Minions are set to passive");
                } else
                    throw new WrongUsageException("minion", new Object[0]);
            } else if (var2[0].equals("friend")) {
                nbt.setString(var2[1], "friend");
                packet = new Packet250CustomPayload("NecromancyMod", new byte[] { 1, (byte) func_82359_c(var1, var2[1]).entityId });
                var1.sendChatToPlayer(var2[1] + " is now a friend");
            } else if (var2[0].equals("enemy")) {
                nbt.setString(var2[1], "enemy");
                packet = new Packet250CustomPayload("NecromancyMod", new byte[] { 2, (byte) func_82359_c(var1, var2[1]).entityId });
                var1.sendChatToPlayer(var2[1] + " is now an enemy");
            } else
                throw new WrongUsageException("minion", new Object[0]);
            PacketDispatcher.sendPacketToAllPlayers(packet);
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        List<String> result = new ArrayList<String>();
        if (par2ArrayOfStr.length == 1) {
            result.add("set");
            result.add("friend");
            result.add("enemy");
        } else if (par2ArrayOfStr.length == 2) {
            if (par2ArrayOfStr[0].equals("set")) {
                result.add("aggressive");
                result.add("passive");
            } else if (par2ArrayOfStr[0].equals("friend") || par2ArrayOfStr[0].equals("enemy")) {
                for (int i = 0; i < getPlayers().length; i++) {
                    result.add(getPlayers()[i]);
                }
            }
        }
        return result;
    }

    protected String[] getPlayers() {
        return MinecraftServer.getServer().getAllUsernames();
    }

}
