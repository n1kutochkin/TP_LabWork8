package ru.mai;

import java.util.ArrayList;
import java.util.Objects;

class Car implements Comparable<Car> {
    public static final byte BRAND = 1;
    public static final byte COST = 2;
    Long averageCost;
    String brand;
    ArrayList<Long> costs;

    Car(String brand, Long cost) {
        this.brand = brand;
        this.costs = new ArrayList<>();
        this.costs.add(cost);
        this.averageCost = Long.valueOf(0);
    }

    Car(String brand) {
        this.brand = brand;
//        this.cost = null;
    }

    public void addPrice(long cost) {
        costs.add(cost);
    }

    public long calculateAverageCost() {
        for (Long cost : costs ) {
            averageCost += cost;
        }
        return averageCost = (long) Math.floor(averageCost / costs.size());
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