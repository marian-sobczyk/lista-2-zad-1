package com.marian;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by marian on 19.10.15.
 */
public class CipherReader {
    public static String getCipherFromFile(int fileIndex) throws IOException {
        String fileName = getFileNameForIndex(fileIndex);

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        String allText = sb.toString();

        return allText;
    }

    private static String getFileNameForIndex(int fileIndex) {
        String fileName = null;
        if (fileIndex > 0) {
            fileName = "c" + fileIndex + ".txt";
        } else {
            fileName = "c.txt";
        }
        return fileName;
    }
}
