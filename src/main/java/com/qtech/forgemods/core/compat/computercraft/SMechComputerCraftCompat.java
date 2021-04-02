package com.qtech.forgemods.core.compat.computercraft;

import com.qtech.forgemods.core.modules.tiles.blocks.machines.AbstractMachineBaseTileEntity;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public final class SMechComputerCraftCompat {
    private SMechComputerCraftCompat() {
    }

    public static void init() {
        ComputerCraftAPI.registerPeripheralProvider(SMechComputerCraftCompat::getPeripheral);
    }

    @SuppressWarnings("TypeMayBeWeakened")
    private static LazyOptional<IPeripheral> getPeripheral(World world, BlockPos pos, Direction side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof AbstractMachineBaseTileEntity) {
            return LazyOptional.of(() -> new MachinePeripheral((AbstractMachineBaseTileEntity) tileEntity));
        }
        return LazyOptional.empty();
    }
}
