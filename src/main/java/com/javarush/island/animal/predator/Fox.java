package com.javarush.island.animal.predator;

/*
 * Fox хищник
 */

import com.javarush.island.animal.Animal;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

import java.util.Map;

public class Fox extends Predator {
    private static final double WEIGHT = 8;
    private static final double MAX_SATIETY = 2;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(); //todo добавить сущности, кого ест волк


    public Fox() {
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
