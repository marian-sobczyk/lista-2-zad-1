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
}
