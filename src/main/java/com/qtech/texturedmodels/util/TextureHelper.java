package com.qtech.texturedmodels.util;

import com.qtech.texturedmodels.tileentity.FrameBlockTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.IModelData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Util class for picking the right texture of a block. Pretty stupid at the moment (May be removed and rewritten in the future)
 *
 * @author PianoManu
 * @version 1.5 10/29/20
 */
public class TextureHelper {

    /**
     * Used for dyed glass doors, trapdoors etc
     *
     * @return list of all glass textures
     */
    public static List<TextureAtlasSprite> getGlassTextures() {
        List<TextureAtlasSprite> glassTextures = new ArrayList<>();
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/white_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/orange_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/magenta_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/light_blue_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/yellow_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/lime_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/pink_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/gray_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/light_gray_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/cyan_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/purple_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/blue_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/brown_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/green_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/red_stained_glass")));
        glassTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/black_stained_glass")));
        return glassTextures;
    }

    public static List<TextureAtlasSprite> getWoolTextures() {
        List<TextureAtlasSprite> woolTextures = new ArrayList<>();
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/white_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/orange_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/magenta_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/light_blue_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/yellow_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/lime_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/pink_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/gray_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/light_gray_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/cyan_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/purple_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/blue_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/brown_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/green_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/red_wool")));
        woolTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/black_wool")));
        return woolTextures;
    }

    public static List<TextureAtlasSprite> getPlanksTextures() {
        List<TextureAtlasSprite> planksTextures = new ArrayList<>();
        planksTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/oak_planks")));
        planksTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/birch_planks")));
        planksTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/spruce_planks")));
        planksTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/jungle_planks")));
        planksTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/acacia_planks")));
        planksTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/dark_oak_planks")));
        planksTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/crimson_planks")));
        planksTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/warped_planks")));
        return planksTextures;
    }

    public static List<TextureAtlasSprite> getMetalTextures() {
        List<TextureAtlasSprite> metalTextures = new ArrayList<>();
        metalTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/iron_block")));
        metalTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/obsidian")));
        metalTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/stone")));
        metalTextures.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(loc("minecraft", "block/oak_log")));
        return metalTextures;
    }

    public static List<TextureAtlasSprite> getTextureFromModel(IBakedModel model, IModelData extraData, Random rand) {
        List<TextureAtlasSprite> textureList = new ArrayList<>();
        for (BakedQuad quad : model.getQuads(extraData.getData(FrameBlockTile.MIMIC), Direction.UP, rand, extraData)) {
            if (!textureList.contains(quad.getSprite())) {
                textureList.add(quad.getSprite());
            }
        }
        for (BakedQuad quad : model.getQuads(extraData.getData(FrameBlockTile.MIMIC), Direction.DOWN, rand, extraData)) {
            if (!textureList.contains(quad.getSprite())) {
                textureList.add(quad.getSprite());
            }
        }
        for (BakedQuad quad : model.getQuads(extraData.getData(FrameBlockTile.MIMIC), Direction.NORTH, rand, extraData)) {
            if (!textureList.contains(quad.getSprite())) {
                textureList.add(quad.getSprite());
            }
        }
        for (BakedQuad quad : model.getQuads(extraData.getData(FrameBlockTile.MIMIC), Direction.EAST, rand, extraData)) {
            if (!textureList.contains(quad.getSprite())) {
                textureList.add(quad.getSprite());
            }
        }
        for (BakedQuad quad : model.getQuads(extraData.getData(FrameBlockTile.MIMIC), Direction.SOUTH, rand, extraData)) {
            if (!textureList.contains(quad.getSprite())) {
                textureList.add(quad.getSprite());
            }
        }
        for (BakedQuad quad : model.getQuads(extraData.getData(FrameBlockTile.MIMIC), Direction.WEST, rand, extraData)) {
            if (!textureList.contains(quad.getSprite())) {
                textureList.add(quad.getSprite());
            }
        }
        return textureList;
    }

    private static ResourceLocation loc(String nameSpace, String path) {
        return new ResourceLocation(nameSpace, path);
    }
}
//========SOLI DEO GLORIA========//