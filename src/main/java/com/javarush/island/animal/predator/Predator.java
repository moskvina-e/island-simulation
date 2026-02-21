package com.javarush.island.animal.predator;

import com.javarush.island.animal.Animal;
import com.javarush.island.model.Location;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public abstract class Predator extends Animal {

    public Predator(double weight, double maxSatiety, Map<Class<? extends Animal>, Integer> eatingProbabilities) {
        super(weight, maxSatiety);
        this.eatingProbabilities = eatingProbabilities;
    }

    public void eat(Location location) {
        //Жив ли объект?
        if(!this.isAlive())
            return;
        for(Animal prey : location.getAnimals()) {
            if(prey == this || !prey.isAlive() || prey.getClass() == this.getClass())
                continue;
            Integer prob = eatingProbabilities.get(prey.getClass());
            if (prob != null && ThreadLocalRandom.current().nextInt(100) < prob) {
                location.removeAnimal(prey);
                prey.die();
                currentSatiety = Math.min(currentSatiety + prey.getWeight(), maxSatiety);
                log.debug("{} съел {}", this.getClass().getSimpleName(), prey.getClass().getSimpleName());
                break;
            }
        }
    }

}
