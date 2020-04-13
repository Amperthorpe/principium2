package com.alleluid.tdfmod;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Util {
    public static ITextComponent loreStyle(ITextComponent textComponent){
        return textComponent.applyTextStyle(TextFormatting.DARK_PURPLE).applyTextStyle(TextFormatting.ITALIC);
    }

    public static void setPositionAndRotationAndUpdate(Entity entity, Double x, Double y, Double z, Float yaw, Float pitch) {
        entity.setPositionAndUpdate(x, y, z);
        entity.setPositionAndRotation(x, y, z, yaw, pitch);
    }

    public static void setPositionAndRotationAndUpdate(Entity entity, Double x, Double y, Double z){
        setPositionAndRotationAndUpdate(entity, x, y, z, entity.rotationYaw, entity.rotationPitch);
    }

}
