package com.javarush.island.animal.predator;

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.*;
import com.javarush.island.animal.omnivorous.Boar;
import com.javarush.island.animal.omnivorous.Duck;
import com.javarush.island.animal.omnivorous.Mouse;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

import java.util.Map;

/*
 * Wolf хищник
 */

public class Wolf extends Predator {

    private static final double WEIGHT = 50;
    private static final double MAX_SATIETY = 8;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(
            Horse.class, 10, Deer.class, 15, Rabbit.class, 60, Mouse.class, 80,
            Goat.class, 60, Sheep.class, 70, Boar.class, 15, Buffalo.class, 10, Duck.class, 40);
    private static final int SPEED = 3;


    public Wolf() {
        super(WEIGHT, MAX_SATIETY, EATING_PROBABILITIES, SPEED);
    }

    @Override
    public void move(Island island, int currentX, int currentY) {
        //todo Пока заглушка
    }

    @Override
    public void reproduce(Location location) {
        //todo Пока заглушка
    }
}
