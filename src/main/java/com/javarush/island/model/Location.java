package com.javarush.island.model;

import com.javarush.island.animal.Animal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
* Класс Локация содержит списки животных и растений
*/
public class Location {

    @Getter
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Plant> plants = new CopyOnWriteArrayList<>();

    public boolean addAnimal(Animal animal) {
        //Проверка, есть ли в локации еще вакантное место для особи переданного вида
        long count = this.getAnimals()
                .stream()
                .filter(animalType -> animal.getClass().equals(animalType.getClass()))
                .count();
        if (count < animal.getMaxNumberOfAnimalsPerCell()) {
            animals.add(animal);
            animal.setCurrentLocation(this);
            return true;
        }
        return false;
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Plant removePlant() {
        synchronized (plants) {
            if (!plants.isEmpty()) {
                return plants.remove(plants.size() - 1);
            }
            return null;
        }
    }

    public List<Plant> getPlants() {
        return plants;
    }
}
