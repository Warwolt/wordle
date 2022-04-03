package com.wordle;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color;

import java.util.Map;

public class Draw {
    static void eraseLinesBelow(int numLines) {
        for (int i = 0; i < numLines; i++) {
            System.out.println(ansi().eraseLine());
        }
        System.out.print(ansi().cursorUp(numLines));
    }

    /**
     * Print a word with a square border around every letter.
     */
    static void printFramedWord(String word, Color[] colors) {
        for (int i = 0; i < word.length(); i++) {
            char letter = Character.toUpperCase(word.charAt(i));
            Color color = colors[i];
            printFramedLetter(letter, color);
            if (i < word.length() - 1) {
                System.out.print(ansi().cursorMove(3, -3));
            }
        }
        System.out.print(ansi().reset());
        System.out.println(ansi().cursorUp(1));
    }

    /**
     * Prints a string with an optional color per character.
     */
    static void printColoredString(String string, Map<Character, ColorPair> colors) {
        for (char letter : string.toCharArray()) {
            ColorPair colorPair = colors.get(letter);
            if (colorPair != null) {
                printColoredLetter(letter, colorPair);
            } else {
                System.out.print(letter);
            }
        }
        System.out.println();
    }

    static private void printFramedLetter(char letter, Color color) {
        System.out.print(ansi().fg(color.value()));
        System.out.print(String.format("┌─┐"));
        System.out.print(ansi().cursorMove(-3, 1));
        System.out.print(String.format("│%c│", letter));
        System.out.print(ansi().cursorMove(-3, 1));
        System.out.print(String.format("└─┘"));
        System.out.print(ansi().cursorMove(-3, 1));
    }

    static private void printColoredLetter(char letter, ColorPair colorPair) {
        System.out.print(ansi().fg(colorPair.fg).bg(colorPair.bg));
        System.out.print(letter);
        System.out.print(ansi().reset());
    }
}
