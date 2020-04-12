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
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractPrincipicArmor extends ArmorItem {

    private static final IArmorMaterial principium =
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
                    return "tdfmod:principium";
                }

                @Override
                public float getToughness() {
                    return 0;
                }
            };

    public AbstractPrincipicArmor(EquipmentSlotType slot) {
        super(
                principium,
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
