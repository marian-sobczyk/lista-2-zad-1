package com.marian;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Ciphertext> ciphertexts;
    private static Ciphertext ciphertextToDecode;
    private static int maxCiphertextLength;
    private static DecryptedMessage message;

    public static void main(String[] args) {

        ciphertexts = new ArrayList<Ciphertext>();

        try {
            parseTexts();
        } catch (IOException e) {
            e.printStackTrace();
        }

        message = new DecryptedMessage(ciphertextToDecode.getLength());

        filterCharacters((char) 32, 33);
        filterCharacters((char) 40, 41);
        filterCharacters((char) 44, 44);
        filterCharacters((char) 46, 46);
        filterCharacters((char) 48, 59);
        filterCharacters((char) 65, 90);
        filterCharacters((char) 97, 122);

        for (int i = 0; i < message.getLength(); i++) {
            message.printLettersAtIndex(i);
            System.out.println();
        }

    }

    private static void filterCharacters(char from, int to) {
        for (char currentCharacter = from; currentCharacter <= to; currentCharacter++) {
            for (int i = 0; i < ciphertextToDecode.getLength(); i++) {
                boolean validCharacters = true;
                int j;
                for (j = 0; j < ciphertexts.size(); j++) {
                    Ciphertext ciphertext = ciphertexts.get(j);
                    try {
                        char currentCipher = ciphertext.getCharacterAtIndex(i);
                        char decodedCipher = ciphertextToDecode.getCharacterAtIndex(i);
                        char xored = (char) (currentCipher ^ decodedCipher);
                        xored = (char) (xored ^ currentCharacter);
                        validCharacters = validCharacters && isValidCharacter(xored);

                    } catch (RequestedCharacterNotExistExteption requestedCharacterNotExistExteption) {
                    }
                }
                if (validCharacters && j > 0) {
                    message.addValidCharacterAtIndex(currentCharacter, i);
                }
                System.out.println();

            }
        }
    }

    private static void updateMaxCiphertextLength(int ciphertextLength) {
        if (ciphertextLength > maxCiphertextLength) {
            maxCiphertextLength = ciphertextLength;
        }
    }

    private static void parseTexts() throws IOException {
        for (int i = 1; i <= 20; i++) {
            String text = CipherReader.getCipherFromFile(i);
            Ciphertext ciphertext = new Ciphertext(text);
            ciphertexts.add(ciphertext);
            updateMaxCiphertextLength(ciphertext.getLength());
        }
        String text = CipherReader.getCipherFromFile(0);
        ciphertextToDecode = new Ciphertext(text);
        updateMaxCiphertextLength(ciphertextToDecode.getLength());
    }

    private static boolean isValidCharacter(char character) {
        if (character >= 32 && character <= 33)
            return true;
        if (character >= 40 && character <= 41)
            return true;
        if (character == 44)
            return true;
        if (character == 46)
            return true;
        if (character >= 48 && character <= 59)
            return true;
        if (character >= 65 && character <= 90)
            return true;
        if (character >= 97 && character <= 122)
            return true;

        return false;
    }
}
