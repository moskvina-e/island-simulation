package com.javarush.island.animal.predator;

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.Rabbit;
import com.javarush.island.animal.omnivorous.Duck;
import com.javarush.island.animal.omnivorous.Mouse;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

import java.util.Map;

/*
 * Boa (удав) хищник
 */
public class Boa extends Predator {

    private static final double WEIGHT = 15;
    private static final double MAX_SATIETY = 3;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(
            Fox.class, 15, Rabbit.class, 20, Mouse.class, 40, Duck.class, 10);
    private static final int SPEED = 1;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 30;


    public Boa() {
        super(WEIGHT, MAX_SATIETY, EATING_PROBABILITIES, SPEED,  MAX_NUMBER_OF_ANIMALS_PER_CELL);
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
