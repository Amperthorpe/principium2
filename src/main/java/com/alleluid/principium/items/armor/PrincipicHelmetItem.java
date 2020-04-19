package com.alleluid.principium.items.armor;

import com.alleluid.principium.Util;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static com.alleluid.principium.PrincipiumMod.MODID;

public class PrincipicHelmetItem extends AbstractPrincipicArmor {

    private static Effect night_vision = Effects.NIGHT_VISION;

    public PrincipicHelmetItem() {
        super(EquipmentSlotType.HEAD);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(isEnabled(stack))
            tooltip.add(Util.tooltipStyle("tooltip.principium.principic_helmet.effect_enabled"));
        else
            tooltip.add(Util.tooltipStyle("tooltip.principium.principic_helmet.effect_disabled"));
        tooltip.add(Util.tooltipStyle("tooltip.principium.principic_helmet"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(Util.loreStyle("lore.principium.principic_helmet"));
    }


//    @Override
//    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
//        if(stack.getItem() instanceof PrincipicHelmetItem && isEnabled(stack)) {
//          EffectInstance pnv = player.getActivePotionEffect(night_vision);
//            if (pnv!= null && pnv.getDuration() < 1000000) {
//                player.removePotionEffect(night_vision);
//                EffectInstance nv = new EffectInstance(night_vision);
//                nv.setPotionDurationMax(true);
//                player.addPotionEffect(nv);
//            }
//            else if (pnv==null)
//        }
//    }

    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent evt)
    {
        if (!(evt.getEntityLiving() instanceof PlayerEntity)) {
            return;
        }

        if (evt.getSlot() != EquipmentSlotType.HEAD) {
            return;
        }

        PlayerEntity playerEntity = (PlayerEntity) evt.getEntity();
        ItemStack equipment = evt.getTo();

        if (!(equipment.getItem() instanceof PrincipicHelmetItem) || !((PrincipicHelmetItem) equipment.getItem()).isEnabled(equipment))
        {
            playerEntity.removePotionEffect(night_vision);
        }
        else if((equipment.getItem() instanceof PrincipicHelmetItem) && ((PrincipicHelmetItem) equipment.getItem()).isEnabled(equipment))
        {
            playerEntity.addPotionEffect(new EffectInstance(night_vision, Integer.MAX_VALUE));
        }

    }

}
