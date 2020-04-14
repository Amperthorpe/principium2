package com.alleluid.tdfmod.items.armor;

import com.alleluid.tdfmod.Util;
import com.alleluid.tdfmod.items.IClickable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
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
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.caelus.api.CaelusAPI;
import top.theillusivec4.caelus.api.event.RenderElytraEvent;

import javax.annotation.Nullable;
import javax.naming.CompoundName;
import java.util.List;
import java.util.UUID;

public class PrincipicChestplateItem extends AbstractPrincipicArmor implements IClickable {

    public PrincipicChestplateItem() {
        super(EquipmentSlotType.CHEST);
    }

    public static AttributeModifier FLIGHT_MODIFIER = new AttributeModifier(
            UUID.fromString("668bdbee-32b6-4c4b-bf6a-5a30f4d02e37"), "Flight modifier", 1.0d,
            AttributeModifier.Operation.ADDITION);

    public static void onRenderElytraEvent(RenderElytraEvent evt) {
        evt.setRenderElytra(false);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {


        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(Util.loreStyle("tooltip.tdfmod.principic_chestplate"));

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
            nbt.putBoolean("ElytraMode",true);
        stack.setTag(nbt);
    }

    public static boolean isElytra(ItemStack stack){
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



    @Override
    public boolean onClick(PlayerEntity player, ItemStack itemStack, Container container, int slot) {

        setElytra(itemStack, !isElytra(itemStack));
        return true;
    }
}
