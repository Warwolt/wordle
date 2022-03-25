package com.wordle;

import static org.fusesource.jansi.Ansi.Color;

/**
 * A foreground and background color pair.
 */
class ColorPair {
    public final Color fg;
    public final Color bg;

    public ColorPair(Color fg, Color bg) {
        this.fg = fg;
        this.bg = bg;
    }
}
