package com.alleluid.tdfmod.items.armor;

import com.alleluid.tdfmod.Util;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PrincipicChestplateItem extends AbstractPrincipicArmor {

    public PrincipicChestplateItem() {
        super(EquipmentSlotType.CHEST);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Util.loreStyle(new TranslationTextComponent("tooltip.tdfmod.principic_chestplate")));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
