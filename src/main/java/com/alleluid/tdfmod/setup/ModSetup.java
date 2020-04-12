package com.alleluid.tdfmod.setup;

import com.alleluid.tdfmod.TdfMod;
import com.alleluid.tdfmod.blocks.ModBlocks;
import com.alleluid.tdfmod.items.armor.AbstractPrincipicArmor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TdfMod.MODID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup(TdfMod.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.PRINCIPIC_BLOCK);
        }
    };

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) event.getEntityLiving();
            event.setCanceled(true);
            for (ItemStack item: playerEntity.getArmorInventoryList()){
                if (!(item.getItem() instanceof AbstractPrincipicArmor)) {
                    event.setCanceled(false);
                }
            }
        }
    }
}
