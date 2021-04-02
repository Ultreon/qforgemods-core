package com.qtech.forgemods.core.common;

import com.qtech.forgemods.core.common.interfaces.Formattable;
import net.minecraft.util.text.TextFormatting;

public class FormattableObject implements Formattable {
    @Override
    public String toFormattedString() {
        return TextFormatting.AQUA + getClass().getPackage().getName().replaceAll("\\.", TextFormatting.GRAY + "." + TextFormatting.AQUA) +
                TextFormatting.GRAY + "." +
                TextFormatting.DARK_AQUA + getClass().getSimpleName() +
                TextFormatting.GRAY + "@" +
                TextFormatting.GREEN + Integer.toHexString(hashCode());
    }
}
