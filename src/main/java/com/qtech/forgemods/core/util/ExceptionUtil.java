package com.qtech.forgemods.core.util;

import lombok.experimental.UtilityClass;

@Deprecated
@UtilityClass
public final class ExceptionUtil {
    public static UnsupportedOperationException utilityConstructor() {
        return new UnsupportedOperationException("Tried to initialize constructor of utility class.");
    }
}
