package com.alleluid.tdfmod.setup;

import com.alleluid.tdfmod.TdfMod;
import com.alleluid.tdfmod.items.armor.AbstractPrincipicArmor;
import com.alleluid.tdfmod.items.armor.PrincipicBootsItem;
import com.alleluid.tdfmod.items.armor.PrincipicLeggingsItem;
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
            return new ItemStack(Registration.PRINCIPIC_BLOCK.get());
        }
    };

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) event.getEntityLiving();
            boolean fullPrin = true;
            for (ItemStack item: playerEntity.getArmorInventoryList()){
                if (!(item.getItem() instanceof AbstractPrincipicArmor)) {
                    fullPrin = false;
                }

                if (item.getItem() instanceof PrincipicLeggingsItem && event.getSource().damageType.equals("outOfWorld")) {
                    playerEntity.setPositionAndUpdate(playerEntity.posX, 260, playerEntity.posZ);
                }

                if (item.getItem() instanceof PrincipicBootsItem && event.getSource().damageType.equals("fall")) {
                    event.setCanceled(true);
                }
            }
            if (fullPrin){ event.setCanceled(true); }
        }
    }
}
