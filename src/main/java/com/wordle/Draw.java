package com.wordle;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color;

import java.util.Map;

public class Draw {
    static int numPrintedLines = 0;

    /* print wrappers */
    public static void print(boolean b) {
        System.out.print(b);
    }

    public static void print(char c) {
        System.out.print(c);
    }

    public static void print(int i) {
        System.out.print(i);
    }

    public static void print(long l) {
        System.out.print(l);
    }

    public static void print(float f) {
        System.out.print(f);
    }

    public static void print(double d) {
        System.out.print(d);
    }

    public static void print(char s[]) {
        System.out.print(s);
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void print(Object obj) {
        System.out.print(obj);
    }

    /* println wrappers */
    static public void println() {
        numPrintedLines += 1;
        System.out.println();
    }

    static public void println(boolean x) {
        numPrintedLines += 1;
        System.out.println(x);
    }

    static public void println(char x) {
        numPrintedLines += 1;
        System.out.println(x);
    }

    static public void println(int x) {
        numPrintedLines += 1;
        System.out.println(x);
    }

    static public void println(long x) {
        numPrintedLines += 1;
        System.out.println(x);
    }

    static public void println(float x) {
        numPrintedLines += 1;
        System.out.println(x);
    }

    static public void println(double x) {
        numPrintedLines += 1;
        System.out.println(x);
    }

    static public void println(char x[]) {
        numPrintedLines += 1;
        System.out.println(x);
    }

    static public void println(String x) {
        numPrintedLines += 1;
        System.out.println(x);
    }

    static public void println(Object x) {
        numPrintedLines += 1;
        System.out.println();
    }

    static void countLine() {
        numPrintedLines += 1;
    }

    static void erase() {
        System.out.print(ansi().cursorUp(numPrintedLines));
        eraseLinesBelow(numPrintedLines);
        numPrintedLines = 0;
    }

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
                Draw.print(ansi().cursorMove(3, -3));
                numPrintedLines -= 3;
            }
        }
        Draw.print(ansi().reset());
        Draw.print(ansi().cursorLeft(100));
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
                Draw.print(letter);
            }
        }
        Draw.println();
    }

    static private void printFramedLetter(char letter, Color color) {
        Draw.print(ansi().fg(color.value()));
        Draw.print(String.format("┌─┐"));
        Draw.print(ansi().cursorMove(-3, 1));
        Draw.print(String.format("│%c│", letter));
        Draw.print(ansi().cursorMove(-3, 1));
        Draw.print(String.format("└─┘"));
        Draw.print(ansi().cursorMove(-3, 1));
        numPrintedLines += 3;
    }

    static private void printColoredLetter(char letter, ColorPair colorPair) {
        Draw.print(ansi().fg(colorPair.fg).bg(colorPair.bg));
        Draw.print(letter);
        Draw.print(ansi().reset());
    }
}
