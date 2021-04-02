package com.qtech.forgemods.core.util.color;

import lombok.experimental.UtilityClass;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;

/**
 * TODO: Currently just leaning on JEI for this...
 */
@UtilityClass
public final class ColorGetter {
    public static int getColor(Fluid fluid) {
        if (fluid == Fluids.WATER) {
            return 0x0094FF;
        }
        return 0xFFFFFF;
    }

    public static int getColor(TextureAtlasSprite sprite) {
        return 0xFFFFFF;
    }
}
