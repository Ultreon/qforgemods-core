package com.qtech.forgemods.core.plugins;

import com.google.common.annotations.Beta;

@Beta
public class ModPlugin {
    private final Class<?> clazz;

    public ModPlugin(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getPluginClass() {
        return clazz;
    }
}
