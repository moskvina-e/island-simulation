package com.javarush.island.animal.omnivorous;

/*
 * Duck всеядное
 */

import com.javarush.island.animal.Animal;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

import java.util.Map;

public class Duck extends Omnivorous{
    private static final double WEIGHT = 1;
    private static final double MAX_SATIETY = 0.15;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(); //todo добавить сущности, кого ест волк


    public Duck() {
        super(WEIGHT, MAX_SATIETY, EATING_PROBABILITIES);
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
