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

    public Rabbit() {
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
