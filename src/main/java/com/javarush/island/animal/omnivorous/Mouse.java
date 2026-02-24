package com.javarush.island.animal.omnivorous;

/*
 * Mouse всеядное
 */

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.Caterpillar;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

import java.util.Map;

public class Mouse extends Omnivorous {
    private static final double WEIGHT = 0.05;
    private static final double MAX_SATIETY = 0.01;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(Caterpillar.class, 90);
    private static final int SPEED = 1;


    public Mouse() {
        super(WEIGHT, MAX_SATIETY, EATING_PROBABILITIES, SPEED);
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
