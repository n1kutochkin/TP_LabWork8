package ru.mai;

import java.util.*;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс Алгоритм - содержит логику и вычисления.
 *
 * @author n1kutochkin
 */
public class Algorithm {

    private static Pattern pattern = Pattern.compile("\\\"([А-Яа-яa-zA-z]+)\\\":\\\"(\\d+)\\\"");
    private Scanner in = new Scanner(System.in);
    private static final String NO_CARS = "Автомобилей введенной марки не найдено";
    private static final String AVERAGE_COST_IS = "Средняя стоимость машин марки ";
    private Matcher matcher;
    private ArrayList<String> data;
    private ArrayList<String> outputData;
    private MyLogger logger;

    /**
     * Конструртора класса, создающий сущность класса. При его создании будет сразу вызван процесс считывания запросов.
     *
     * @param data список с данными строкового типа в формате <b>"название бренда авто":"стоимость"</b>*
     */
    public Algorithm(List<String> data) throws Exception {

        this.logger = new MyLogger(Algorithm.class.getName());

        if (data.isEmpty()) {
            throw new AlgorithmNothingToProcess();
        } else {
            this.data = (ArrayList<String>) data;
        }

        this.start();
    }

    /**
     * Запуск считывания запросов в клавиатуры
     */
    public void start() {
        HashMap<String, Car> carBase = generateCarBase();
        outputData = new ArrayList<>();

        while (in.hasNextLine() && !in.hasNext("~")) {
            String buffKey = in.nextLine();

            if (carBase.containsKey(buffKey)) {
                Car buffCar = carBase.get(buffKey);
                outputData.add(generatePhrase(buffCar.getBrand(), buffCar.getAverageCost()));
            } else {
                outputData.add(NO_CARS);
            }
        }
    }

    /**
     * @return список с информацией после запросов
     */
    public List<String> getOutputData() {
        return outputData;
    }

    /**
     * создает фразу информации о средней стоимости авто определенного бренда
     * @param brand название бренда
     * @param averageCost средняя стоимость
     * @return фразу информации о средней стоимисти автомобилей бренда
     */
    private String generatePhrase(String brand, Long averageCost) {
        StringBuilder buffStr = new StringBuilder(brand);
        buffStr.insert(0, '<').insert(buffStr.length(), '>');
        return AVERAGE_COST_IS + buffStr + " " + averageCost;
    }

    /**
     * Метод создания словаря с ключями из брендов и значениями-объектами класса Car
     * @return словарь бренд-машины бренда
     * @throws WrongInputException в случае строки, несоответсвующей формату <p> <b>"название бренда":"стоимость"</b>
     */
    private HashMap<String, Car> generateCarBase() {
        HashMap<String, Car> carBase = new HashMap<>();
        Integer numberOfLine = 1;

        for (String str : data) {
            matcher = pattern.matcher(str);
            try {
                if (matcher.find()) {
                    String buffKey = matcher.group(Car.BRAND);

                    if (carBase.containsKey(buffKey)) {
                        carBase.get(buffKey).addPrice(Long.parseLong(matcher.group(Car.COST)));
                    } else {
                        carBase.put(buffKey, new Car(buffKey, Long.parseLong(matcher.group(Car.COST))));
                    }
                } else {
                    throw new WrongInputException(str, numberOfLine);
                }
            } catch (WrongInputException e) {
                logger.log(Level.WARNING, e.getMessage());
            } finally {
                ++numberOfLine;
            }
        }

        return carBase;
    }

    /**
     * Класс-исключение неверного ввода
     */
    private class WrongInputException extends InputMismatchException {

        public WrongInputException() {
            super();
        }

        /**
         * Создает сущность класса
         *
         * @param line         содержимое строки
         * @param numberOfLine номер самой строки в файле
         */
        public WrongInputException(final String line, final Integer numberOfLine) {
            super("Некорректные данные: " + line + "\t Строка файла: " + numberOfLine);
        }
    }

    /**
     * Класс-исключение при отсутствии данных для обработки алгоритмом
     */
    private static class AlgorithmNothingToProcess extends NullPointerException {

        AlgorithmNothingToProcess() {
            super("Нет данных для обработки");
        }
    }
}
