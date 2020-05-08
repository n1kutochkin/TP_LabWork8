package ru.mai;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class FileWorker {

    public BufferedReader in;
    public Writer out;

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    FileWorker() {
        in = makeReader();
        out = makeWriter();
    }

    public BufferedReader getIn() {
        return in;
    }

    public Writer getOut() {
        return out;
    }

    public static BufferedReader makeReader() {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                Files.createFile(Paths.get(INPUT_FILE));
                return new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE), StandardCharsets.UTF_8));
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public static Writer makeWriter() {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE),StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                Files.createFile(Paths.get(OUTPUT_FILE));
                return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE),StandardCharsets.UTF_8));
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public HashSet<String> getAllStringsInFile() {
        HashSet<String> buffSet = new HashSet<>();
        String line;

        try {
            while ((line = in.readLine()) != null) {
                buffSet.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return buffSet;
    }
}
