package ru.mai;

import java.util.HashSet;

//TODO Убрать весь класс за ненадобностью, если только не увелится функционал
//TODO Возможно, изменить структуру данных для хранения
class CarBase {
    HashSet<Car> cars;

    CarBase() {
        cars = new HashSet<>();
    }

    public HashSet<Car> getCars() {
        return cars;
    }
}