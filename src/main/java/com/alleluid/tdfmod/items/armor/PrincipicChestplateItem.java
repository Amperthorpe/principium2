package com.alleluid.tdfmod.items.armor;

import com.alleluid.tdfmod.Util;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import top.theillusivec4.caelus.api.CaelusAPI;

import javax.annotation.Nullable;
import javax.naming.CompoundName;
import java.util.List;
import java.util.UUID;

public class PrincipicChestplateItem extends AbstractPrincipicArmor {

    public PrincipicChestplateItem() {
        super(EquipmentSlotType.CHEST);
    }

    public static AttributeModifier FLIGHT_MODIFIER = new AttributeModifier(
            UUID.fromString("668bdbee-32b6-4c4b-bf6a-5a30f4d02e37"), "Flight modifier", 1.0d,
            AttributeModifier.Operation.ADDITION);

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {


        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(Util.loreStyle(new TranslationTextComponent("tooltip.tdfmod.principic_chestplate")));

        if(isElytra(stack))
            tooltip.add((new TranslationTextComponent("tooltip.tdfmod.principic_chestplate.elytra_enabled")));
        else
            tooltip.add((new TranslationTextComponent("tooltip.tdfmod.principic_chestplate.elytra_disabled")));

    }



    private static void checkNBT(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();
        if(nbt==null)
            nbt = new CompoundNBT();
        if(!nbt.contains("ElytraMode"))
            nbt.putBoolean("ElytraMode",false);
        stack.setTag(nbt);
    }

    private static boolean isElytra(ItemStack stack){
        checkNBT(stack);
        return stack.getTag().getBoolean("ElytraMode");
    }

    private static void setElytra(ItemStack stack, Boolean mode){
        checkNBT(stack);
        CompoundNBT nbt = stack.getTag();
        nbt.putBoolean("ElytraMode",mode);
        stack.setTag(nbt);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.isSneaking()) {
            ItemStack chest = playerIn.getHeldItem(handIn);
            setElytra(chest, !isElytra(chest));
            return new ActionResult<>(ActionResultType.SUCCESS, chest);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent evt)
    {

        if (!(evt.getEntityLiving() instanceof PlayerEntity)) {
            return;
        }

        if (evt.getSlot() != EquipmentSlotType.CHEST) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity) evt.getEntity();
        ItemStack equipment = evt.getTo();
        IAttributeInstance attributeInstance = playerEntity.getAttribute(CaelusAPI.ELYTRA_FLIGHT);
        attributeInstance.removeModifier(FLIGHT_MODIFIER);

        if (equipment.getItem() instanceof PrincipicChestplateItem && isElytra(equipment)) {
            attributeInstance.applyModifier(FLIGHT_MODIFIER);
        }
    }


}
