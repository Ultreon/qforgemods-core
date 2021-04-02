package com.qtech.forgemods.core.util;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DirtMessageScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.realms.RealmsBridgeScreen;
import net.minecraft.util.text.TranslationTextComponent;

@UtilityClass
public final class WorldUtils {
    public static void saveWorldThenOpenTitle() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world != null) {
            boolean flag = mc.isIntegratedServerRunning();
            boolean flag1 = mc.isConnectedToRealms();
            mc.world.sendQuittingDisconnectingPacket();
            if (flag) {
                mc.unloadWorld(new DirtMessageScreen(new TranslationTextComponent("menu.savingLevel")));
            } else {
                mc.unloadWorld();
            }

            if (flag) {
                mc.displayGuiScreen(new MainMenuScreen());
            } else if (flag1) {
                RealmsBridgeScreen realmsbridgescreen = new RealmsBridgeScreen();
                realmsbridgescreen.func_231394_a_(new MainMenuScreen());
            } else {
                mc.displayGuiScreen(new MultiplayerScreen(new MainMenuScreen()));
            }
        }
    }
    public static void saveWorldThen(Runnable runnable) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world != null) {
            boolean flag = mc.isIntegratedServerRunning();
            boolean flag1 = mc.isConnectedToRealms();
            mc.world.sendQuittingDisconnectingPacket();
            if (flag) {
                mc.unloadWorld(new DirtMessageScreen(new TranslationTextComponent("menu.savingLevel")));
            } else {
                mc.unloadWorld();
            }

            runnable.run();
        }
    }
    public static void saveWorldThenOpen(Screen screen) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world != null) {
            boolean flag = mc.isIntegratedServerRunning();
            boolean flag1 = mc.isConnectedToRealms();
            mc.world.sendQuittingDisconnectingPacket();
            if (flag) {
                mc.unloadWorld(new DirtMessageScreen(new TranslationTextComponent("menu.savingLevel")));
            } else {
                mc.unloadWorld();
            }

            mc.displayGuiScreen(screen);
        }
    }
    public static void saveWorldThenQuitGame() {
        saveWorldThen(() -> Minecraft.getInstance().shutdown());
    }
}
