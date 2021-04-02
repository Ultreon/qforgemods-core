package com.qtech.texturedmodels.setup;

import com.qtech.texturedmodels.QTextureModels;
import com.qtech.texturedmodels.model.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Class for client setup
 * Things like model loaders are registered here
 *
 * @author PianoManu
 * @version 1.7 09/24/20
 */
@Mod.EventBusSubscriber(modid = QTextureModels.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void init(final ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frameloader"), new FrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_slab_loader"), new SlabFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_button_loader"), new ButtonFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_button_pressed_loader"), new ButtonPressedFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_pressure_plate_loader"), new PressurePlateFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_pressure_plate_pressed_loader"), new PressurePlatePressedFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_stairs_loader"), new StairsFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_door_loader"), new DoorFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_trapdoor_loader"), new TrapdoorFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_fence_loader"), new FenceFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_bed_loader"), new BedFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_wall_loader"), new WallFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_ladder_loader"), new LadderFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_chest_loader"), new ChestFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_fence_gate_loader"), new FenceGateFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_slope_loader"), new SlopeFrameModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "frame_edged_slope_loader"), new EdgedSlopeFrameModelLoader());

        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_block_loader"), new IllusionBlockModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_slab_loader"), new IllusionSlabModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_button_loader"), new IllusionButtonModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_button_pressed_loader"), new IllusionButtonPressedModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_pressure_plate_loader"), new IllusionPressurePlateModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_pressure_plate_pressed_loader"), new IllusionPressurePlatePressedModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_stairs_loader"), new IllusionStairsModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_door_loader"), new IllusionDoorModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_trapdoor_loader"), new IllusionTrapdoorModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_fence_loader"), new IllusionFenceModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_bed_loader"), new IllusionBedModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_wall_loader"), new IllusionWallModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_ladder_loader"), new IllusionLadderModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_chest_loader"), new IllusionChestModelLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(QTextureModels.MOD_ID, "illusion_fence_gate_loader"), new IllusionFenceGateModelLoader());
    }
}
//========SOLI DEO GLORIA========//