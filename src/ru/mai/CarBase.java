package ru.mai;

import java.util.HashSet;

class CarBase {
    HashSet<Car> cars;

    CarBase() {
        cars = new HashSet<>();
    }

    public HashSet<Car> getCars() {
        return cars;
    }
}