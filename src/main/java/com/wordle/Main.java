package com.wordle;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color;

public class Main {
    public static void main(String[] args) {
        // create blank lines to enable vertical cursor navigation
        final int numLines = 22;
        for (int i = 0; i < numLines; i++) {
            System.out.println();
        }
        System.out.print(ansi().cursorUp(numLines));

        System.out.println();
        System.out.println("   W O R D L E    ");

        printWord("chest", new Color[] {Color.GREEN, Color.WHITE, Color.YELLOW, Color.WHITE, Color.WHITE});
        printBlankRow();
        printBlankRow();
        printBlankRow();
        printBlankRow();
        printBlankRow();

        System.out.print(ansi().reset());

        System.out.print(ansi().fg(Color.BLACK).bg(Color.WHITE));
        System.out.print("Q");
        System.out.print(ansi().reset());
        System.out.println(" W E R T Y U I O P");
        System.out.println(" A S D F G H J K L");
        System.out.println("  Z X C V B N M");

        System.out.println();
    }

    static void printBlankRow() {
        printWord("", null);
    }

    static void printWord(String word, Color[] colors) {
        System.out.print(" ");
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
}
