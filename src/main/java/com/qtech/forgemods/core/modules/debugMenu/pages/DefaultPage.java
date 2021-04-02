package com.qtech.forgemods.core.modules.debugMenu.pages;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.debugMenu.DebugPage;

public class DefaultPage extends DebugPage {
    public DefaultPage() {
        super(QFMCore.getModId(), "default");
    }

    @Override
    public boolean hasRequiredComponents() {
        return true;
    }
}
