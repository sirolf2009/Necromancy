package com.sirolf2009.necromancy.tileentity;

import java.util.Iterator;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.sirolf2009.necroapi.BodyPart;
import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necroapi.NecroEntityRegistry;
import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.client.model.ModelMinion;
import com.sirolf2009.necromancy.entity.EntityMinion;
import com.sirolf2009.necromancy.item.ItemNecromancy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;

public class TileEntityAltar extends TileEntity implements IInventory {

    public TileEntityAltar() {
        setMinion(new EntityMinion(worldObj));
        bodyPartsNew = new ItemStack[5];
        bodyPartsOld = new ItemStack[5];
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return true;
    }

    public void spawn(EntityPlayer user) {
        lastUser = user;
        if (!worldObj.isRemote) {
            BodyPart[][] types = new BodyPart[5][];
            ItemStack head = getStackInSlot(2);
            ItemStack body = getStackInSlot(3);
            ItemStack leg = getStackInSlot(4);
            ItemStack armRight = getStackInSlot(5);
            ItemStack armLeft = getStackInSlot(6);
            if (head != null && head.getItem() != null) {
                types[0] = getBodyPart(head, false);
            } else {
                types[0] = new BodyPart[] {};
            }
            if (body != null) {
                types[1] = getBodyPart(body, false);
            } else {
                types[1] = new BodyPart[] {};
            }
            if (leg != null) {
                types[4] = getBodyPart(leg, false);
            } else {
                types[4] = new BodyPart[] {};
            }
            if (armLeft != null) {
                types[2] = getBodyPart(armLeft, false);
            } else {
                types[2] = new BodyPart[] {};
            }
            if (armRight != null) {
                types[3] = getBodyPart(armRight, true);
            } else {
                types[3] = new BodyPart[] {};
            }
            EntityMinion minionTemp = new EntityMinion(worldObj, types, user.username);
            minionTemp.setPosition(xCoord, yCoord + 1, zCoord);
            worldObj.spawnEntityInWorld(minionTemp);
            user.addStat(Necromancy.SpawnAchieve, 1);
            if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage("<Minion> Craft me Thy dark command.");
            }
            minionTemp.dataWatcherUpdate();
            minionTemp.getModel().updateModel(minionTemp, true);
            if (!user.capabilities.isCreativeMode) {
                for (int x = 0; x < 7; x++) {
                    decrStackSize(x, 1);
                }
            }
            bodyPartsOld = null;
            user.addStat(Necromancy.SpawnAchieve, 1);
            log(minionTemp);
        }
    }

    public boolean canSpawn() {
        if (getStackInSlot(0) == null || getStackInSlot(0).getItem() != ItemNecromancy.getItemStackFromName("Jar of Blood").getItem())
            return false;
        if (getStackInSlot(1) == null || !soulCheck())
            return false;
        return true;
    }

    public BodyPart[] getBodyPart(ItemStack stack, boolean isRightArm) {
        Iterator<NecroEntityBase> itr = NecroEntityRegistry.registeredEntities.values().iterator();
        while (itr.hasNext()) {
            NecroEntityBase mob = itr.next();
            if (mob.headItem != null && stack.isItemEqual(mob.headItem))
                return mob.head == null ? mob.updateParts(ModelMinion.instance).head : mob.head;
            if (mob.torsoItem != null && stack.isItemEqual(mob.torsoItem))
                return mob.torso == null ? mob.updateParts(ModelMinion.instance).torso : mob.torso;
            if (mob.armItem != null && stack.isItemEqual(mob.armItem))
                if (isRightArm)
                    return mob.armRight == null ? mob.updateParts(ModelMinion.instance).armRight : mob.armRight;
                else
                    return mob.armLeft == null ? mob.updateParts(ModelMinion.instance).armLeft : mob.armLeft;
            if (mob.legItem != null && stack.isItemEqual(mob.legItem))
                return mob.legs == null ? mob.updateParts(ModelMinion.instance).legs : mob.legs;
        }
        return null;
    }

    public boolean hasContainerChanged() {
        bodyPartsNew[0] = getStackInSlot(2);
        bodyPartsNew[1] = getStackInSlot(3);
        bodyPartsNew[2] = getStackInSlot(4);
        bodyPartsNew[3] = getStackInSlot(5);
        bodyPartsNew[4] = getStackInSlot(6);

        for (int i = 0; i < 5; i++)
            if (bodyPartsNew[i] != null && bodyPartsOld[i] != null) {
                if (!bodyPartsNew[i].isItemEqual(bodyPartsOld[i])) {
                    bodyPartsOld = bodyPartsNew.clone();
                    return true;
                }
            } else if (bodyPartsNew[i] != null || bodyPartsOld[i] != null && !(bodyPartsNew[i] == null && bodyPartsOld[i] == null)) {
                bodyPartsOld = bodyPartsNew.clone();
                return true;
            }

        bodyPartsOld = bodyPartsNew.clone();
        return false;
    }

    public Entity getPreviewEntity() {
        ItemStack head = getStackInSlot(2);
        ItemStack body = getStackInSlot(3);
        ItemStack leg = getStackInSlot(4);
        ItemStack armRight = getStackInSlot(5);
        ItemStack armLeft = getStackInSlot(6);
        if (head != null && head.getItem() != null && isLegalCombo("head", head)) {
            getMinion().setBodyPart("head", getBodyPart(head, false));
        } else {
            getMinion().setBodyPart("head", new BodyPart[] {});
        }
        if (body != null && isLegalCombo("body", body)) {
            getMinion().setBodyPart("torso", getBodyPart(body, false));
        } else {
            getMinion().setBodyPart("torso", new BodyPart[] {});
        }
        if (leg != null && isLegalCombo("leg", leg)) {
            getMinion().setBodyPart("leg", getBodyPart(leg, false));
        } else {
            getMinion().setBodyPart("leg", new BodyPart[] {});
        }
        if (armLeft != null && isLegalCombo("arm", armLeft)) {
            getMinion().setBodyPart("armLeft", getBodyPart(armLeft, false));
        } else {
            getMinion().setBodyPart("armLeft", new BodyPart[] {});
        }
        if (armRight != null && isLegalCombo("arm", armRight)) {
            getMinion().setBodyPart("armRight", getBodyPart(armRight, true));
        } else {
            getMinion().setBodyPart("armRight", new BodyPart[] {});
        }
        getMinion().setAltarMob(true);
        getMinion().setAltar(this);
        return getMinion();
    }

    public boolean isLegalCombo(String string, ItemStack stack) {
        Iterator<NecroEntityBase> itr = NecroEntityRegistry.registeredEntities.values().iterator();
        while (itr.hasNext() && stack != null) {
            NecroEntityBase mob = itr.next();
            if (string.equals("head") && mob.hasHead && ItemStack.areItemStacksEqual(stack, mob.headItem))
                return true;
            if (string.equals("body") && mob.hasTorso && ItemStack.areItemStacksEqual(stack, mob.torsoItem))
                return true;
            if (string.equals("arm") && mob.hasArms && ItemStack.areItemStacksEqual(stack, mob.armItem))
                return true;
            if (string.equals("leg") && mob.hasLegs && ItemStack.areItemStacksEqual(stack, mob.legItem))
                return true;
        }
        log(stack.getDisplayName() + " is not a " + string + " item");
        return false;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        altarItemStacks = new ItemStack[getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); var3++) {
            NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < altarItemStacks.length) {
                altarItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < altarItemStacks.length; var3++)
            if (altarItemStacks[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                altarItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }

        par1NBTTagCompound.setTag("Items", var2);
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if (altarItemStacks[var1] != null) {
            ItemStack var3;
            if (altarItemStacks[var1].stackSize <= var2) {
                var3 = altarItemStacks[var1];
                altarItemStacks[var1] = null;
                return var3;
            }
            var3 = altarItemStacks[var1].splitStack(var2);
            if (altarItemStacks[var1].stackSize == 0) {
                altarItemStacks[var1] = null;
            }
            return var3;
        } else
            return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        if (altarItemStacks[var1] != null) {
            ItemStack var2 = altarItemStacks[var1];
            altarItemStacks[var1] = null;
            return var2;
        } else
            return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        altarItemStacks[var1] = var2;
        if (var2 != null && var2.stackSize > getInventoryStackLimit()) {
            var2.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {
        return "Altar";
    }

    @Override
    public int getSizeInventory() {
        return altarItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return altarItemStacks[var1];
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    private void log(Object msg) {
        FMLLog.getLogger().log(Level.INFO, TileEntityAltar.class + "	" + msg);
    }

    public EntityMinion getMinion() {
        return minion;
    }

    public void setMinion(EntityMinion minion) {
        this.minion = minion;
    }

    public boolean soulCheck() {
        if (getStackInSlot(1).getItem() == ItemNecromancy.getItemStackFromName("Soul in a Jar").getItem() || "item.HSBottledSoul".equals(getStackInSlot(1).getItemName()))
            return true;
        return false;
    }

    private ItemStack altarItemStacks[] = new ItemStack[7];
    private EntityMinion minion;
    private ItemStack bodyPartsNew[];
    private ItemStack bodyPartsOld[];
    public EntityPlayer lastUser;

    public int getStartInventorySide(ForgeDirection side) {
        if (side == ForgeDirection.DOWN)
            return 1;
        return 0;
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
        return true;
    }
}
