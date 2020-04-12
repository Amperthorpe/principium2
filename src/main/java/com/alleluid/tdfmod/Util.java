package com.alleluid.tdfmod;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class Util {
    public static ITextComponent loreStyle(ITextComponent textComponent){
        return textComponent.applyTextStyle(TextFormatting.DARK_PURPLE).applyTextStyle(TextFormatting.ITALIC);
    }
}
