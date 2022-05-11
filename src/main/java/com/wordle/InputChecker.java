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

        // implement check input here

        return Optional.empty();
    }
}
