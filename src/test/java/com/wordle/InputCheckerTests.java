package com.wordle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import com.wordle.InputChecker.InputError;

public class InputCheckerTests {
    @Test
    void tooShortInputString_GivesIncorrectLengthError() {
        String[] dictionary = new String[0];
        InputChecker checker = new InputChecker(dictionary);
        String input = "123";
        String[] guesses = new String[0];

        Optional<InputError> result = checker.checkInput(input, guesses);

        assertEquals(Optional.of(InputError.INCORRECT_LENGTH), result);
    }

    @Test
    void tooLongInputString_GivesIncorrectLengthError() {
        String[] dictionary = new String[0];
        InputChecker checker = new InputChecker(dictionary);
        String input = "abcdef";
        String[] guesses = new String[0];

        Optional<InputError> result = checker.checkInput(input, guesses);

        assertEquals(Optional.of(InputError.INCORRECT_LENGTH), result);
    }

    @Test
    void inputStringWithLengthFive_DoesNotGiveIncorrectLengthError() {
        String[] dictionary = new String[0];
        InputChecker checker = new InputChecker(dictionary);
        String input = "robot";
        String[] guesses = new String[0];

        Optional<InputError> result = checker.checkInput(input, guesses);

        assertNotEquals(Optional.of(InputError.INCORRECT_LENGTH), result);
    }

    @Test
    void inputStringNotInDictionary_GivesUnrecognizedWordError() {
        String[] dictionary = {"argue", "bread", "canon"};
        InputChecker checker = new InputChecker(dictionary);
        String input = "birth";
        String[] guesses = new String[0];

        Optional<InputError> result = checker.checkInput(input, guesses);

        assertEquals(Optional.of(InputError.UNRECOGNIZED_WORD), result);
    }

    @Test
    void inputStringFoundInDictionary_DoesNotGiveUnrecognizedWordError() {
        String[] dictionary = {"argue", "bread", "canon"};
        InputChecker checker = new InputChecker(dictionary);
        String input = "bread";
        String[] guesses = new String[0];

        Optional<InputError> result = checker.checkInput(input, guesses);

        assertNotEquals(Optional.of(InputError.UNRECOGNIZED_WORD), result);
    }

    @Test
    void inputStringAlreadyUsedAsGuess_GivesAlreadyGuessedError() {
        String[] dictionary = {"argue", "bread", "canon"};
        InputChecker checker = new InputChecker(dictionary);
        String input = "bread";
        String[] guesses = {"bread", "canon"};

        Optional<InputError> result = checker.checkInput(input, guesses);

        assertEquals(Optional.of(InputError.ALREADY_GUESSED), result);
    }

    @Test
    void inputStringNotAlreadyUsedAsGuess_GivesNoErrorAtAll() {
        String[] dictionary = {"argue", "bread", "canon"};
        InputChecker checker = new InputChecker(dictionary);
        String input = "argue";
        String[] guesses = {"bread", "canon"};

        Optional<InputError> result = checker.checkInput(input, guesses);

        assertEquals(Optional.empty(), result);
    }
}
