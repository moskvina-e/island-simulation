package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

/*
 * Sheep травоядное
 */

public class Sheep extends Herbivore {

    private static final double WEIGHT = 70;
    private static final double MAX_SATIETY = 15;
    private static final int SPEED = 3;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 140;

    public Sheep() {
        super(WEIGHT, MAX_SATIETY, SPEED, MAX_NUMBER_OF_ANIMALS_PER_CELL);
    }

}
