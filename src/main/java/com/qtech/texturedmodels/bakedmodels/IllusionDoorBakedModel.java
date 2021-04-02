package com.qtech.texturedmodels.bakedmodels;

import com.qtech.texturedmodels.bakedmodels.helper.DoorKnobBakedModel;
import com.qtech.texturedmodels.block.DoorFrameBlock;
import com.qtech.texturedmodels.block.FrameBlock;
import com.qtech.texturedmodels.tileentity.FrameBlockTile;
import com.qtech.texturedmodels.util.ModelHelper;
import com.qtech.texturedmodels.util.TextureHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Contains all information for the block model
 * See {@link com.qtech.texturedmodels.util.ModelHelper} for more information
 *
 * @author PianoManu
 * @version 1.2 09/28/20
 */
public class IllusionDoorBakedModel implements IDynamicBakedModel {
    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        BlockState mimic = extraData.getData(FrameBlockTile.MIMIC);
        if (mimic != null && !(mimic.getBlock() instanceof FrameBlock)) {
            ModelResourceLocation location = BlockModelShapes.getModelLocation(mimic);
            if (location != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    return getIllusionQuads(state, side, rand, extraData, model);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<BakedQuad> getIllusionQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData, IBakedModel model) {
        if (side != null) {
            return Collections.emptyList();
        }
        BlockState mimic = extraData.getData(FrameBlockTile.MIMIC);
        if (mimic != null && state != null) {
            List<TextureAtlasSprite> glassBlockList = TextureHelper.getGlassTextures();
            TextureAtlasSprite glass = glassBlockList.get(extraData.getData(FrameBlockTile.GLASS_COLOR));
            List<BakedQuad> quads = new ArrayList<>();
            Direction dir = state.get(DoorFrameBlock.FACING);
            boolean open = state.get(DoorFrameBlock.OPEN);
            DoorHingeSide hinge = state.get(DoorFrameBlock.HINGE);
            Direction west = Direction.WEST;
            Direction east = Direction.EAST;
            Direction north = Direction.NORTH;
            Direction south = Direction.SOUTH;
            DoorHingeSide left = DoorHingeSide.LEFT;
            DoorHingeSide right = DoorHingeSide.RIGHT;
            int design = extraData.getData(FrameBlockTile.DESIGN);//int design = state.get(DoorFrameBlock.DESIGN);
            int desTex = extraData.getData(FrameBlockTile.DESIGN_TEXTURE); //state.get(DoorFrameBlock.DESIGN_TEXTURE);
            DoubleBlockHalf half = state.get(DoorBlock.HALF);
            DoubleBlockHalf lower = DoubleBlockHalf.LOWER;
            DoubleBlockHalf upper = DoubleBlockHalf.UPPER;
            int tintIndex = -1;
            if (mimic.getBlock() instanceof GrassBlock) {
                tintIndex = 1;
            }
            boolean northSide = (dir == north && !open && hinge == right) || (dir == east && open && hinge == right) || (dir == west && open && hinge == left) || (dir == north && !open && hinge == left);
            boolean westSide = (dir == west && !open && hinge == right) || (dir == north && open && hinge == right) || (dir == south && open && hinge == left) || (dir == west && !open && hinge == left);
            boolean eastSide = (dir == south && open && hinge == right) || (dir == east && !open && hinge == right) || (dir == east && !open && hinge == left) || (dir == north && open && hinge == left);
            if (design == 0) {
                if (northSide) {
                    if (half == lower) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 4 / 16f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(12 / 16f, 1f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(4 / 16f, 12 / 16f, 4 / 16f, 1f, 14 / 16f, 15 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 0f, 4 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    }
                    if (half == upper) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 4 / 16f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(12 / 16f, 1f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(4 / 16f, 12 / 16f, 0f, 12 / 16f, 14 / 16f, 15 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 12 / 16f, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    }
                } else if (westSide) {
                    if (half == lower) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 12 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 0f, 4 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(14 / 16f, 15 / 16f, 4 / 16f, 1f, 4 / 16f, 12 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 4 / 16f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                    if (half == upper) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 12 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 0f, 4 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(14 / 16f, 15 / 16f, 0f, 12 / 16f, 4 / 16f, 12 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 12 / 16f, 1f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                } else if (eastSide) {
                    if (half == lower) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 12 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 0f, 4 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(1 / 16f, 2 / 16f, 4 / 16f, 1f, 4 / 16f, 12 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 4 / 16f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                    if (half == upper) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 12 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 0f, 4 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(1 / 16f, 2 / 16f, 0f, 12 / 16f, 4 / 16f, 12 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 12 / 16f, 1f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                } else {
                    if (half == lower) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 4 / 16f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(12 / 16f, 1f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(4 / 16f, 12 / 16f, 4 / 16f, 1f, 1 / 16f, 2 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 0f, 4 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                    if (half == upper) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 4 / 16f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(12 / 16f, 1f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(4 / 16f, 12 / 16f, 0f, 12 / 16f, 1 / 16f, 2 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 12 / 16f, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                }
            }
            if (design == 3) {
                if (northSide) {
                    if (half == lower) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 4 / 16f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(12 / 16f, 1f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(4 / 16f, 12 / 16f, 4 / 16f, 12 / 16f, 14 / 16f, 15 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 0f, 4 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 12 / 16f, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    }
                    if (half == upper) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 4 / 16f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(12 / 16f, 1f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(4 / 16f, 12 / 16f, 4 / 16f, 12 / 16f, 14 / 16f, 15 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 12 / 16f, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 0f, 4 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    }
                } else if (westSide) {
                    if (half == lower) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 12 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 0f, 4 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(14 / 16f, 15 / 16f, 4 / 16f, 12 / 16f, 4 / 16f, 12 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 4 / 16f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 12 / 16f, 1f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                    if (half == upper) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 12 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 0f, 4 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(14 / 16f, 15 / 16f, 4 / 16f, 12 / 16f, 4 / 16f, 12 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 12 / 16f, 1f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 4 / 16f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                } else if (eastSide) {
                    if (half == lower) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 12 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 0f, 4 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(1 / 16f, 2 / 16f, 4 / 16f, 12 / 16f, 4 / 16f, 12 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 4 / 16f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 12 / 16f, 1f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                    if (half == upper) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 12 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 0f, 4 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(1 / 16f, 2 / 16f, 4 / 16f, 12 / 16f, 4 / 16f, 12 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 12 / 16f, 1f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 4 / 16f, 4 / 16f, 12 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                } else {
                    if (half == lower) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 4 / 16f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(12 / 16f, 1f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(4 / 16f, 12 / 16f, 4 / 16f, 12 / 16f, 1 / 16f, 2 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 0f, 4 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 12 / 16f, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                    if (half == upper) {
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(0f, 4 / 16f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(12 / 16f, 1f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createCuboid(4 / 16f, 12 / 16f, 4 / 16f, 12 / 16f, 1 / 16f, 2 / 16f, glass, tintIndex));
                        quads.addAll(com.qtech.texturedmodels.util.ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 12 / 16f, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                        quads.addAll(ModelHelper.createSixFaceCuboid(4 / 16f, 12 / 16f, 0f, 4 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    }
                }
            }
            if (design == 1 || design == 2) {
                int flag = 0;
                if (northSide) {
                    flag = 1;
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                } else if (westSide) {
                    flag = 2;
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 1f, 0f, 1f, mimic, model, extraData, rand, tintIndex));
                } else if (eastSide) {
                    flag = 3;
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 1f, 0f, 1f, mimic, model, extraData, rand, tintIndex));
                } else {
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                }
                if (design == 1) {
                    if (half == lower) {
                        if ((dir == south && hinge == left && !open) || (dir == west && hinge == right && open)) {
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(2 / 16f, 4 / 16f, 15 / 16f, 17 / 16f, -1 / 16f, 1 / 16f, flag, desTex));
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(2 / 16f, 4 / 16f, 15 / 16f, 17 / 16f, 2 / 16f, 4 / 16f, flag, desTex));
                        } else if ((dir == south && hinge == right && !open) || (dir == east && hinge == left && open)) {
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(12 / 16f, 14 / 16f, 15 / 16f, 17 / 16f, -1 / 16f, 1 / 16f, flag, desTex));
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(12 / 16f, 14 / 16f, 15 / 16f, 17 / 16f, 2 / 16f, 4 / 16f, flag, desTex));
                        }
                    }
                    if (half == lower) {
                        if ((dir == north && hinge == right && !open) || (dir == west && hinge == left && open)) {
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(2 / 16f, 4 / 16f, 15 / 16f, 17 / 16f, 15 / 16f, 17 / 16f, flag, desTex));
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(2 / 16f, 4 / 16f, 15 / 16f, 17 / 16f, 12 / 16f, 14 / 16f, flag, desTex));
                        } else if ((dir == north && hinge == left && !open) || (dir == east && hinge == right && open)) {
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(12 / 16f, 14 / 16f, 15 / 16f, 17 / 16f, 15 / 16f, 17 / 16f, flag, desTex));
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(12 / 16f, 14 / 16f, 15 / 16f, 17 / 16f, 12 / 16f, 14 / 16f, flag, desTex));
                        }
                    }
                    if (half == lower) {
                        if ((dir == west && hinge == left && !open) || (dir == north && hinge == right && open)) {
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(15 / 16f, 17 / 16f, 15 / 16f, 17 / 16f, 2 / 16f, 4 / 16f, flag, desTex));
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(12 / 16f, 14 / 16f, 15 / 16f, 17 / 16f, 2 / 16f, 4 / 16f, flag, desTex));
                        } else if ((dir == west && hinge == right && !open) || (dir == south && hinge == left && open)) {
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(15 / 16f, 17 / 16f, 15 / 16f, 17 / 16f, 12 / 16f, 14 / 16f, flag, desTex));
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(12 / 16f, 14 / 16f, 15 / 16f, 17 / 16f, 12 / 16f, 14 / 16f, flag, desTex));
                        }
                    }
                    if (half == lower) {
                        if ((dir == east && hinge == right && !open) || (dir == north && hinge == left && open)) {
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(-1 / 16f, 1 / 16f, 15 / 16f, 17 / 16f, 2 / 16f, 4 / 16f, flag, desTex));
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(2 / 16f, 4 / 16f, 15 / 16f, 17 / 16f, 2 / 16f, 4 / 16f, flag, desTex));
                        } else if ((dir == east && hinge == left && !open) || (dir == south && hinge == right && open)) {
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(-1 / 16f, 1 / 16f, 15 / 16f, 17 / 16f, 12 / 16f, 14 / 16f, flag, desTex));
                            quads.addAll(DoorKnobBakedModel.createDoorKnob(2 / 16f, 4 / 16f, 15 / 16f, 17 / 16f, 12 / 16f, 14 / 16f, flag, desTex));
                        }
                    }
                }
            }
            if (design == 4) {
                if (northSide) {
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createCuboid(3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, 14 / 16f, 15 / 16f, glass, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 0f, 3 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 13 / 16f, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(7 / 16f, 9 / 16f, 3 / 16f, 13 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 7 / 16f, 9 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                } else if (westSide) {
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createCuboid(14 / 16f, 15 / 16f, 3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, glass, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 3 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 13 / 16f, 1f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 3 / 16f, 13 / 16f, 7 / 16f, 9 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 7 / 16f, 9 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex));
                } else if (eastSide) {
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createCuboid(1 / 16f, 2 / 16f, 3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, glass, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 3 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 13 / 16f, 1f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 3 / 16f, 13 / 16f, 7 / 16f, 9 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 7 / 16f, 9 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex));
                } else {
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createCuboid(3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, 1 / 16f, 2 / 16f, glass, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 0f, 3 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 13 / 16f, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(7 / 16f, 9 / 16f, 3 / 16f, 13 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                    quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 7 / 16f, 9 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex));
                }
            }
            int overlayIndex = extraData.getData(FrameBlockTile.OVERLAY);
            if (overlayIndex != 0) {
                if (northSide) {
                    quads.addAll(ModelHelper.createOverlay(0f, 1f, 0f, 1f, 13 / 16f, 1f, overlayIndex));
                } else if (westSide) {
                    quads.addAll(ModelHelper.createOverlay(13 / 16f, 1f, 0f, 1f, 0f, 1f, overlayIndex));
                } else if (eastSide) {
                    quads.addAll(ModelHelper.createOverlay(0f, 3 / 16f, 0f, 1f, 0f, 1f, overlayIndex));
                } else {
                    quads.addAll(ModelHelper.createOverlay(0f, 1f, 0f, 1f, 0f, 3 / 16f, overlayIndex));
                }
            }
            return quads;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(new ResourceLocation("minecraft", "block/oak_planks"));
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }
}
//========SOLI DEO GLORIA========//