package com.alleluid.tdfmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class PrincipicBlock extends Block {

    public PrincipicBlock() {
        super(Properties.create(Material.GLASS)
        .sound(SoundType.METAL)
        .hardnessAndResistance(1.5f)
        .lightValue(14)
        );
    }
}
