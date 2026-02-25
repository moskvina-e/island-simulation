package com.javarush.island.animal.herbivore;

import com.javarush.island.animal.Animal;
import com.javarush.island.model.Location;
import com.javarush.island.model.Plant;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class Herbivore extends Animal {

    public Herbivore(double weight, double maxSatiety, int speed, int maxNumberOfAnimalsPerCell) {
        super(weight, maxSatiety, speed, maxNumberOfAnimalsPerCell);
    }

    public void eat(Location location) {
        if(!this.isAlive())
            return;
        Plant plant = location.removePlant();
        if (plant != null) {
            currentSatiety = Math.min(maxSatiety, currentSatiety + plant.getWeight());
            log.debug("{} съел растение",this.getClass().getSimpleName());
        }
    }
}
