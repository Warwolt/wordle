package com.wordle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (guess.length() != secretWord.length()) {
            throw new IllegalArgumentException();
        }

        int[] numMatches = new int[Character.MAX_VALUE];
        ArrayList<Optional<LetterStatus>> statuses;
        statuses = new ArrayList<>(Collections.nCopies(guess.length(), Optional.empty()));

        // check for exact matches
        for (int i = 0; i < guess.length(); i++) {
            char currentLetter = guess.charAt(i);
            if (currentLetter == secretWord.charAt(i)) {
                statuses.set(i, Optional.of(LetterStatus.CORRECT_SPOT));
                numMatches[currentLetter] += 1;
            }
        }

        // check if in wrong spot or has no spot
        for (int i = 0; i < guess.length(); i++) {
            char currentLetter = guess.charAt(i);
            if (!statuses.get(i).isPresent()) {
                if (numMatches[currentLetter] < letterOccurrences[currentLetter]) {
                    statuses.set(i, Optional.of(LetterStatus.WRONG_SPOT));
                    numMatches[currentLetter] += 1;
                } else {
                    statuses.set(i, Optional.of(LetterStatus.NO_SPOT));
                }
            }
        }

        // flatten to just an array and return it
        return statuses
            .stream()
            .map(e -> e.get())
            .collect(Collectors.toList())
            .toArray(new LetterStatus[guess.length()]);
    }
}
