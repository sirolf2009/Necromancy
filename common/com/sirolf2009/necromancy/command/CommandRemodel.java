package com.sirolf2009.necromancy.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import com.sirolf2009.necromancy.client.model.ModelMinion;

public class CommandRemodel extends CommandBase {

    @Override
    public String getCommandName() {
        return "remodel";
    }

    @Override
    public void processCommand(ICommandSender var1, String[] var2) {
        ModelMinion.remodelCommand = true;
    }

}
