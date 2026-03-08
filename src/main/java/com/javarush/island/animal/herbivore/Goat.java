package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

/*
 * Goat травоядное
 */

public class Goat extends Herbivore {

    private static final double WEIGHT = 60;
    private static final double MAX_SATIETY = 10;
    private static final int SPEED = 3;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 140;

    public Goat() {
        super(WEIGHT, MAX_SATIETY, SPEED, MAX_NUMBER_OF_ANIMALS_PER_CELL);
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
