package ru.mai;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

/**
 * Класс работы с файлами ввода и вывода
 */
public class FileWorker {

    public Optional<BufferedReader> in;
    public Optional<Writer> out;
    private static MyLogger logger = new MyLogger(FileWorker.class.getName());

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    /**
     * Стандратный конструктор для класса. Работает с файлами с названием <b>input.txt</b> и <b>output.txt</b>
     */
    public FileWorker() throws FileWorkerException {
        in = makeReader();
        out = makeWriter();

        if (!in.isPresent() || !out.isPresent()) {
            throw new FileWorkerException();
        }
    }

    /**
     * Конструктор класса для работы с произвольными файлами ввода и вывода данных
     *
     * @param inputFileName  название входящего текстового файла
     * @param outputFileName название исходящего текстового файла
     */
    public FileWorker(String inputFileName, String outputFileName) throws FileWorkerException {
        in = makeReader(inputFileName);
        out = makeWriter(outputFileName);

        if (!in.isPresent() || !out.isPresent()) {
            throw new FileWorkerException();
        }
    }

    public Optional<BufferedReader> getIn() {
        return in;
    }

    public Optional<Writer> getOut() {
        return out;
    }


    private static Optional<BufferedReader> makeReader() {
        return makeReader(INPUT_FILE);
    }

    /**
     * создание класса читателя из файла
     *
     * @param inputFileName название файла
     *
     * @return класс чтения
     */
    private static Optional<BufferedReader> makeReader(String inputFileName) {
        try {
            return Optional.of(new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "Cp1251")));
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
            try {
                Files.createFile(Paths.get(inputFileName));
                return Optional.of(new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "Cp1251")));
            } catch (IOException ex) {
                logger.log(Level.SEVERE, e.getMessage());
                return Optional.empty();
            }
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<Writer> makeWriter() {
        return makeWriter(OUTPUT_FILE);
    }

    /**
     * Создание класса записи
     *
     * @param outputFileName название файла вывода
     *
     * @return буфферизированный класс вывода
     */
    public Optional<Writer> makeWriter(String outputFileName) {
        try {
            return Optional.of(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8)));
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
            try {
                Files.createFile(Paths.get(outputFileName));
                return Optional.of(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8)));
            } catch (IOException ex) {
                logger.log(Level.SEVERE, e.getMessage());
                return Optional.empty();
            }
        }
    }

    /**
     * Взятие всех строк файла в массив-список в виде строк
     *
     * @return массив-список со строками из файла
     */
    public Optional<ArrayList<String>> getAllStringsInFile() {
        ArrayList<String> buffSet = new ArrayList<>();
        String line;

        try {
            while ((line = in.get().readLine()) != null) {
                buffSet.add(line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return Optional.empty();
        }

        if (buffSet.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(buffSet);
        }
    }

    /**
     * Вывод всех данных из списка состоящих из элементов, подобных строкам
     *
     * @param data - список элементов-данных, подобных строкам, на вывод
     */
    public void printData(List<String> data) {
        for (String out : data) {
            try {
                this.out.get().write(out);
                this.out.get().write("\n");
                this.out.get().flush();
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    private class FileWorkerException extends Exception {

        FileWorkerException() {
            super("Ошибка при работе с файлами. Смотрите лог-файл класса FileWorker.");
        }
    }

    private class FileWorkerUnsupportedEncodingException extends UnsupportedEncodingException {

        FileWorkerUnsupportedEncodingException() {
            super("Файл, который пытаются прочесть или использовать для записи в неверной кодировке");
        }
    }

    private class FileWorkerIOException extends IOException {

        FileWorkerIOException(String whatHappened) {
            super("Произошла ошибка при чтении/записи файла ввода/вывода");
        }
    }

    private class FileWorkerFileNotFoundException extends FileNotFoundException {

        FileWorkerFileNotFoundException() {
            super("Файл, который указан к чтению/записи не найден. Создаем этот файл.");
        }
    }
}



