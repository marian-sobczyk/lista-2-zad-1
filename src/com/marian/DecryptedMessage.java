package com.marian;

import java.util.ArrayList;

/**
 * Created by marian on 20.10.15.
 */
public class DecryptedMessage {
    private final ArrayList<DecryptedLetter> decryptedLetters;

    public DecryptedMessage(int length) {
        decryptedLetters = new ArrayList<DecryptedLetter>();
        for (int i = 0; i < length; i++) {
            DecryptedLetter letter = new DecryptedLetter();
            decryptedLetters.add(letter);
        }
    }

    public void addValidCharacterAtIndex(char currentCharacter, int i) {
        if (i < decryptedLetters.size()) {
            DecryptedLetter decryptedLetter = decryptedLetters.get(i);
            decryptedLetter.addCharacter(currentCharacter);
        }
    }

    public int getLength() {
        return decryptedLetters.size();
    }

    public void printLettersAtIndex(int i) {
        DecryptedLetter decryptedLetter = decryptedLetters.get(i);
        decryptedLetter.printPossibleCharacters();
    }
}
