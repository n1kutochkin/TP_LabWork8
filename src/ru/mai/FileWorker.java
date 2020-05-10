package ru.mai;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс работы с файлами ввода и вывода
 */
public class FileWorker {

    public BufferedReader in;
    public Writer out;

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    /**
     * Стандратный конструктор для класса. Работает с файлами с названием <b>input.txt</b> и <b>output.txt</b>
     */
    FileWorker() {
        in = makeReader();
        out = makeWriter();
    }

    /**
     * Конструктор класса для работы с произвольными файлами ввода и вывода данных
     *
     * @param inputFileName  название входящего текстового файла
     * @param outputFileName название исходящего текстового файла
     */
    FileWorker(String inputFileName, String outputFileName) {
        in = makeReader(inputFileName);
        out = makeWriter(outputFileName);
    }

    public BufferedReader getIn() {
        return in;
    }

    public Writer getOut() {
        return out;
    }


    private static BufferedReader makeReader() {
        return makeReader(INPUT_FILE);
    }

    /**
     * создание класса читателя из файла
     *
     * @param inputFileName название файла
     *
     * @return класс чтения
     */
    private static BufferedReader makeReader(String inputFileName) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "Cp1251"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                Files.createFile(Paths.get(inputFileName));
                return new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "Cp1251"));
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Writer makeWriter() {
        return makeWriter(OUTPUT_FILE);
    }

    /**
     * Создание класса записи
     *
     * @param outputFileName название файла вывода
     *
     * @return буфферизированный класс вывода
     */
    public Writer makeWriter(String outputFileName) {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                Files.createFile(Paths.get(outputFileName));
                return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8));
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Взятие всех строк файла в массив-список в виде строк
     *
     * @return массив-список со строками из файла
     */
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

    /**
     * Вывод всех данных из списка состоящих из элементов, подобных строкам
     *
     * @param data - список элементов-данных, подобных строкам, на вывод
     */
    public void printData(List<? extends String> data) {
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
}
