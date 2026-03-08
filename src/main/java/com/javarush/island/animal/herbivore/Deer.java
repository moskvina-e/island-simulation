package com.javarush.island.animal.herbivore;

/*
 * Deer травоядное
 */

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

public class Deer extends Herbivore {

    private static final double WEIGHT = 300;
    private static final double MAX_SATIETY = 50;
    private static final int SPEED = 4;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 20;

    public Deer() {
        super(WEIGHT, MAX_SATIETY, SPEED,  MAX_NUMBER_OF_ANIMALS_PER_CELL);
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
