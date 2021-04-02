package com.qtech.texturedmodels.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class StairsFrameModelLoader implements IModelLoader<StairsFrameModelGeometry> {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public StairsFrameModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new StairsFrameModelGeometry();
    }
}
