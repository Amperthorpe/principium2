package com.alleluid.tdfmod.items;

import com.alleluid.tdfmod.setup.ModSetup;
import net.minecraft.item.Item;

public class BioMashIngotItem extends Item {
    public BioMashIngotItem() {
        super(new Item.Properties()
                .maxStackSize(64)
                .group(ModSetup.ITEM_GROUP)
        );
    }
}
