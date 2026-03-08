package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

/*
 * Rabbit травоядное
 */

public class Rabbit extends Herbivore {

    private static final double WEIGHT = 2;
    private static final double MAX_SATIETY = 0.45;
    private static final int SPEED = 2;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 150;

    public Rabbit() {
        super(WEIGHT, MAX_SATIETY, SPEED, MAX_NUMBER_OF_ANIMALS_PER_CELL);
    }

}
