package ru.mai;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker();
        Algorithm algorithm = new Algorithm(fileWorker.getAllStringsInFile(), fileWorker);
        algorithm.start();
    }
}
