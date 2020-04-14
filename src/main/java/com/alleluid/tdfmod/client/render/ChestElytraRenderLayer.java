package com.alleluid.tdfmod.client.render;

import com.alleluid.tdfmod.items.armor.PrincipicChestplateItem;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class ChestElytraRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends
        ElytraLayer<T, M> {

    private static final ResourceLocation TEXTURE_ELYTRA =
            new ResourceLocation("tdfmod:textures/models/golden_elytra_model.png");

    private final ElytraModel<T> modelElytra = new ElytraModel<>();

    public ChestElytraRenderLayer(IEntityRenderer<T, M> renderer) {

        super(renderer);
    }

    @Override
    public void render(@Nonnull T entityIn, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        ItemStack itemstack = entityIn.getItemStackFromSlot(EquipmentSlotType.CHEST);

        if (itemstack.getItem() != Items.ELYTRA) {

            Boolean renderElytra = false;
            Boolean renderElytraGlowing = false;

            if (itemstack.getItem() instanceof PrincipicChestplateItem && PrincipicChestplateItem.isEnabled(itemstack))
            {
                renderElytra = true;
                if(itemstack.isEnchanted())
                {
                    renderElytraGlowing = true;
                }
            }


            if (renderElytra) {
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.enableAlphaTest();
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

                if (entityIn instanceof AbstractClientPlayerEntity) {
                    AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity) entityIn;
                    boolean hasElytra = abstractclientplayerentity.isPlayerInfoSet()
                            && abstractclientplayerentity.getLocationElytra() != null;
                    boolean hasCape = abstractclientplayerentity.hasPlayerInfo()
                            && abstractclientplayerentity.getLocationCape() != null
                            && abstractclientplayerentity.isWearing(PlayerModelPart.CAPE);

                    if (hasElytra) {
                        this.bindTexture(abstractclientplayerentity.getLocationElytra());
                    } else if (hasCape) {
                        this.bindTexture(abstractclientplayerentity.getLocationCape());
                    } else {
                        this.bindTexture(TEXTURE_ELYTRA);
                    }
                } else {
                    this.bindTexture(TEXTURE_ELYTRA);
                }

                GlStateManager.pushMatrix();
                GlStateManager.translatef(0.0F, 0.0F, 0.125F);
                this.modelElytra.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks,
                        netHeadYaw, headPitch, scale);
                this.modelElytra.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,
                        headPitch, scale);

                if (renderElytraGlowing) {
                    ArmorLayer.func_215338_a(this::bindTexture, entityIn, this.modelElytra, limbSwing,
                            limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch,
                            scale);
                }

                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.disableBlend();
                GlStateManager.disableAlphaTest();
                GlStateManager.popMatrix();
            }
        }
    }
}