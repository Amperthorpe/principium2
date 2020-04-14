package com.alleluid.tdfmod;

import com.alleluid.tdfmod.client.render.ChestElytraRenderLayer;
import com.alleluid.tdfmod.items.armor.PrincipicChestplateItem;
import com.alleluid.tdfmod.items.armor.PrincipicLeggingsItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import top.theillusivec4.caelus.api.event.RenderElytraEvent;

public class EventHandlerCommon {

    @SubscribeEvent
    public void onLivingEquipmentChange(LivingEquipmentChangeEvent evt)
    {
        PrincipicChestplateItem.onLivingEquipmentChange(evt);
        PrincipicLeggingsItem.onLivingEquipmentChange(evt);
    }

    @SubscribeEvent void onRenderElytraEvent(RenderElytraEvent evt)
    {
        PrincipicChestplateItem.onRenderElytraEvent(evt);
    }

}
