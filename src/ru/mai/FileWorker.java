package ru.mai;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.ArrayList;

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

    //TODO Сделать верные кодировки
    public static BufferedReader makeReader() {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE), "Cp1251"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                Files.createFile(Paths.get(INPUT_FILE));
                return new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE), "Cp1251"));
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Writer makeWriter() {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE),StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                Files.createFile(Paths.get(OUTPUT_FILE));
                return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE), StandardCharsets.UTF_8));
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public ArrayList<String> getAllStringsInFile() {
        ArrayList<String> buffSet = new ArrayList<>();
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

    public void printData(ArrayList<String> data) {
        for (String out : data) {
            try {
                this.out.write(out);
                this.out.write("\n");
                this.out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO Сделать метод вывода всего стека информации из алгоритма в потока/файла/списка строк
}
