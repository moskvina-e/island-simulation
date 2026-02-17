package com.javarush.island.model;

import com.javarush.island.animal.Animal;

import java.util.ArrayList;
import java.util.List;
/**
* Класс Локация содержит списки животных и растений
* Метод removePlant удаляет последнее растение (без синхронизации в еврсии 1.0)
*/
public class Location {

    private final List<Animal> animals = new ArrayList<Animal>();

    private final List<Plant> plants = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    //Для однопоточной версии простое удаление растения
    public List<Plant> removePlant() {
        if(!plants.isEmpty()) {
            return plants.remove(plants.size() - 1);
        }
        return null;
    }
}
