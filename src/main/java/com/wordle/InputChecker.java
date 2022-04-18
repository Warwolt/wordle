package com.wordle;

import java.util.Arrays;
import java.util.Optional;

public class InputChecker {
    public enum InputError {
        INCORRECT_LENGTH,
        UNRECOGNIZED_WORD,
        ALREADY_GUESSED,
    }

    private final String[] dictionary;

    public InputChecker(String[] dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Checks that the input string can be used as a guess.
     *
     * @param input the string input from the user
     * @param guesses all previous guesses this game session
     * @return InputError if any error found, else Optional.empty()
     */
    public Optional<InputError> checkInput(String input, final String[] guesses) {
        /* Check length */
        if (input.length() != 5) {
            return Optional.of(InputError.INCORRECT_LENGTH);
        }

        /* Check is in dictionary */
        if (!Arrays.stream(dictionary).anyMatch(input::equals)) {
            return Optional.of(InputError.UNRECOGNIZED_WORD);
        }

        /* Check if already guessed */
        if (Arrays.stream(guesses).anyMatch(input::equals)) {
            return Optional.of(InputError.ALREADY_GUESSED);
        }

        return Optional.empty();
    }
}
