package ru.mai;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm {
    static Pattern pattern = Pattern.compile("\\\"([А-Яа-яa-zA-z]+)\\\":\\\"(\\d+)\\\"");
    static Scanner in = new Scanner(System.in);
    private static final String NO_CARS = "Автомобилей введенной марки не найдено";
    private static final String AVERAGE_COST_IS = "Средняя стоимость машин марки ";
    Matcher matcher;
    //TODO Поменять структуру данных для хранения данных о машине, так как повторяющийся элемент - допустим
    HashSet<String> data;
    FileWorker fileWorker;

    Algorithm(HashSet<String> data, FileWorker fileWorker) {
        this.data = data;
        this.fileWorker = fileWorker;
    }

    public void start() {
        CarBase carBase = generateCarBase();


        while (in.hasNextLine() && !in.hasNext("~")) {
            Car car = new Car(in.nextLine());

            try {
                if (carBase.getCars().contains(car)) {
                    for (Car iter : carBase.getCars()) {
                        if (iter.equals(car)) {
                            fileWorker.getOut().write(generatePhrase(iter.brand, iter.calculateAverageCost()));
                            break;
                        }
                    }
                } else {
                    fileWorker.getOut().write(NO_CARS);
                }
                fileWorker.getOut().write("\n");
                fileWorker.getOut().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generatePhrase(String brand, Long averageCost) {
        StringBuilder buffStr = new StringBuilder(brand);
        buffStr.insert(0, '<').insert(buffStr.length(), '>');
        return AVERAGE_COST_IS + buffStr + " " + averageCost;
    }

    private CarBase generateCarBase() {
        CarBase carBase = new CarBase();

        for (String str : data) {
            matcher = pattern.matcher(str);
            while (matcher.find()) {
//                Car buffCar = new Car(matcher.group(Car.BRAND), Long.parseLong(matcher.group(Car.COST)));
                Car buffCar = new Car(matcher.group(Car.BRAND));

                if (carBase.getCars().contains(buffCar)) {
                    for (Car iter : carBase.getCars()) {
                        if (iter.equals(buffCar)) {
                            iter.addPrice(Long.parseLong(matcher.group(Car.COST)));
                        }
                    }
                } else {
                    carBase.getCars().add(new Car(matcher.group(Car.BRAND), Long.parseLong(matcher.group(Car.COST))));
                }
                carBase.getCars().add(buffCar);
            }
        }

        return carBase;
    }
}
