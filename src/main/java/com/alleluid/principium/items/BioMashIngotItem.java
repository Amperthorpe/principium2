package com.alleluid.principium.items;

import com.alleluid.principium.setup.ModSetup;
import net.minecraft.item.Item;

public class BioMashIngotItem extends Item {
    public BioMashIngotItem() {
        super(new Item.Properties()
                .maxStackSize(64)
                .group(ModSetup.ITEM_GROUP)
        );
    }
}
