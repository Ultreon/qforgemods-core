package com.qtech.texturedmodels.bakedmodels;

import com.qtech.texturedmodels.block.FrameBlock;
import com.qtech.texturedmodels.tileentity.FrameBlockTile;
import com.qtech.texturedmodels.util.ModelHelper;
import com.qtech.texturedmodels.util.TextureHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
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
 * See {@link ModelHelper} for more information
 *
 * @author PianoManu
 * @version 1.0 09/24/20
 */
public class FenceGateBakedModel implements IDynamicBakedModel {

    public static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "block/oak_planks");

    private TextureAtlasSprite getTexture() {
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(TEXTURE);
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        BlockState mimic = extraData.getData(FrameBlockTile.MIMIC);
        if (mimic != null && !(mimic.getBlock() instanceof FrameBlock)) {
            ModelResourceLocation location = BlockModelShapes.getModelLocation(mimic);
            if (location != null && state != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    return getMimicQuads(state, side, rand, extraData, model);
                }
            }
        }
        return Collections.emptyList();
    }

    @Nonnull
    public List<BakedQuad> getMimicQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, IModelData extraData, IBakedModel model) {
        BlockState mimic = extraData.getData(FrameBlockTile.MIMIC);
        Integer design = extraData.getData(FrameBlockTile.DESIGN);
        if (side != null) {
            return Collections.emptyList();
        }
        if (mimic != null && state != null) {
            int index = extraData.getData(FrameBlockTile.TEXTURE);
            List<TextureAtlasSprite> texture = TextureHelper.getTextureFromModel(model, extraData, rand);
            if (texture.size() <= index) {
                extraData.setData(FrameBlockTile.TEXTURE, 0);
                index = 0;
            }
            if (texture.size() == 0) {
                if (Minecraft.getInstance().player != null) {
                    Minecraft.getInstance().player.sendStatusMessage(new TranslationTextComponent("We're sorry, but this block can't be displayed"), true);
                }
                return Collections.emptyList();
            }
            int tintIndex = -1;
            if (mimic.getBlock() instanceof GrassBlock) {
                tintIndex = 1;
            }
            float w = 0;
            if (state.get(FenceGateBlock.IN_WALL)) {
                w = -3 / 16f;
            }
            List<BakedQuad> quads = new ArrayList<>();
            if (design == 0 || design == 3) {
                if (state.get(FenceGateBlock.OPEN)) {
                    switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                        case NORTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            //0-0 post
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 15 / 16f + w, 1 / 16f, 3 / 16f, texture.get(index), tintIndex));
                            //1-0 post
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 15 / 16f + w, 1 / 16f, 3 / 16f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 9 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 12 / 16f + w, 15 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 9 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 12 / 16f + w, 15 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            break;
                        case SOUTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            //0-1 post
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 15 / 16f + w, 13 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            //1-1 post
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 15 / 16f + w, 13 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 9 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 12 / 16f + w, 15 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 9 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 12 / 16f + w, 15 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            break;
                        case EAST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //1-0 post
                            quads.addAll(ModelHelper.createCuboid(13 / 16f, 15 / 16f, 6 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            //1-1 post
                            quads.addAll(ModelHelper.createCuboid(13 / 16f, 15 / 16f, 6 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 6 / 16f + w, 9 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 12 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 6 / 16f + w, 9 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 12 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            break;
                        case WEST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //0-0 post
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 3 / 16f, 6 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            //0-1 post
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 3 / 16f, 6 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 6 / 16f + w, 9 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 12 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 6 / 16f + w, 9 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 12 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            break;
                    }
                } else {
                    switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                        case NORTH:
                        case SOUTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(2 / 16f, 14 / 16f, 6 / 16f + w, 9 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(2 / 16f, 14 / 16f, 12 / 16f + w, 15 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(6 / 16f, 10 / 16f, 9 / 16f + w, 12 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            break;
                        case EAST:
                        case WEST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 6 / 16f + w, 9 / 16f + w, 2 / 16f, 14 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 12 / 16f + w, 15 / 16f + w, 2 / 16f, 14 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 9 / 16f + w, 12 / 16f + w, 6 / 16f, 10 / 16f, texture.get(index), tintIndex));
                            break;
                    }
                }
            }
            if (design == 1) {
                if (state.get(FenceGateBlock.OPEN)) {
                    switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                        case NORTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 9 / 16f + w, 1 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 12 / 16f + w, 15 / 16f + w, 1 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 9 / 16f + w, 1 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 12 / 16f + w, 15 / 16f + w, 1 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            break;
                        case SOUTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 9 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 12 / 16f + w, 15 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 9 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 12 / 16f + w, 15 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            break;
                        case EAST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 6 / 16f + w, 9 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 12 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 6 / 16f + w, 9 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 12 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            break;
                        case WEST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 6 / 16f + w, 9 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 12 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 6 / 16f + w, 9 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 12 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            break;
                    }
                } else {
                    switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                        case NORTH:
                        case SOUTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(2 / 16f, 14 / 16f, 6 / 16f + w, 9 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(2 / 16f, 14 / 16f, 12 / 16f + w, 15 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            break;
                        case EAST:
                        case WEST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 6 / 16f + w, 9 / 16f + w, 2 / 16f, 14 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 12 / 16f + w, 15 / 16f + w, 2 / 16f, 14 / 16f, texture.get(index), tintIndex));
                            break;
                    }
                }
            }
            if (design == 2) {
                if (state.get(FenceGateBlock.OPEN)) {
                    switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                        case NORTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            //0-0 post
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 13 / 16f + w, 1 / 16f, 3 / 16f, texture.get(index), tintIndex));
                            //1-0 post
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 13 / 16f + w, 1 / 16f, 3 / 16f, texture.get(index), tintIndex));
                            //2 Crossbars
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 15 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 15 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            break;
                        case SOUTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            //0-1 post
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 13 / 16f + w, 13 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            //1-1 post
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 13 / 16f + w, 13 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            //2 Crossbars
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 15 / 16f + w, 9 / 16f, 13 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 15 / 16f + w, 9 / 16f, 13 / 16f, texture.get(index), tintIndex));
                            break;
                        case EAST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //1-0 post
                            quads.addAll(ModelHelper.createCuboid(13 / 16f, 15 / 16f, 6 / 16f + w, 13 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            //1-1 post
                            quads.addAll(ModelHelper.createCuboid(13 / 16f, 15 / 16f, 6 / 16f + w, 13 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //2 Crossbars
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 13 / 16f, 6 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 13 / 16f, 6 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            break;
                        case WEST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //0-0 post
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 3 / 16f, 6 / 16f + w, 13 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            //0-1 post
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 3 / 16f, 6 / 16f + w, 13 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //2 Crossbars
                            quads.addAll(ModelHelper.createCuboid(3 / 16f, 7 / 16f, 6 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(3 / 16f, 7 / 16f, 6 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            break;
                    }
                } else {
                    switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                        case NORTH:
                        case SOUTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(2 / 16f, 6 / 16f, 6 / 16f + w, 15 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(10 / 16f, 14 / 16f, 6 / 16f + w, 15 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(6 / 16f, 10 / 16f, 6 / 16f + w, 13 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            break;
                        case EAST:
                        case WEST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 6 / 16f + w, 15 / 16f + w, 2 / 16f, 6 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 6 / 16f + w, 15 / 16f + w, 10 / 16f, 14 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 6 / 16f + w, 13 / 16f + w, 6 / 16f, 10 / 16f, texture.get(index), tintIndex));
                            break;
                    }
                }
            }
            if (design == 3) {
                switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                    case NORTH:
                    case SOUTH:
                        quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 0f, 1f, 6 / 16f, 10 / 16f, texture.get(index), tintIndex));
                        quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 0f, 1f, 6 / 16f, 10 / 16f, texture.get(index), tintIndex));
                        break;
                    case EAST:
                    case WEST:
                        quads.addAll(ModelHelper.createCuboid(6 / 16f, 10 / 16f, 0f, 1f, 0f, 2 / 16f, texture.get(index), tintIndex));
                        quads.addAll(ModelHelper.createCuboid(6 / 16f, 10 / 16f, 0f, 1f, 14 / 16f, 1f, texture.get(index), tintIndex));
                        break;
                }
            }
            if (design == 4) {
                //inverts gate height when connected with walls
                w = -3 / 16f;
                if (state.get(FenceGateBlock.IN_WALL)) {
                    w = 0;
                }
                if (state.get(FenceGateBlock.OPEN)) {
                    switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                        case NORTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            //0-0 post
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 15 / 16f + w, 1 / 16f, 3 / 16f, texture.get(index), tintIndex));
                            //1-0 post
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 15 / 16f + w, 1 / 16f, 3 / 16f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 9 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 12 / 16f + w, 15 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 9 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 12 / 16f + w, 15 / 16f + w, 3 / 16f, 7 / 16f, texture.get(index), tintIndex));
                            break;
                        case SOUTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            //0-1 post
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 15 / 16f + w, 13 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            //1-1 post
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 15 / 16f + w, 13 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 6 / 16f + w, 9 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 12 / 16f + w, 15 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 6 / 16f + w, 9 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 12 / 16f + w, 15 / 16f + w, 9 / 16f, 15 / 16f, texture.get(index), tintIndex));
                            break;
                        case EAST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //1-0 post
                            quads.addAll(ModelHelper.createCuboid(13 / 16f, 15 / 16f, 6 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            //1-1 post
                            quads.addAll(ModelHelper.createCuboid(13 / 16f, 15 / 16f, 6 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 6 / 16f + w, 9 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 12 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 6 / 16f + w, 9 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(9 / 16f, 15 / 16f, 12 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            break;
                        case WEST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //0-0 post
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 3 / 16f, 6 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            //0-1 post
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 3 / 16f, 6 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            //4 Crossbars
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 6 / 16f + w, 9 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 12 / 16f + w, 15 / 16f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 6 / 16f + w, 9 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(1 / 16f, 7 / 16f, 12 / 16f + w, 15 / 16f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            break;
                    }
                } else {
                    switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                        case NORTH:
                        case SOUTH:
                            quads.addAll(ModelHelper.createCuboid(0f, 2 / 16f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(14 / 16f, 1f, 5 / 16f + w, 1f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(2 / 16f, 14 / 16f, 6 / 16f + w, 9 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(2 / 16f, 14 / 16f, 12 / 16f + w, 15 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(6 / 16f, 10 / 16f, 9 / 16f + w, 12 / 16f + w, 7 / 16f, 9 / 16f, texture.get(index), tintIndex));
                            break;
                        case EAST:
                        case WEST:
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 0f, 2 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 5 / 16f + w, 1f + w, 14 / 16f, 1f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 6 / 16f + w, 9 / 16f + w, 2 / 16f, 14 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 12 / 16f + w, 15 / 16f + w, 2 / 16f, 14 / 16f, texture.get(index), tintIndex));
                            quads.addAll(ModelHelper.createCuboid(7 / 16f, 9 / 16f, 9 / 16f + w, 12 / 16f + w, 6 / 16f, 10 / 16f, texture.get(index), tintIndex));
                            break;
                    }
                }
            }
            return quads;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
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
        return getTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }
}
//========SOLI DEO GLORIA========//