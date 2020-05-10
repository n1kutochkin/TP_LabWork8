package ru.mai;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm {

    private static Pattern pattern = Pattern.compile("\\\"([А-Яа-яa-zA-z]+)\\\":\\\"(\\d+)\\\"");
    private Scanner in = new Scanner(System.in);
    private static final String NO_CARS = "Автомобилей введенной марки не найдено";
    private static final String AVERAGE_COST_IS = "Средняя стоимость машин марки ";
    private Matcher matcher;
    private ArrayList<String> data;
    private ArrayList<String> outputData;
    private Logger logger;


    public Algorithm(List<? extends String> data) {
        this.data = (ArrayList<String>) data;
        this.logger = MyLogger.getMyLogger(Algorithm.class.getName());
    }

    public void start() {
        HashMap<String, Car> carBase = generateCarBase();
        outputData = new ArrayList<>();

        while (in.hasNextLine() && !in.hasNext("~")) {
            String buffKey = in.nextLine();

            if (carBase.containsKey(buffKey)) {
                Car buffCar = carBase.get(buffKey);
                buffCar.calculateAverageCost();
                outputData.add(generatePhrase(buffCar.getBrand(), buffCar.getAverageCost()));
            } else {
                outputData.add(NO_CARS);
            }
        }
    }


    public List<? extends String> getOutputData() {
        return outputData;
    }

    private String generatePhrase(String brand, Long averageCost) {
        StringBuilder buffStr = new StringBuilder(brand);
        buffStr.insert(0, '<').insert(buffStr.length(), '>');
        return AVERAGE_COST_IS + buffStr + " " + averageCost;
    }

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
            } catch (InputMismatchException e) {
                logger.log(Level.WARNING, e.getMessage());
            } finally {
                ++numberOfLine;
            }
        }

        return carBase;
    }

    private class WrongInputException extends InputMismatchException {

        public WrongInputException() {
            super();
        }

        public WrongInputException(final String line, final Integer numberOfLine) {
            super("Некорректные данные: " + line + "\t Строка файла: " + numberOfLine);
        }
    }
}
