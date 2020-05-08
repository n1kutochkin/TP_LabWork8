package ru.mai;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
//TODO Проставить везде модификаторы доступа для всех полей всех классов
public class Main {

    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker();
        //TODO Убрать передачу файл-воркера в алгоритм
        Algorithm algorithm = new Algorithm(fileWorker.getAllStringsInFile(), fileWorker);
        algorithm.start();
    }
}
