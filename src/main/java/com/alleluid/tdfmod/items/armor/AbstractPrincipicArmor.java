package com.alleluid.tdfmod.items.armor;

import com.alleluid.tdfmod.setup.ModSetup;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

public abstract class AbstractPrincipicArmor extends ArmorItem {

    private static final IArmorMaterial principic =
            new IArmorMaterial() {
                @Override
                public int getDurability(@Nonnull EquipmentSlotType slotIn) {
                    return -1;
                }

                @Override
                public int getDamageReductionAmount(@Nonnull EquipmentSlotType slotIn) {
                    return 0;
                }

                @Override
                public int getEnchantability() {
                    return 100;
                }

                @Override
                @Nonnull
                public SoundEvent getSoundEvent() {
                    return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
                }

                @Override
                @Nonnull
                public Ingredient getRepairMaterial() {
                    return Ingredient.EMPTY;
                }

                @Override
                public String getName() {
                    return "tdfmod:principic";
                }

                @Override
                public float getToughness() {
                    return 0;
                }
            };

    public AbstractPrincipicArmor(EquipmentSlotType slot) {
        super(
                principic,
                slot,
                new Properties()
                    .maxDamage(-1)
                    .maxStackSize(1)
                    .rarity(Rarity.EPIC)
                    .group(ModSetup.ITEM_GROUP)
        );

        DispenserBlock.registerDispenseBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public int getItemEnchantability() {
        return 15;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return this.getItemStackLimit(stack) == 1;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName(@Nonnull ItemStack stack) {
        return super.getDisplayName(stack).applyTextStyle(TextFormatting.GOLD);
    }
}
