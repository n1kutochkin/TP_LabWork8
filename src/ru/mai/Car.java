package ru.mai;

import java.util.ArrayList;
import java.util.Objects;


//TODO убрать интерфейс сравнения, так как все равно нет необходимости сравнивать между собой объекты
class Car implements Comparable<Car> {

    public static final byte BRAND = 1;
    public static final byte COST = 2;
    private Long averageCost;
    private String brand;
    private ArrayList<Long> costs;

    Car(String brand, Long cost) {
        this.brand = brand;
        this.costs = new ArrayList<>();
        this.costs.add(cost);
        this.averageCost = Long.valueOf(0);
    }

    //TODO убрать этот конструктор, чтобы не пользоваться им для поиска сущностей по сет - реализовать ключ-значение

    public String getBrand() {
        return brand;
    }

    public Long getAverageCost() {
        return averageCost;
    }

    public void addPrice(long cost) {
        costs.add(cost);
    }

    public void calculateAverageCost() {
        for (Long cost : costs) {
            averageCost += cost;
        }
        averageCost = (long) Math.floor(averageCost / costs.size());
    }

    @Override
    public int compareTo(Car o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return brand.equals(car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand);
    }
}