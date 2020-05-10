package ru.mai;

import java.util.ArrayList;
import java.util.Objects;


class Car implements Comparable<Car> {

    public static final byte BRAND = 1;
    public static final byte COST = 2;
    private Long averageCost;
    private String brand;
    private ArrayList<Long> costs;

    public Car(String brand, Long cost) {
        this.brand = brand;
        this.costs = new ArrayList<>();
        this.costs.add(cost);
        this.averageCost = Long.valueOf(0);
    }

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
        return this.brand.compareTo(o.brand);
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