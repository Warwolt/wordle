package com.wordle;

import static org.fusesource.jansi.Ansi.Color;

/**
 * A string and the colors associated with each position in the string.
 */
class ColoredWord {
    public final String word;
    public final Color[] colors;

    public ColoredWord(String word, Color[] colors) {
        this.word = word;
        this.colors = colors;
    }
}
