package com.sirolf2009.necromancy.core.handler;

import java.util.Random;

import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import com.sirolf2009.necromancy.block.BlockNecromancy;
import com.sirolf2009.necromancy.client.model.ModelMinion;
import com.sirolf2009.necromancy.command.CommandMinion;
import com.sirolf2009.necromancy.command.CommandRemodel;
import com.sirolf2009.necromancy.item.ItemNecromancy;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventHandler {

    @ForgeSubscribe
    public void LivingDropsEvent(LivingDeathEvent evt) {
        if (evt.entity instanceof EntityLiving && !evt.entity.worldObj.isRemote && rand.nextInt(100) <= 8) {
            switch (rand.nextInt(7)) {
                case 0:
                    evt.entity.entityDropItem(new ItemStack(ItemNecromancy.organs, 1, 0), 1);
                    break; // brains
                case 1:
                    evt.entity.entityDropItem(new ItemStack(ItemNecromancy.organs, 1, 1), 1);
                    break; // heart
                case 2:
                    evt.entity.entityDropItem(new ItemStack(ItemNecromancy.organs, 1, 2), 1);
                    break; // muscle
                case 3:
                    evt.entity.entityDropItem(new ItemStack(ItemNecromancy.organs, 1, 2), 1);
                    break; // muscle
                case 4:
                    evt.entity.entityDropItem(new ItemStack(ItemNecromancy.organs, 1, 2), 1);
                    break; // muscle
                case 5:
                    evt.entity.entityDropItem(new ItemStack(ItemNecromancy.organs, 1, 2), 1);
                    break; // muscle
                case 6:
                    evt.entity.entityDropItem(new ItemStack(ItemNecromancy.organs, 1, 3), 1);
                    break; // lungs
            }
        }
    }

    public void initCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandRemodel());
        event.registerServerCommand(new CommandMinion());
    }

    @ForgeSubscribe
    public void CommandEvent(net.minecraftforge.event.CommandEvent evt) {
        if (evt.command instanceof CommandRemodel) {
            ModelMinion.remodelCommand = true;
        }
    }

    @ForgeSubscribe
    public void onBucketFill(FillBucketEvent event) {

        ItemStack result = fillCustomBucket(event.world, event.target);

        if (result == null)
            return;

        event.result = result;
        event.setResult(Result.ALLOW);
    }

    public ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {

        int blockID = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);

        if ((blockID == BlockNecromancy.fluidBlood.getBlockID() || blockID == BlockNecromancy.blood.blockID) && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {

            world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0, 0, 0);

            return new ItemStack(ItemNecromancy.bucketBlood);
        } else
            return null;
    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void onSoundsLoaded(SoundLoadEvent event) {
        SoundManager manager = event.manager;
        manager.soundPoolSounds.addSound("necromancy:sounds/nightcrawler/scream.ogg");
        manager.soundPoolSounds.addSound("necromancy:sounds/nightcrawler/howl.ogg");
    }

    Random rand = new Random();
}
