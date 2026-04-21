package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

/*
 * Horse травоядное
 */

public class Horse extends Herbivore {
    private static final double WEIGHT = 400;
    private static final double MAX_SATIETY = 60;
    private static final int SPEED = 4;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 20;

    public Horse() {
        super(WEIGHT, MAX_SATIETY, SPEED, MAX_NUMBER_OF_ANIMALS_PER_CELL);
    }

}
