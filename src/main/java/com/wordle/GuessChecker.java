package com.wordle;

public class GuessChecker {
    public enum LetterStatus {
        CORRECT_SPOT,
        WRONG_SPOT,
        NO_SPOT
    }

    private final String secretWord;

    public GuessChecker(String secretWord) {
        this.secretWord = secretWord;
    }

    LetterStatus[] checkGuess(String guess) {
        if (guess.length() != secretWord.length()) {
            throw new IllegalArgumentException();
        }

        LetterStatus[] statuses = new LetterStatus[guess.length()];
        char[] guessLetters = guess.toCharArray();
        char[] secretLetters = secretWord.toCharArray();

        for (int i = 0; i < guess.length(); i++) {
            // check if guess letter matches secret letter
            if (guessLetters[i] == secretLetters[i]) {
                statuses[i] = LetterStatus.CORRECT_SPOT;
            // else check if guess letter matches any secret letter
            } else if (secretWord.indexOf(guessLetters[i]) != -1) {
                statuses[i] = LetterStatus.WRONG_SPOT;
            // else, no corresponding letter found
            } else {
                statuses[i] = LetterStatus.NO_SPOT;
            }
        }

        return statuses;
    }
}
