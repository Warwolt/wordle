package com.wordle;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InputChecker {
    public enum InputError {
        INCORRECT_LENGTH,
        UNRECOGNIZED_WORD,
        ALREADY_GUESSED,
    }

    private final List<String> dictionary;

    public InputChecker(String[] dictionary) {
        this.dictionary = Arrays.asList(dictionary);
    }

    /**
     * Checks that the input string can be used as a guess.
     *
     * @param input the lower case string input from the user
     * @param guesses all previous guesses this game session
     * @return InputError if any error found, else Optional.empty()
     */
    public Optional<InputError> checkInput(String input, final String[] guesses) {
        if (input.length() != 5) {
            return Optional.of(InputError.INCORRECT_LENGTH);
        }

        if (!dictionary.contains(input)) {
            return Optional.of(InputError.UNRECOGNIZED_WORD);
        }

        if (Arrays.asList(guesses).contains(input)) {
            return Optional.of(InputError.ALREADY_GUESSED);
        }

        return Optional.empty();
    }

}
