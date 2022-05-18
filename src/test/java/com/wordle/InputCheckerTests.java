package com.wordle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import com.wordle.InputChecker.InputError;

public class InputCheckerTests {
    @Test
    void guessLessThanFiveGivesIncorrectLengthError() {
        final String guess = "word";
        final String[] dictionary = new String[0];
        final String[] guesses = new String[0];
        InputChecker checker = new InputChecker(dictionary);

        final Optional<InputChecker.InputError> result = checker.checkInput(guess, guesses);

        assertEquals(Optional.of(InputError.INCORRECT_LENGTH), result);
    }

    @Test
    void guessMoreThanFiveGivesIncorrectLengthError() {
        final String guess = "longWord";
        final String[] dictionary = new String[0];
        final String[] guesses = new String[0];
        InputChecker checker = new InputChecker(dictionary);

        final Optional<InputChecker.InputError> result = checker.checkInput(guess, guesses);

        assertEquals(Optional.of(InputError.INCORRECT_LENGTH), result);
    }

    @Test
    void guessWithLengthFiveDoesNotGiveLengthError() {
        final String guess = "rrrrr";
        final String[] dictionary = new String[0];
        final String[] guesses = new String[0];
        InputChecker checker = new InputChecker(dictionary);

        final Optional<InputChecker.InputError> result = checker.checkInput(guess, guesses);

        assertNotEquals(Optional.of(InputError.INCORRECT_LENGTH), result);
    }

    @Test
    void guessNotInDictionaryGivesUnrecognizedWordError() {
        final String guess = "robot";
        final String[] dictionary = {"horse"};
        final String[] guesses = new String[0];
        InputChecker checker = new InputChecker(dictionary);

        final Optional<InputChecker.InputError> result = checker.checkInput(guess, guesses);

        assertEquals(Optional.of(InputError.UNRECOGNIZED_WORD), result);
    }

    @Test
    void guessThatIsInDictionaryDoesNotGiveUnrecognizedWordError() {
        final String guess = "robot";
        final String[] dictionary = {"robot"};
        final String[] guesses = new String[0];
        InputChecker checker = new InputChecker(dictionary);

        final Optional<InputChecker.InputError> result = checker.checkInput(guess, guesses);

        assertNotEquals(Optional.of(InputError.UNRECOGNIZED_WORD), result);
    }

    @Test
    void guessThatIsAlreadyGuessedGivesAlreadyGuessedError() {
        final String guess = "robot";
        final String[] dictionary = {"robot"};
        final String[] guesses = {"robot"};
        InputChecker checker = new InputChecker(dictionary);

        final Optional<InputChecker.InputError> result = checker.checkInput(guess, guesses);

        assertEquals(Optional.of(InputError.ALREADY_GUESSED), result);
    }

    @Test
    void guessThatHasNotBeenGuessedDoesNotGiveAlreadyGuessedError() {
        final String guess = "robot";
        final String[] dictionary = {"robot", "kebab"};
        final String[] guesses = {"kebab"};
        InputChecker checker = new InputChecker(dictionary);

        final Optional<InputChecker.InputError> result = checker.checkInput(guess, guesses);

        assertNotEquals(Optional.of(InputError.ALREADY_GUESSED), result);
    }
}
