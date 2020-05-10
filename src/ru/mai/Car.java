package ru.mai;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Класс Машин бренда
 *
 * @author n1kutochkin
 */
class Car implements Comparable<Car> {

    public static final byte BRAND = 1;
    public static final byte COST = 2;
    private Long averageCost;
    private String brand;
    private ArrayList<Long> costs;
    private Long sum;

    /**
     * Создает класс с указанием первой стоимости машины бренда
     * @param brand - бренд машин
     * @param cost - стоимсоть первой машины
     */
    public Car(String brand, Long cost) {
        this.brand = brand;
        this.costs = new ArrayList<>();
        this.costs.add(cost);
        this.averageCost = Long.valueOf(cost);
        this.sum = Long.valueOf(cost);
    }

    /**
     *
     * @return бренд сущности
     */
    public String getBrand() {
        return brand;
    }

    public Long getAverageCost() {
        return averageCost;
    }

    /**
     * Добавляет стоимость еще одной машины данного бренда
     * @param cost стоимость добавляемой машины
     */
    public void addPrice(long cost) {
        costs.add(cost);
        sum += cost;
        averageCost = (long) Math.floor(sum / costs.size());
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