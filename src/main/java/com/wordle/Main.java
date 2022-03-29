package com.wordle;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // create blank lines to enable vertical cursor navigation
        final int numLines = 22;
        for (int i = 0; i < numLines; i++) {
            System.out.println();
        }
        System.out.print(ansi().cursorUp(numLines));

        System.out.println();
        System.out.println("    W O R D L E    ");

        printWord("chest", new Color[] {Color.GREEN, Color.WHITE, Color.YELLOW, Color.WHITE, Color.WHITE});
        printBlankWordRow();
        printBlankWordRow();
        printBlankWordRow();
        printBlankWordRow();
        printBlankWordRow();

        System.out.print(ansi().reset());

        HashMap<Character, ColorPair> colors = new HashMap<>();
        colors.put('C', new ColorPair(Color.BLACK, Color.GREEN));
        colors.put('H', new ColorPair(Color.BLACK, Color.WHITE));
        colors.put('E', new ColorPair(Color.BLACK, Color.YELLOW));
        colors.put('S', new ColorPair(Color.BLACK, Color.WHITE));
        colors.put('T', new ColorPair(Color.BLACK, Color.WHITE));
        printKeyboard(colors);

        System.out.println();
    }

    static void printBlankWordRow() {
        printWord("", null);
    }

    static void printWord(String word, Color[] colors) {
        System.out.print("  ");
        int i = 0;
        for (; i < 5; i++) {
            char letter = i < word.length() ? Character.toUpperCase(word.charAt(i)) : ' ';
            Color color = i < word.length() ? colors[i] : Color.WHITE;
            printFramedLetter(letter, color);
            if (i < 4) {
                System.out.print(ansi().cursorMove(3, -3));
            }
        }
        System.out.println(ansi().cursorUp(1));
    }

    static void printFramedLetter(char letter, Color color) {
        System.out.print(ansi().fg(color.value()));
        System.out.print(String.format("┌─┐"));
        System.out.print(ansi().cursorMove(-3, 1));
        System.out.print(String.format("│%c│", letter));
        System.out.print(ansi().cursorMove(-3, 1));
        System.out.print(String.format("└─┘"));
        System.out.print(ansi().cursorMove(-3, 1));
    }

    static void printKeyboard(Map<Character, ColorPair> colors) {
        char[] firstRow = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'};
        printRow(firstRow, colors);

        char[] secondRow = {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'};
        System.out.print(" ");
        printRow(secondRow, colors);

        char[] thirdRow = {'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        System.out.print("  ");
        printRow(thirdRow, colors);
    }

    static void printRow(char[] row, Map<Character, ColorPair> colors) {
        for (char letter : row) {
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

    static void printColoredLetter(char letter, ColorPair colorPair) {
        System.out.print(ansi().fg(colorPair.fg).bg(colorPair.bg));
        printColoredLetter(letter);
    }

    static void printColoredLetter(char letter) {
        System.out.print(letter);
        System.out.print(ansi().reset());
    }
}
