package com.alleluid.tdfmod;

import com.alleluid.tdfmod.items.armor.PrincipicChestplateItem;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.caelus.api.event.RenderElytraEvent;

public class EventHandlerCommon {

    @SubscribeEvent
    public void onLivingEquipmentChange(LivingEquipmentChangeEvent evt)
    {
        PrincipicChestplateItem.onLivingEquipmentChange(evt);
    }

    @SubscribeEvent void onRenderElytraEvent(RenderElytraEvent evt)
    {
        PrincipicChestplateItem.onRenderElytraEvent(evt);
    }

}
