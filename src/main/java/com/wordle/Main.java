package com.wordle;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MAX_GUESSES = 6;
        final int NUM_DRAW_LINES = 26; // this must exactly match the number of lines printed
        Draw.eraseLinesBelow(NUM_DRAW_LINES);

        ColoredWord guesses[] = new ColoredWord[MAX_GUESSES];
        Arrays.fill(guesses, blankLine(5));
        HashMap<Character, ColorPair> keyboardColors = new HashMap<>();

        int numGuesses = 0;
        Scanner scanner = new Scanner(System.in);
        Optional<String> errorMessage = Optional.empty();

        while (true) {
            /* Draw current game state */
            drawBoard(guesses, keyboardColors);
            System.out.println();

            /* Check if we should stop the game, or run another update */
            if (numGuesses == MAX_GUESSES) {
                break;
            }

            /* Print error message */
            if (errorMessage.isPresent()) {
                printErrorMsg(errorMessage.get());
                errorMessage = Optional.empty();
            } else {
                System.out.println();
            }

            /* Prompt user input */
            System.out.print("Enter guess: ");
            String input = scanner.next();

            if (input.equals("quit")) {
                break;
            }

            // test out error messages by just assigning it
            errorMessage = Optional.of("this is an error message");

            /* Update guesses */
            Color[] colors = new Color[input.length()];
            Arrays.fill(colors, Color.WHITE);
            guesses[numGuesses] = new ColoredWord(input, colors);
            numGuesses += 1;

            /* Reset screen */
            System.out.print(ansi().cursorUp(NUM_DRAW_LINES));
            Draw.eraseLinesBelow(NUM_DRAW_LINES);
        }

        System.out.println("bye!");

        scanner.close();
    }

    static ColoredWord blankLine(int length) {
        char[] spaces = new char[length];
        Color[] colors = new Color[length];
        Arrays.fill(colors, Color.WHITE);
        Arrays.fill(spaces, ' ');
        return new ColoredWord(new String(spaces), colors);
    }

    static void drawBoard(ColoredWord[] words, Map<Character, ColorPair> keyboardColors) {
        // draw title
        System.out.println();
        System.out.println("     W O R D L E    ");

        // draw words
        for (ColoredWord word : words) {
            System.out.print("   ");
            Draw.printFramedWord(word.word, word.colors);
        }

        // draw keyboard state
        Draw.printColoredString(" Q W E R T Y U I O P", keyboardColors);
        Draw.printColoredString("  A S D F G H J K L", keyboardColors);
        Draw.printColoredString("   Z X C V B N M", keyboardColors);
    }

    static void printErrorMsg(String msg) {
        System.out.println(ansi().fg(Color.RED) + "error: " + ansi().reset() + msg);
    }
}
