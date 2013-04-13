package com.sirolf2009.necromancy.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBucket;

public class ItemBucketBlood extends ItemBucket {

    public ItemBucketBlood(int par1, int par2) {
        super(par1, par2);
    }

    @Override
    public void updateIcons(IconRegister iconRegister) {
        iconIndex = iconRegister.registerIcon("necromancy:bucketBlood");
    }

}
