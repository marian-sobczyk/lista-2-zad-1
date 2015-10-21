package com.marian;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by marian on 20.10.15.
 */
public class DecryptedLetter {

    private final Set<Character> letters;

    DecryptedLetter() {
        letters = new HashSet<Character>();
    }

    public void addCharacter(char currentCharacter) {
        letters.add(currentCharacter);
    }

    public void printPossibleCharacters() {
        for (Character character : letters) {
            System.out.print(character);
            System.out.print(" ");
        }
    }
}
