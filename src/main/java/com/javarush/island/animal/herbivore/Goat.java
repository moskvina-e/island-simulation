package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

/*
 * Goat травоядное
 */

public class Goat extends Herbivore {

    private static final double WEIGHT = 60;
    private static final double MAX_SATIETY = 10;

    public Goat() {
        super(WEIGHT, MAX_SATIETY);
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
