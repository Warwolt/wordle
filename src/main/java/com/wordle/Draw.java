package com.wordle;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color;

import java.util.Map;

public class Draw {
    static void clearLines(int numLines) {
        for (int i = 0; i < numLines; i++) {
            System.out.println();
        }
        System.out.print(ansi().cursorUp(numLines));
    }

    static void printFramedWord(String word, Color[] colors) {
        for (int i = 0; i < 5; i++) {
            char letter = i < word.length() ? Character.toUpperCase(word.charAt(i)) : ' ';
            Color color = i < word.length() ? colors[i] : Color.WHITE;
            printFramedLetter(letter, color);
            if (i < 4) {
                System.out.print(ansi().cursorMove(3, -3));
            }
        }
        System.out.print(ansi().reset());
        System.out.println(ansi().cursorUp(1));
    }

    static void printColoredLetters(char[] letters, Map<Character, ColorPair> colors) {
        for (char letter : letters) {
            ColorPair colorPair = colors.get(letter);
            if (colorPair != null) {
                printColoredLetter(letter, colorPair);
            } else {
                System.out.print(letter);
            }
            System.out.print(" ");
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
        printColoredLetter(letter);
    }

    static private void printColoredLetter(char letter) {
        System.out.print(letter);
        System.out.print(ansi().reset());
    }
}
