package com.javarush.island.animal.herbivore;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;
import lombok.Getter;

/*
 * Buffalo травоядное
 */
@Getter
public class Buffalo extends Herbivore {

    private static final double WEIGHT = 700;
    private static final double MAX_SATIETY = 100;
    private static final int SPEED = 3;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 10;

    public Buffalo() {
        super(WEIGHT, MAX_SATIETY, SPEED, MAX_NUMBER_OF_ANIMALS_PER_CELL);
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
