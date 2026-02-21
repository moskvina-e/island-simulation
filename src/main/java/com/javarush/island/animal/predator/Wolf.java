package com.javarush.island.animal.predator;

import com.javarush.island.animal.Animal;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Wolf хищник
 */
@Slf4j
public class Wolf extends Predator {

    private static final double WEIGHT = 50;
    private static final double MAX_SATIETY = 8;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(); //todo добавить сущности, кого ест волк


    public Wolf() {
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
