package com.marian;

import java.util.ArrayList;

/**
 * Created by marian on 20.10.15.
 */
public class Ciphertext {
    private final ArrayList<Character> characters;

    public Ciphertext(String text) {
        String[] numbers = text.split(" ");
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i = 0; i < numbers.length; i++) {
            String number = numbers[i];
            if (number.length() >= 8) {
                char character = (char) Integer.parseInt(number, 2);
                list.add(character);
            }
        }
        this.characters = list;
    }

    public int getLength() {
        return characters.size();
    }

    public char getCharacterAtIndex(int i) throws RequestedCharacterNotExistExteption {
        if (i < characters.size()) {
            return characters.get(i);
        }

        throw new RequestedCharacterNotExistExteption();
    }
}
