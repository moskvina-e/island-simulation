package com.javarush.island.animal;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;
import com.javarush.island.model.Plant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rabbit extends Animal {

    private static final double WEIGHT = 2;
    private static final double MAX_SATIETY = 0.45;

    public Rabbit() {
        super(WEIGHT, MAX_SATIETY);
    }

    @Override
    public void eat(Location location) {
        if(!alive)
            return;
        Plant plant = location.removePlant();
        if (plant != null) {
            currentSatiety = Math.min(maxSatiety, currentSatiety + plant.getWeight());
            log.debug("Кролик съел растение");
        }
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
