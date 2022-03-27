package com.wordle;

// import static com.wordle.EscapeCode.Color;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Main {
    public static void main(String[] args) {
        // System.out.println( ansi().fg(RED).a("Hello").fg(GREEN).a(" World").reset() );
        System.out.println("hej världen!");
        System.out.println§(String.format("┌─┐"));

        // // create blank lines to enable vertical cursor navigation
        // final int numLines = 22;
        // for (int i = 0; i < numLines; i++) {
        //     System.out.println();
        // }
        // System.out.print(EscapeCode.moveUp(numLines));

        // System.out.println();
        // System.out.println("   W O R D L E    ");
        // System.out.println();

        // printWord("chest", new Color[] {Color.GREEN, Color.WHITE, Color.YELLOW, Color.WHITE, Color.WHITE});
        // printBlankRow();
        // printBlankRow();
        // printBlankRow();
        // printBlankRow();
        // printBlankRow();

        // System.out.print(EscapeCode.RESET);

        // System.out.print("\033[0;30;47m");
        // System.out.print("Q");
        // System.out.print(EscapeCode.RESET);
        // System.out.println(" W E R T Y U I O P");
        // System.out.println(" A S D F G H J K L");
        // System.out.println("  Z X C V B N M");

        // System.out.println();
    }

    // static void printBlankRow() {
    //     printWord("", null);
    // }

    // static void printWord(String word, Color[] colors) {
    //     System.out.print(" ");
    //     int i = 0;
    //     for (; i < 5; i++) {
    //         char letter = i < word.length() ? Character.toUpperCase(word.charAt(i)) : ' ';
    //         Color color = i < word.length() ? colors[i] : Color.WHITE;
    //         printFramedLetter(letter, color);
    //         if (i < 4) {
    //             System.out.print(EscapeCode.moveForward(3) + EscapeCode.moveUp(3));
    //         }
    //     }
    //     System.out.println(EscapeCode.moveUp(1));
    // }

    // static void printFramedLetter(char letter, Color color) {
    //     System.out.print(color.code);
    //     System.out.print(String.format("┌─┐"));
    //     System.out.print(EscapeCode.moveBack(3) + EscapeCode.moveDown(1));
    //     System.out.print(String.format("│%c│", letter));
    //     System.out.print(EscapeCode.moveBack(3) + EscapeCode.moveDown(1));
    //     System.out.print(String.format("└─┘"));
    //     System.out.print(EscapeCode.moveBack(3) + EscapeCode.moveDown(1));
    // }
}
