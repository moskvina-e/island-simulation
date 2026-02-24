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

    public Sheep() {
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
