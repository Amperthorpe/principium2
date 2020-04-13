package com.alleluid.tdfmod.items;

import com.alleluid.tdfmod.Util;
import com.alleluid.tdfmod.setup.ModSetup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.awt.geom.Arc2D;

public class TransportRodItem extends Item {
    public static final float teleRange = 128.0f;
    public static final int maxBlocksSearched = 5;
    public static boolean didAltTeleport = false;

    public TransportRodItem() {
        super(new Properties()
                .maxStackSize(1)
                .group(ModSetup.ITEM_GROUP)
        );
    }

    private static boolean checkHeadspace(World world, BlockPos pos){
        return world.isAirBlock(pos) && world.isAirBlock(pos.up());
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!playerIn.isSneaking() && !didAltTeleport){
            Vec3d lookVec = playerIn.getLookVec();
            Vec3d start = new Vec3d(playerIn.posX, playerIn.posY+ playerIn.getEyeHeight(), playerIn.posZ);
            Vec3d end = start.add(lookVec.x * teleRange, lookVec.y * teleRange, lookVec.z * teleRange);
            BlockRayTraceResult raytrace = worldIn.rayTraceBlocks(
                    new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, playerIn)
            );
            BlockPos pos = raytrace.getPos();

            for (int i=0; i < maxBlocksSearched; i++){
                // Check two blocks to insure no suffocation.
                BlockPos adjustedPos = new BlockPos(pos.getX(), pos.getY() + i, pos.getZ());
                if (checkHeadspace(worldIn, adjustedPos)) {
                    if (!worldIn.isRemote){
                        playerIn.fallDistance = 0f;
                        Util.setPositionAndRotationAndUpdate(
                            playerIn,
                            adjustedPos.getX() + 0.5,
                            (double) adjustedPos.getY(),
                            adjustedPos.getZ() + 0.5
                        );
                    } else {
                        worldIn.playSound(playerIn, adjustedPos, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 0.3f, 1f);
                        // TODO: add particle here
                    }
                    break;
                }
            }
        } else {
            didAltTeleport = false;
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
