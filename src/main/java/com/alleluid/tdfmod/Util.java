package com.alleluid.tdfmod;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class Util {
    public static ITextComponent loreStyle(ITextComponent textComponent){
        return textComponent.applyTextStyle(TextFormatting.DARK_PURPLE).applyTextStyle(TextFormatting.ITALIC);
    }

    public static ITextComponent loreStyle(String key, Object...args){
        return loreStyle(new TranslationTextComponent(key, args));
    }

    public static ITextComponent tooltipStyle(ITextComponent textComponent){
        return textComponent.applyTextStyle(TextFormatting.GRAY);
    }

    public static ITextComponent tooltipStyle(String key, Object... args){
        return tooltipStyle(new TranslationTextComponent(key, args));
    }

    public static void setPositionAndRotationAndUpdate(Entity entity, Double x, Double y, Double z, Float yaw, Float pitch) {
        entity.setPositionAndUpdate(x, y, z);
        entity.setPositionAndRotation(x, y, z, yaw, pitch);
    }

    public static void setPositionAndRotationAndUpdate(Entity entity, Double x, Double y, Double z){
        setPositionAndRotationAndUpdate(entity, x, y, z, entity.rotationYaw, entity.rotationPitch);
    }

    public static boolean checkHeadspace(World world, BlockPos pos){
        return world.isAirBlock(pos) && world.isAirBlock(pos.up());
    }


}
