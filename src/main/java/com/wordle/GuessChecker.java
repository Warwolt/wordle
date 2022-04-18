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
        // 1. check for exact matches
        // 2. check letters in wrong spots
        // 3. mark remaining letters as incorrect
        for (int i = 0; i < guess.length(); i++) {
            char currentChar = guess.charAt(i);
            if (currentChar == secretWord.charAt(i)) {
                statuses[i] = LetterStatus.CORRECT_SPOT;
            } else if (secretWord.indexOf(currentChar) != -1) {
                long guessCount = guess.chars().filter(ch -> ch == currentChar).count();
                long secretCount = secretWord.chars().filter(ch -> ch == currentChar).count();

                if (guessCount <= secretCount) {
                    statuses[i] = LetterStatus.WRONG_SPOT;
                } else {
                    statuses[i] = LetterStatus.NO_SPOT;
                }
            } else {
                statuses[i] = LetterStatus.NO_SPOT;
            }
        }

        return statuses;
    }
}
