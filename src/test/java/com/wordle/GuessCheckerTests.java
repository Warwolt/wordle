package com.wordle;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import com.wordle.GuessChecker.LetterStatus;

public class GuessCheckerTests {
    @Test
    void tooShortGuess_ThrowsException() {
        final GuessChecker checker = new GuessChecker("123");
        assertThrows(IllegalArgumentException.class, () -> {
            checker.checkGuess("1");
        });
    }

    @Test
    void tooLongGuess_ThrowsException() {
        final GuessChecker checker = new GuessChecker("123");
        assertThrows(IllegalArgumentException.class, () -> {
            checker.checkGuess("12345");
        });
    }

    @Test
    void wordWithNoCorrectLetters_GivesAllNoSpotStatuses() {
        final GuessChecker checker = new GuessChecker("abc");
        final String guess = "xyz";

        final LetterStatus[] statuses = checker.checkGuess(guess);

        final LetterStatus[] expected = new LetterStatus[guess.length()];
        Arrays.fill(expected, LetterStatus.NO_SPOT);
        assertArrayEquals(expected, statuses);
    }

    @Test
    void singleLetter_InWrongSpot_GivesSingleWrongSpotStatus() {
        final GuessChecker checker = new GuessChecker("abc");
        final String guess = "xcy";

        final LetterStatus[] statuses = checker.checkGuess(guess);

        final LetterStatus[] expected = { LetterStatus.NO_SPOT, LetterStatus.WRONG_SPOT, LetterStatus.NO_SPOT };
        assertArrayEquals(expected, statuses);
    }

    @Test
    void singleLetter_InCorrectSpot_GivesSingleCorrectSpotStatus() {
        final GuessChecker checker = new GuessChecker("abc");
        final String guess = "ayz";

        final LetterStatus[] statuses = checker.checkGuess(guess);

        final LetterStatus[] expected = { LetterStatus.CORRECT_SPOT, LetterStatus.NO_SPOT, LetterStatus.NO_SPOT };
        assertArrayEquals(expected, statuses);
    }

    @Test
    void twoLetters_BothWrongSpots_GivesWrongSpotStatuses() {
        final GuessChecker checker = new GuessChecker("abc");
        final String guess = "uab";

        final LetterStatus[] statuses = checker.checkGuess(guess);

        final LetterStatus[] expected = { LetterStatus.NO_SPOT, LetterStatus.WRONG_SPOT, LetterStatus.WRONG_SPOT };
        assertArrayEquals(expected, statuses);
    }

    @Test
    void twoLetters_OneInCorrectSpot_GivesOneCorrectSpotStatus() {
        final GuessChecker checker = new GuessChecker("abc");
        final String guess = "aub";

        final LetterStatus[] statuses = checker.checkGuess(guess);

        final LetterStatus[] expected = { LetterStatus.CORRECT_SPOT, LetterStatus.NO_SPOT, LetterStatus.WRONG_SPOT };
        assertArrayEquals(expected, statuses);
    }

    @Test
    void twoLetters_BothInCorrectSpot_GivesTwoCorrectSpotStatuses() {
        final GuessChecker checker = new GuessChecker("abc");
        final String guess = "abz";

        final LetterStatus[] statuses = checker.checkGuess(guess);

        final LetterStatus[] expected = { LetterStatus.CORRECT_SPOT, LetterStatus.CORRECT_SPOT, LetterStatus.NO_SPOT};
        assertArrayEquals(expected, statuses);
    }

    @Test
    void twoEqualLetters_ButOnlyOneInSecretWord_GivesOnlyOneCorrectSpotStatus() {
        final GuessChecker checker = new GuessChecker("abc");
        final String guess = "bby";

        final LetterStatus[] statuses = checker.checkGuess(guess);

        final LetterStatus[] expected = { LetterStatus.NO_SPOT, LetterStatus.CORRECT_SPOT, LetterStatus.NO_SPOT};
        assertArrayEquals(expected, statuses);
    }

    @Test
    @Disabled
    // TODO: give a more sensible name for this, figure out how to make it pass
    void threeEqualLetters_OnlyTwoInSecretWord_GivesOneCorrectOneWrongAndOneNoSpotStatus() {
        final GuessChecker checker = new GuessChecker("babe");
        final String guess = "obbb";

        final LetterStatus[] statuses = checker.checkGuess(guess);

        final LetterStatus[] expected = { LetterStatus.NO_SPOT, LetterStatus.WRONG_SPOT, LetterStatus.CORRECT_SPOT, LetterStatus.NO_SPOT};
        assertArrayEquals(expected, statuses);
    }
}
