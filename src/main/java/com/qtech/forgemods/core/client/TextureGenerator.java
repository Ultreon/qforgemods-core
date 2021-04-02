package com.qtech.forgemods.core.client;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.util.BufferUtil;
import com.qtech.forgemods.core.util.QFMTexture;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextureGenerator {
    @Getter
    private static final TextureGenerator instance = new TextureGenerator();
    private final Map<ResourceLocation, QFMTexture> textures = new HashMap<>();

    private TextureGenerator() {

    }

    public void addTexture(QFMTexture texture, ResourceLocation rl) {
        this.textures.put(rl, texture);
    }

    public void generate() {
        for (Map.Entry<ResourceLocation, QFMTexture> texture : this.textures.entrySet()) {
            try {
                Minecraft.getInstance().getTextureManager().loadTexture(texture.getKey(), new DynamicTexture(NativeImage.read(BufferUtil.toByteBuffer(texture.getValue().render()))));
            } catch (IOException e) {
                QFMCore.LOGGER.error("Couldn't generate texture: " + texture.getKey(), e);
            }
        }
    }
}
