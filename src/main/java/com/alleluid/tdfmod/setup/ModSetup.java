package com.alleluid.tdfmod.setup;

import com.alleluid.tdfmod.TdfMod;
import com.alleluid.tdfmod.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TdfMod.MODID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup(TdfMod.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.PRINCIPIC_BLOCK);
        }
    };
}
