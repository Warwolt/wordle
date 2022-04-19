package com.wordle;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.wordle.GuessChecker.LetterStatus;
import com.wordle.InputChecker.InputError;

public class Main {
    enum GameState {
        RUNNING,
        WON,
        LOST,
        QUIT,
    }

    public static void main(String[] args) {
        final int MAX_GUESSES = 6;
        final int WORD_LENGTH = 5;
        final int NUM_INITIAL_LINES = 26; // needed to be able to move cursor down
        Draw.eraseLinesBelow(NUM_INITIAL_LINES);

        HashMap<Character, ColorPair> keyboardColors = new HashMap<>();
        String[] guessWords = new String[MAX_GUESSES];
        Arrays.fill(guessWords, "     ");
        Color[][] guessColors = new Color[MAX_GUESSES][];
        for (int i = 0; i < MAX_GUESSES; i++) {
            guessColors[i] = whiteLine(WORD_LENGTH);
        }

        int numGuesses = 0;
        Scanner scanner = new Scanner(System.in);
        Optional<String> errorMessage = Optional.empty();

        final String[] dictionary = {
            "above",
            "aboil",
            "below",
            "clear",
            "devil",
            "ebola",
            "fiend",
            "guard",
            "robot"
        };
        final InputChecker inputChecker = new InputChecker(dictionary);
        final String secretWord = "aboil";
        final GuessChecker guessChecker = new GuessChecker(secretWord);

        GameState gameState = GameState.RUNNING;
        while (gameState.equals(GameState.RUNNING)) {
            /* Draw current game state */
            drawBoard(guessWords, guessColors, keyboardColors);
            Draw.println();

            /* Check if we should stop the game, or run another update */
            if (numGuesses == MAX_GUESSES) {
                gameState = GameState.LOST;
                break;
            }

            if (numGuesses > 0 && guessWords[numGuesses - 1].equals(secretWord)) {
                gameState = GameState.WON;
                break;
            }

            /* Print error message */
            if (errorMessage.isPresent()) {
                printErrorMsg(errorMessage.get());
                errorMessage = Optional.empty();
            }

            /* Prompt user input */
            Draw.print("Enter guess: ");
            String input = scanner.next();
            Draw.countLine(); // count scanner newline

            if (input.equals("quit")) {
                gameState = GameState.QUIT;
                break;
            }

            /* Check user input */
            Optional<InputError> inputError = inputChecker.checkInput(input, guessWords);
            if (inputError.isPresent()) {
                errorMessage = Optional.of(getInputErrorMsg(inputError.get(), input));
            } else {
                /* Update guesses */
                String guess = input;
                LetterStatus[] statuses = guessChecker.checkGuess(guess);
                for (int i = 0; i < statuses.length; i++) {
                    Color color = letterStatusToColor(statuses[i]);
                    guessColors[numGuesses][i] = color;

                    char key = Character.toUpperCase(guess.charAt(i));
                    ColorPair colorPair = new ColorPair(Color.BLACK, color);
                    keyboardColors.put(key, colorPair);
                }
                guessWords[numGuesses] = guess;
                numGuesses += 1;
            }

            /* Reset screen */
            Draw.erase();
        }

        Draw.println(getGoodByeMsg(gameState));

        scanner.close();
    }

    static Color[] whiteLine(int length) {
        Color[] colors = new Color[length];
        Arrays.fill(colors, Color.WHITE);
        return colors;
    }

    static void drawBoard(String[] words, Color[][] colors, Map<Character, ColorPair> keyboardColors) {
        // draw title
        Draw.println();
        Draw.println("     W O R D L E    ");

        // draw words
        for (int i = 0; i < words.length; i++) {
            Draw.print("   ");
            Draw.printFramedWord(words[i], colors[i]);
        }

        // draw keyboard state
        Draw.printColoredString(" Q W E R T Y U I O P", keyboardColors);
        Draw.printColoredString("  A S D F G H J K L", keyboardColors);
        Draw.printColoredString("   Z X C V B N M", keyboardColors);
    }

    static void printErrorMsg(String msg) {
        Draw.println(ansi().fg(Color.RED) + "error: " + ansi().reset() + msg);
    }

    static String getInputErrorMsg(InputError error, String input) {
        switch (error) {
            case INCORRECT_LENGTH:
                return "Input must be exactly 5 letters";
            case UNRECOGNIZED_WORD:
                return "Unrecognized word \"" + input + "\"";
            case ALREADY_GUESSED:
                return "You have already guessed \"" + input + "\"";
            default:
                throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    static String getGoodByeMsg(GameState state) {
        switch (state) {
            case WON:
                return "You win!";
            case LOST:
                return "Better luck next time!";
            case QUIT:
                return "Bye!";
            default:
                throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    static Color letterStatusToColor(LetterStatus status) {
        switch (status) {
            case CORRECT_SPOT:
                return Color.GREEN;
            case WRONG_SPOT:
                return Color.YELLOW;
            case NO_SPOT:
                return Color.WHITE;
            default:
                throw new UnsupportedOperationException("Not implemented yet");
        }
    }
}
