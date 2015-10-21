package com.marian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static ArrayList<Ciphertext> ciphertexts;
    private static Ciphertext ciphertextToDecode;
    private static int maxCiphertextLength;
    private static DecryptedMessage message;
    private static char[] validCharacters = {32, 33, 34, 44, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
    private static char[] charactersToSearch = {32, 33, 44, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 82, 83, 84, 85, 87, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 114, 115, 116, 117, 119, 121, 122};

    public static void main(String[] args) {

        ciphertexts = new ArrayList<Ciphertext>();

        try {
            parseTexts();
        } catch (IOException e) {
            e.printStackTrace();
        }

        message = new DecryptedMessage(ciphertextToDecode.getLength());

        filterCharacters();

        for (int i = 0; i < message.getLength(); i++) {
            message.printLettersAtIndex(i);
            System.out.println();
        }

    }

    private static void filterCharacters() {
        for (int index = 0; index < charactersToSearch.length; index++) {
            char currentCharacter = charactersToSearch[index];
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
        return Arrays.binarySearch(validCharacters, character) >= 0;
    }
}
