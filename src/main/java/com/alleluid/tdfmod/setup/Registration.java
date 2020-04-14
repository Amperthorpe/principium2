package com.alleluid.tdfmod.setup;

import com.alleluid.tdfmod.blocks.DumpsterBlock;
import com.alleluid.tdfmod.blocks.PrincipicBlock;
import com.alleluid.tdfmod.items.BioMashIngotItem;
import com.alleluid.tdfmod.items.trinkets.HearthstoneItem;
import com.alleluid.tdfmod.items.TransportRodItem;
import com.alleluid.tdfmod.items.trinkets.DumpsterRingItem;
import com.alleluid.tdfmod.items.armor.PrincipicBootsItem;
import com.alleluid.tdfmod.items.armor.PrincipicChestplateItem;
import com.alleluid.tdfmod.items.armor.PrincipicHelmetItem;
import com.alleluid.tdfmod.items.armor.PrincipicLeggingsItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.alleluid.tdfmod.TdfMod.MODID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MODID);
//    private static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
//        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Item Registration
    public static final RegistryObject<BioMashIngotItem> BIO_MASH_INGOT = ITEMS.register("bio_mash_ingot", BioMashIngotItem::new);
    public static final RegistryObject<DumpsterRingItem> DUMPSTER_RING = ITEMS.register("dumpster_ring", DumpsterRingItem::new);
    public static final RegistryObject<TransportRodItem> TRANSPORT_ROD = ITEMS.register("transport_rod", TransportRodItem::new);
    public static final RegistryObject<HearthstoneItem> HEARTHSTONE = ITEMS.register("hearthstone", HearthstoneItem::new);

    public static final RegistryObject<PrincipicHelmetItem> PRINCIPIC_HELMET = ITEMS.register("principic_helmet", PrincipicHelmetItem::new);
    public static final RegistryObject<PrincipicChestplateItem> PRINCIPIC_CHESTPLATE = ITEMS.register("principic_chestplate", PrincipicChestplateItem::new);
    public static final RegistryObject<PrincipicLeggingsItem> PRINCIPIC_LEGGINGS = ITEMS.register("principic_leggings", PrincipicLeggingsItem::new);
    public static final RegistryObject<PrincipicBootsItem> PRINCIPIC_BOOTS = ITEMS.register("principic_boots", PrincipicBootsItem::new);

    // Block Registration
    public static final RegistryObject<PrincipicBlock> PRINCIPIC_BLOCK = BLOCKS.register("principic_block", PrincipicBlock::new);
    public static final RegistryObject<Item> PRINCIPIC_BLOCK_ITEM = ITEMS.register("principic_block", () -> new BlockItem(PRINCIPIC_BLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<DumpsterBlock> DUMPSTER_BLOCK = BLOCKS.register("dumpster_block", DumpsterBlock::new);
    public static final RegistryObject<Item> DUMPSTER_BLOCK_ITEM = ITEMS.register("dumpster_block", () -> new BlockItem(DUMPSTER_BLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
}
