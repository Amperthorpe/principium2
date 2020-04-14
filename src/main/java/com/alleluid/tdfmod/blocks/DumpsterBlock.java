package com.alleluid.tdfmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class DumpsterBlock extends HorizontalBlock
{

    public DumpsterBlock() {
        super(Properties.create(Material.GLASS)
                .sound(SoundType.METAL)
                .hardnessAndResistance(1.5f)
        );
    }
}