package com.alleluid.tdfmod.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {

    @ObjectHolder("tdfmod:bio_mash_ingot")
    public static BioMashIngotItem BIO_MASH_INGOT;

    @ObjectHolder("tdfmod:dumpster_ring")
    public static DumpsterRingItem DUMPSTER_RING;


//    public static final Item CHAINMAIL_HELMET = register("chainmail_helmet", new ArmorItem(ArmorMaterial.CHAIN, EquipmentSlotType.HEAD, (new Item.Properties()).group(ItemGroup.COMBAT)));
//    public static final Item CHAINMAIL_CHESTPLATE = register("chainmail_chestplate", new ArmorItem(ArmorMaterial.CHAIN, EquipmentSlotType.CHEST, (new Item.Properties()).group(ItemGroup.COMBAT)));
//    public static final Item CHAINMAIL_LEGGINGS = register("chainmail_leggings", new ArmorItem(ArmorMaterial.CHAIN, EquipmentSlotType.LEGS, (new Item.Properties()).group(ItemGroup.COMBAT)));
//    public static final Item CHAINMAIL_BOOTS = register("chainmail_boots", new ArmorItem(ArmorMaterial.CHAIN, EquipmentSlotType.FEET, (new Item.Properties()).group(ItemGroup.COMBAT)));

}
