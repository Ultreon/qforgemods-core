package com.qtech.forgemods.core.common;

import com.qtech.forgemods.core.QFMCore;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.Supplier;

public abstract class CoreRegisterModule<T extends IForgeRegistryEntry<T>> extends CoreModule {
    protected static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> create(IForgeRegistry<T> registry) {
        return DeferredRegister.create(registry, QFMCore.modId);
    }

    public abstract DeferredRegister<T> getDeferredRegister();
    public abstract <O extends T> RegistryObject<O> register(String name, Supplier<O> supplier);
}
