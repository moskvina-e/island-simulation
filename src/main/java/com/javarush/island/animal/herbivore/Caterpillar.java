package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

/*
 * Caterpillar травоядное
 */

public class Caterpillar extends Herbivore {

    private static final double WEIGHT = 0.01;
    private static final double MAX_SATIETY = 0;

    public Caterpillar() {
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
