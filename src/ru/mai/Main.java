package ru.mai;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
//TODO Проставить везде модификаторы доступа для всех полей всех классов
//TODO Прописать документацию по JavaDoc во всем проекте

/**
 * @author n1kutochkin
 * @version 0.1
 */
public class Main {

    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker();
        Algorithm algorithm = new Algorithm(fileWorker.getAllStringsInFile());
        algorithm.start();
        fileWorker.printData(algorithm.getOutputData());
    }
}
