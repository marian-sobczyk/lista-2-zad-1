package com.marian;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            for (int i = 0; i <= 20; i++) {
                String text = CipherReader.getCipherFromFile(i);
                System.out.println(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
