package com.alleluid.principium.items.armor;

import com.alleluid.principium.Util;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PrincipicBootsItem extends AbstractPrincipicArmor {

    public PrincipicBootsItem() {
        super(EquipmentSlotType.FEET);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Util.tooltipStyle("tooltip.principium.principic_boots.effect_disabled"));
        tooltip.add(Util.tooltipStyle("tooltip.principium.principic_boots"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(Util.loreStyle("lore.principium.principic_boots"));
    }

}
