package com.marian;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Ciphertext> ciphertexts;

    public static void main(String[] args) {

        ciphertexts = new ArrayList<Ciphertext>();

        try {
            for (int i = 0; i <= 20; i++) {
                String text = CipherReader.getCipherFromFile(i);
                Ciphertext ciphertext = new Ciphertext(text);
                ciphertexts.add(ciphertext);
                System.out.println(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
