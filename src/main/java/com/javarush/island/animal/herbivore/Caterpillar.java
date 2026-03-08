package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

/*
 * Caterpillar травоядное
 */

public class Caterpillar extends Herbivore {

    private static final double WEIGHT = 0.01;
    private static final double MAX_SATIETY = 0.001; //задаю макс. сытость гусеницы как десятую часть ее веса по аналогии с др. животными
    private static final int SPEED = 0;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 1000;

    public Caterpillar() {
        super(WEIGHT, MAX_SATIETY, SPEED, MAX_NUMBER_OF_ANIMALS_PER_CELL);
    }

}
