package com.alleluid.tdfmod.items;

import net.minecraft.item.Item;

public class BioMashIngotItem extends Item {
    public BioMashIngotItem() {
        super(new Item.Properties()
                .maxStackSize(64)

        );
        setRegistryName("bio_mash_ingot");
    }
}
