package com.sirolf2009.necromancy.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.Necromancy;

import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityVillagerNecro extends EntityVillager implements INpc, IMerchant {
    private boolean isMating;
    private boolean isPlaying;
    Village villageObj;

    /** This villager's current customer. */
    private EntityPlayer buyingPlayer;

    /** Initialises the MerchantRecipeList.java */
    private MerchantRecipeList buyingList;
    private int wealth;
    private String field_82189_bL;
    private float field_82191_bN;

    /**
     * a villagers recipe list is intialized off this list ; the 2 params are
     * min/max amount they will trade for 1 emerald
     */
    public static final Map villagerStockList = new HashMap();

    /**
     * Selling list of Blacksmith items. negative numbers mean 1 emerald for n
     * items, positive numbers are n emeralds for 1 item
     */
    public static final Map blacksmithSellingList = new HashMap();

    public EntityVillagerNecro(World par1World) {
        this(par1World, 0);
    }

    public EntityVillagerNecro(World par1World, int par2) {
        super(par1World);
        this.setProfession(666);
        texture = "/necromancy/villagerNecro.png";
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(20, Integer.valueOf(0));
    }

    @Override
    public int getMaxHealth() {
        return 20;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Profession", this.getProfession());
        par1NBTTagCompound.setInteger("Riches", wealth);

        if (buyingList != null) {
            par1NBTTagCompound.setCompoundTag("Offers", buyingList.getRecipiesAsTags());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setProfession(par1NBTTagCompound.getInteger("Profession"));
        wealth = par1NBTTagCompound.getInteger("Riches");

        if (par1NBTTagCompound.hasKey("Offers")) {
            NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("Offers");
            buyingList = new MerchantRecipeList(var2);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture() {
        return "/necromancy/villagerNecro.png";
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    @Override
    protected boolean canDespawn() {
        return false;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return "mob.villager.default";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return "mob.villager.defaulthurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
        return "mob.villager.defaultdeath";
    }

    @Override
    public void setProfession(int par1) {
        dataWatcher.updateObject(20, Integer.valueOf(par1));
    }

    @Override
    public int getProfession() {
        return dataWatcher.getWatchableObjectInt(20);
    }

    @Override
    public boolean isMating() {
        return isMating;
    }

    @Override
    public void setMating(boolean par1) {
        isMating = par1;
    }

    @Override
    public void setPlaying(boolean par1) {
        isPlaying = par1;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource par1DamageSource) {
        super.onDeath(par1DamageSource);
        EntityZombie zombie = new EntityZombie(worldObj);
        zombie.setPosition(posX, posY, posZ);
        if (!worldObj.isRemote) {
            worldObj.spawnEntityInWorld(zombie);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte par1) {
        if (par1 == 12) {
            this.generateRandomParticles("heart");
        } else if (par1 == 13) {
            this.generateRandomParticles("angryVillager");
        } else if (par1 == 14) {
            this.generateRandomParticles("happyVillager");
        } else {
            super.handleHealthUpdate(par1);
        }
    }

    @SideOnly(Side.CLIENT)
    /**
     * par1 is the particleName
     */
    private void generateRandomParticles(String par1Str) {
        for (int var2 = 0; var2 < 5; ++var2) {
            double var3 = rand.nextGaussian() * 0.02D;
            double var5 = rand.nextGaussian() * 0.02D;
            double var7 = rand.nextGaussian() * 0.02D;
            worldObj.spawnParticle(par1Str, posX + rand.nextFloat() * width * 2.0F - width, posY + 1.0D + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0F - width, var3, var5, var7);
        }
    }

    @Override
    public MerchantRecipeList getRecipes(EntityPlayer par1EntityPlayer) {
        if (buyingList == null) {
            addDefaultEquipmentAndRecipies(1);
        }
        return buyingList;
    }

    @Override
    public void useRecipe(MerchantRecipe par1MerchantRecipe) {
        par1MerchantRecipe.incrementToolUses();

        if (par1MerchantRecipe.hasSameIDsAs((MerchantRecipe) buyingList.get(buyingList.size() - 1))) {
            if (buyingPlayer != null) {
                field_82189_bL = buyingPlayer.getCommandSenderName();
            } else {
                field_82189_bL = null;
            }
        }

        if (par1MerchantRecipe.getItemToBuy().itemID == Item.emerald.itemID) {
            wealth += par1MerchantRecipe.getItemToBuy().stackSize;
        }
    }

    private void addDefaultEquipmentAndRecipies(int par1) {
        if (buyingList != null) {
            field_82191_bN = MathHelper.sqrt_float(buyingList.size()) * 0.2F;
        } else {
            field_82191_bN = 0.0F;
        }

        MerchantRecipeList var2;
        var2 = new MerchantRecipeList();
        VillagerRegistry.manageVillagerTrades(var2, this, this.getProfession(), rand);

        addBlacksmithItem(var2, new ItemStack(Necromancy.necromanticItems, 1, 5).itemID, rand, 1);
        addBlacksmithItem(var2, new ItemStack(Necromancy.necromanticItems, 1, 6).getItemDamage(), rand, 1);
        addBlacksmithItem(var2, Necromancy.necronomicon.itemID, rand, 1);

        if (var2.isEmpty()) {
            addMerchantItem(var2, Item.ingotGold.itemID, rand, 1.0F);
        }

        Collections.shuffle(var2);

        if (buyingList == null) {
            buyingList = new MerchantRecipeList();
        }

        for (int var8 = 0; var8 < par1 && var8 < var2.size(); ++var8) {
            buyingList.addToListWithCheck((MerchantRecipe) var2.get(var8));
        }
    }
}
