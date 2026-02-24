package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

/*
 * Buffalo травоядное
 */

public class Buffalo extends Herbivore {

    private static final double WEIGHT = 700;
    private static final double MAX_SATIETY = 100;
    private static final int SPEED = 3;

    public Buffalo() {
        super(WEIGHT, MAX_SATIETY, SPEED);
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
