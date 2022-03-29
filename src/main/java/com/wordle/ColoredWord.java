package com.wordle;

import static org.fusesource.jansi.Ansi.Color;

class ColoredWord {
    public final String word;
    public final Color[] colors;

    public ColoredWord(String word, Color[] colors) {
        this.word = word;
        this.colors = colors;
    }
}
