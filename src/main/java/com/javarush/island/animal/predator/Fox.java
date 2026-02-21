package com.javarush.island.animal.predator;

/*
 * Fox хищник
 */

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.Caterpillar;
import com.javarush.island.animal.herbivore.Rabbit;
import com.javarush.island.animal.omnivorous.Duck;
import com.javarush.island.animal.omnivorous.Mouse;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

import java.util.Map;

public class Fox extends Predator {
    private static final double WEIGHT = 8;
    private static final double MAX_SATIETY = 2;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(
            Rabbit.class, 70, Mouse.class, 90, Duck.class, 60, Caterpillar.class, 40);


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
