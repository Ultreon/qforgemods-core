package com.qtech.texturedmodels.util;

import com.qtech.texturedmodels.block.FrameBlock;
import com.qtech.texturedmodels.setup.Registration;
import com.qtech.texturedmodels.tileentity.FrameBlockTile;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GrassColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class ensures that blocks of grass take on the correct color
 *
 * @author PianoManu
 * @version 1.4 09/28/20
 */
public class BlockColorHandler implements IBlockColor {
    public static final IBlockColor INSTANCE = new BlockColorHandler();
    private static final Logger LOGGER = LogManager.getLogger();

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColorHandlers(final ColorHandlerEvent.Block event) {
        registerBlockColors();
        event.getBlockColors().register((x, reader, pos, u) -> reader != null
                && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), Registration.FRAMEBLOCK.get());
    }

    public static void registerBlockColors() {
        // DEBUG
        LOGGER.info("Registering block color handler");

        //Causing green color bug...
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.SLAB_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.STAIRS_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.BUTTON_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.DOOR_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.PRESSURE_PLATE_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.TRAPDOOR_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.FENCE_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.BED_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.WALL_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.LADDER_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.CHEST_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.FENCE_GATE_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.SLOPE_FRAMEBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.EDGED_SLOPE_FRAMEBLOCK.get());

        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.ILLUSION_BLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.SLAB_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.STAIRS_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.BUTTON_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.DOOR_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.PRESSURE_PLATE_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.TRAPDOOR_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.FENCE_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.BED_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.WALL_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.LADDER_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.CHEST_ILLUSIONBLOCK.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, Registration.FENCE_GATE_ILLUSIONBLOCK.get());

        LOGGER.info("Registered block color handler");
    }

    @Override
    public int getColor(@Nonnull BlockState state, @Nullable IBlockDisplayReader lightReader, @Nullable BlockPos pos, int tintIndex) {
        //TODO does this work?
        if (state.getBlock() instanceof FrameBlock && lightReader != null && pos != null) {
            TileEntity te = lightReader.getTileEntity(pos);
            if (te instanceof FrameBlockTile && state.get(BCBlockStateProperties.CONTAINS_BLOCK)) {
                BlockState containedBlock = ((FrameBlockTile) te).getMimic();
                return BiomeColors.getGrassColor(lightReader, pos);

                //return Minecraft.getInstance().getBlockColors().getColor(containedBlock, lightReader, pos, tintIndex);
            }
        }
        return BiomeColors.getGrassColor(lightReader, pos);
    }
}
//========SOLI DEO GLORIA========//