package com.marian;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Ciphertext> ciphertexts;
    private static ArrayList<DecryptedMessage> messages;
    private static Ciphertext ciphertextToDecode;

    public static void main(String[] args) {

        ciphertexts = new ArrayList<Ciphertext>();

        try {
            parseTexts();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messages = new ArrayList<DecryptedMessage>();
        createMessages();

    }

    private static void createMessages() {
        for (Ciphertext ciphertext : ciphertexts) {
            DecryptedMessage message = new DecryptedMessage(ciphertext.getLength());
            messages.add(message);
        }
    }

    private static void parseTexts() throws IOException {
        for (int i = 0; i < 20; i++) {
            String text = CipherReader.getCipherFromFile(i);
            Ciphertext ciphertext = new Ciphertext(text);
            ciphertexts.add(ciphertext);
        }
        String text = CipherReader.getCipherFromFile(20);
        ciphertextToDecode = new Ciphertext(text);
    }
}
