package com.alleluid.tdfmod.items;

import com.alleluid.tdfmod.setup.ModSetup;
import net.minecraft.item.Item;

public class TransportRodItem extends Item {
    public TransportRodItem() {
        super(new Properties()
                .maxStackSize(1)
                .group(ModSetup.ITEM_GROUP)
        );
    }


}
