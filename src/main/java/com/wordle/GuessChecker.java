package com.wordle;

public class GuessChecker {
    public enum LetterStatus {
        CORRECT_SPOT,
        WRONG_SPOT,
        NO_SPOT
    }

    private final String secretWord;
    private final int[] letterOccurrences = new int[Character.MAX_VALUE];

    public GuessChecker(String secretWord) {
        this.secretWord = secretWord;
        for (char c : secretWord.toCharArray()) {
            letterOccurrences[c] += 1;
        }
    }

    LetterStatus[] checkGuess(String guess) {

        // implement check guess

        return new LetterStatus[0];
    }
}
