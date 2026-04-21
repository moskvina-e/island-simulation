package com.javarush.island.animal.predator;

/*
 * Bear хищник
 */

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.*;
import com.javarush.island.animal.omnivorous.Boar;
import com.javarush.island.animal.omnivorous.Duck;
import com.javarush.island.animal.omnivorous.Mouse;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;

import java.util.Map;

public class Bear extends Predator {

    private static final double WEIGHT = 500;
    private static final double MAX_SATIETY = 80;
    private static final Map<Class<? extends Animal>, Integer> EATING_PROBABILITIES = Map.of(
            Boa.class, 80,Horse.class, 40, Deer.class, 80, Rabbit.class, 80, Mouse.class, 90,
            Goat.class, 70, Sheep.class, 70, Boar.class, 50, Buffalo.class, 20, Duck.class, 10);
    private static final int SPEED = 2;
    public static final int MAX_NUMBER_OF_ANIMALS_PER_CELL = 5;


    public Bear() {
        super(WEIGHT, MAX_SATIETY, EATING_PROBABILITIES, SPEED,   MAX_NUMBER_OF_ANIMALS_PER_CELL);
    }

}
