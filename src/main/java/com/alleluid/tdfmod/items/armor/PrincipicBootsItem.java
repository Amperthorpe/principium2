package com.alleluid.tdfmod.items.armor;

import com.alleluid.tdfmod.Util;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
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
        tooltip.add(Util.tooltipStyle("tooltip.tdfmod.principic_boots.effect_disabled"));
        tooltip.add(Util.tooltipStyle("tooltip.tdfmod.principic_boots"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(Util.loreStyle("lore.tdfmod.principic_boots"));
    }
}
