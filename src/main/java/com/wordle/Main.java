package com.wordle;

import static org.fusesource.jansi.Ansi.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ColoredWord words[] = {
                new ColoredWord(
                        "chest",
                        new Color[] { Color.GREEN, Color.WHITE, Color.YELLOW, Color.WHITE, Color.WHITE })
        };

        HashMap<Character, ColorPair> keyColors = new HashMap<>();
        keyColors.put('C', new ColorPair(Color.BLACK, Color.GREEN));
        keyColors.put('H', new ColorPair(Color.BLACK, Color.WHITE));
        keyColors.put('E', new ColorPair(Color.BLACK, Color.YELLOW));
        keyColors.put('S', new ColorPair(Color.BLACK, Color.WHITE));
        keyColors.put('T', new ColorPair(Color.BLACK, Color.WHITE));

        Draw.clearLines(22);
        drawBoard(words, keyColors);
        System.out.println();
    }

    static void drawBoard(
            ColoredWord[] words,
            Map<Character, ColorPair> keyColors) {
        drawTitle();
        drawWords(words);
        drawKeyboard(keyColors);
    }

    static void drawTitle() {
        System.out.println();
        System.out.println("     W O R D L E    ");
    }

    static void drawWords(ColoredWord[] words) {
        for (int row = 0; row < 6; row++) {
            System.out.print("   ");
            if (row < words.length) {
                Draw.printFramedWord(words[row].word, words[row].colors);
            } else {
                Color[] colors = new Color[5];
                Arrays.fill(colors, Color.WHITE);
                Draw.printFramedWord("     ", colors);
            }
        }
    }

    static void drawKeyboard(Map<Character, ColorPair> colors) {
        String firstRow = "Q W E R T Y U I O P";
        String secondRow = "A S D F G H J K L";
        String thirdRow = "Z X C V B N M";

        System.out.print(" ");
        Draw.printColoredString(firstRow, colors);

        System.out.print("  ");
        Draw.printColoredString(secondRow, colors);

        System.out.print("   ");
        Draw.printColoredString(thirdRow, colors);
    }
}
