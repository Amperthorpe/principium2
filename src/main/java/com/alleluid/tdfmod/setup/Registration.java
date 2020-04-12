package com.alleluid.tdfmod.setup;

import com.alleluid.tdfmod.blocks.PrincipicBlock;
import com.alleluid.tdfmod.items.BioMashIngotItem;
import com.alleluid.tdfmod.items.DumpsterRingItem;
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

    // Block Registration
    public static final RegistryObject<PrincipicBlock> PRINCIPIC_BLOCK = BLOCKS.register("principic_block", PrincipicBlock::new);
    public static final RegistryObject<Item> PRINCIPIC_BLOCK_ITEM = ITEMS.register("principic_block", () -> new BlockItem(PRINCIPIC_BLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));


}
