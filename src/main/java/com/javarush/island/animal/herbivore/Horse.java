package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

/*
 * Horse травоядное
 */

public class Horse extends Herbivore {
    private static final double WEIGHT = 400;
    private static final double MAX_SATIETY = 60;

    public Horse() {
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
