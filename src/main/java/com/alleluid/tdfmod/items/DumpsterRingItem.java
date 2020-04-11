package com.alleluid.tdfmod.items;

import com.alleluid.tdfmod.setup.ModSetup;
import net.minecraft.item.Item;

public class DumpsterRingItem extends Item {
    public DumpsterRingItem() {
        super(new Properties()
                .maxStackSize(1)
                .group(ModSetup.ITEM_GROUP)
        );
        setRegistryName("dumpster_ring");
    }



}
