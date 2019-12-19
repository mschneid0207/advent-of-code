package de.mschneid.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<String> readLines(String fileName) {
        FileReader fileReader = new FileReader();
        List<String> lines = new ArrayList<String>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new java.io.FileReader(fileReader.getFile(fileName)));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static String readLine(String fileName) {
        FileReader fileReader = new FileReader();
       String line = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new java.io.FileReader(fileReader.getFile(fileName)));
             line = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private File getFile(String fileName) {
        File file = new File(
                getClass().getClassLoader().getResource(fileName).getFile()
        );
        return file;
    }
}
