package com.alleluid.tdfmod.items;

import com.alleluid.tdfmod.Util;
import com.alleluid.tdfmod.setup.ModSetup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

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

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        BlockPos pos = getStoredPos(stack);
        if (pos.getY() > 0){
            tooltip.add(new TranslationTextComponent("tooltip.tdfmod.transport_rod.set_block_pos", pos.getX(), pos.getY(), pos.getZ()));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.tdfmod.transport_rod.no_set_block_pos"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    private static boolean checkHeadspace(World world, BlockPos pos){
        return world.isAirBlock(pos) && world.isAirBlock(pos.up());
    }

    private static void checkNBT(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();
        if(nbt==null)
            nbt = new CompoundNBT();
        if(!nbt.contains("StoredPos"))
            nbt.putIntArray("StoredPos", new int[]{0, -1, 0});
        stack.setTag(nbt);
    }

    private static void setStoredPos(ItemStack stack, BlockPos pos){
        checkNBT(stack);
        CompoundNBT nbt = stack.getTag();
        assert nbt != null;
        nbt.putIntArray("StoredPos", new int[]{pos.getX(), pos.getY(), pos.getZ()});
        stack.setTag(nbt);
    }

    private static BlockPos getStoredPos(ItemStack stack) {
        checkNBT(stack);
        assert stack.getTag() != null;
        int[] posArray = stack.getTag().getIntArray("StoredPos");
        return new BlockPos(posArray[0], posArray[1], posArray[2]);
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

    @Override
    @Nonnull
    public ActionResultType onItemUse(ItemUseContext context) {
        didAltTeleport = true;
        if (!context.isPlacerSneaking()) {
            BlockPos newPos;
            switch (context.getFace()) {
                case DOWN: newPos = new BlockPos(
                        context.getPos().getX(),
                        context.getPos().getY() + 1,
                        context.getPos().getZ()
                );
                break;
                case NORTH: newPos = new BlockPos(
                        context.getPos().getX(),
                        context.getPos().getY(),
                        context.getPos().getZ() + 1
                );
                break;
                case SOUTH: newPos = new BlockPos(
                        context.getPos().getX(),
                        context.getPos().getY(),
                        context.getPos().getZ() - 1
                );
                break;
                case WEST: newPos = new BlockPos(
                        context.getPos().getX() + 1,
                        context.getPos().getY(),
                        context.getPos().getZ()
                );
                break;
                case EAST: newPos = new BlockPos(
                        context.getPos().getX() - 1,
                        context.getPos().getY(),
                        context.getPos().getZ()
                );
                break;
                default: newPos = new BlockPos(
                        context.getPos().getX(),
                        context.getPos().getY() - 2, // -2 to allow for head room
                        context.getPos().getZ()
                );
                break;

            }
            if (context.getWorld().getBlockState(context.getPos()).getBlockHardness(context.getWorld(), context.getPos()) >= 0 && checkHeadspace(context.getWorld(), newPos)) {
                Util.setPositionAndRotationAndUpdate(context.getPlayer(), newPos.getX() + 0.5, (double) newPos.getY(), newPos.getZ() + 0.5);
                context.getWorld().playSound(context.getPlayer(), newPos, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 0.3f, 1f);
                return ActionResultType.SUCCESS;
            } else {
                didAltTeleport = false;
                return ActionResultType.FAIL;
            }
        } else {
            setStoredPos(context.getItem(), context.getPos());
            if (context.getPlayer() != null){
                context.getPlayer().sendStatusMessage(new TranslationTextComponent(
                        "status.tdfmod.transport_rod.set_block_pos",
                        context.getPos().getX(),
                        context.getPos().getY(),
                        context.getPos().getZ()
                ), true);
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof PlayerEntity && !target.isSneaking()){
            ((PlayerEntity) target).sendStatusMessage(new TranslationTextComponent("tooltip.tdfmod.transport_rod.player_not_sneaking"), true);
        } else {
            BlockPos pos = getStoredPos(stack);
            if (attacker.world.isRemote && attacker instanceof PlayerEntity){
                attacker.world.playSound((PlayerEntity)attacker, pos, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 0.3f, 1f);
            }
            Util.setPositionAndRotationAndUpdate(target, pos.getX() + 0.5, (double) pos.getY(), pos.getZ() + 0.5);
            return true;
        }
        return false;
    }
}
