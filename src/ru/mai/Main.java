package ru.mai;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
//TODO Проставить везде модификаторы доступа для всех полей всех классов
//TODO Прописать документацию по JavaDoc во всем проекте

/**
 * <head>Лабораторная работа №8</head> <p></p>
 * <p>
 * Класс точки входа в программу <b>Работа о струтурированными данными в файлах</b>.
 * В папке проекта должны быть файлы <b>input.txt</b>, в котором хранятся данные об авто (его бренд, case sensitive)
 * и цена, и <b>output.txt</b>, в котором будет информация по вашим запросам, заданным в консоли приложения.
 * <p>
 * Обратите внимание! Данные в файле <b>input.txt</b> хранится в формате:
 * "название бренда авто":"стоимость в условных единницах"
 * Одна машина - одна строка. Используйте Enter для перевода на следующую строку.
 * <p>
 * Запросом является название бренда автомобиля. Если данного бренда нет в файле input.txt - вам сообщат об этом.
 * <p>
 * Информация о запросе содержится значение средней стоимости машин бренда, заданого в запросе.
 *
 * @author n1kutochkin
 * @version 0.1
 */

public class Main {

    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker();
        Algorithm algorithm = new Algorithm(fileWorker.getAllStringsInFile());
        fileWorker.printData(algorithm.getOutputData());
    }
}
