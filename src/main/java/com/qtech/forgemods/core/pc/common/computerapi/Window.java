package com.qtech.forgemods.core.pc.common.computerapi;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Window {
    @Getter @Setter public Point pos;
    @Getter @Setter public Dimension size;

    Window(long handle) {

    }
}
