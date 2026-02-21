package com.javarush.island.animal.predator;

/*
 * Eagle хищник
 */

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.Caterpillar;
import com.javarush.island.animal.herbivore.Rabbit;
import com.javarush.island.animal.omnivorous.Duck;
import com.javarush.island.animal.omnivorous.Mouse;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

import java.util.Map;

public class Eagle extends Predator {

    private static final double WEIGHT = 6;
    private static final double MAX_SATIETY = 1;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(
            Fox.class, 10, Rabbit.class, 90, Mouse.class, 90, Duck.class, 80);


    public Eagle() {
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
