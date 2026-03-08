package com.javarush.island.animal.omnivorous;

/*
 * Boar всеядное
 */

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.Caterpillar;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

import java.util.Map;

public class Boar extends Omnivorous{

    private static final double WEIGHT = 400;
    private static final double MAX_SATIETY = 50;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(Mouse.class, 50, Caterpillar.class, 90);
    private static final int SPEED = 2;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 50;


    public Boar() {
        super(WEIGHT, MAX_SATIETY, EATING_PROBABILITIES, SPEED, MAX_NUMBER_OF_ANIMALS_PER_CELL);
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
