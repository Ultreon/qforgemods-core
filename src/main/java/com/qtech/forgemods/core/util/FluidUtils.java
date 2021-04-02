package com.qtech.forgemods.core.util;

import com.qtech.forgemods.core.modules.tiles.blocks.machines.IEnergyHandler;
import com.qsoftware.modlib.api.IFluidContainer;
import lombok.experimental.UtilityClass;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;

@UtilityClass
public final class FluidUtils {
    private static void trySendFluid(int maxSend, IFluidHandler source, IFluidHandler dest) {
        FluidStack toSend = source.drain(maxSend, IFluidHandler.FluidAction.SIMULATE);
        if (toSend.isEmpty()) {
            return;
        }

        FluidStack stack = toSend;
        stack = new FluidStack(stack, dest.fill(stack, IFluidHandler.FluidAction.EXECUTE));
        if (!stack.isEmpty()) {
            source.drain(stack.getAmount(), IFluidHandler.FluidAction.EXECUTE);
        }
    }

    /**
     * Gets the energy capability for the block at the given position. If it does not have an energy
     * capability, or the block is not a tile entity, this returns null.
     *
     * @param world The world
     * @param pos   The position to check
     * @return The energy capability, or null if not present
     */
    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static IFluidHandler getFluid(IWorldReader world, BlockPos pos) {
        if (!world.isAreaLoaded(pos, 1)) return null;
        TileEntity tileEntity = world.getTileEntity(pos);
        return tileEntity != null ? tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null) : null;
    }

    /**
     * Gets the energy capability of the object (item, etc), or null if it does not have one.
     *
     * @param provider The capability provider
     * @return The energy capability, or null if not present
     */
    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static IFluidHandler getFluid(ICapabilityProvider provider) {
        return provider.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null);
    }

    /**
     * Gets the energy capability of the object (item, etc), or null if it does not have one. Tries
     * to get the capability for the given side first, then null side.
     *
     * @param provider The capability provider
     * @param side     The side being accessed
     * @return The energy capability, or null if not present
     */
    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static IFluidHandler getFluidFromSideOrNull(ICapabilityProvider provider, Direction side) {
        return provider.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side).orElse(provider.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null));
    }
}
