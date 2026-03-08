package com.javarush.island;

import com.javarush.island.animal.herbivore.*;
import com.javarush.island.animal.omnivorous.*;
import com.javarush.island.animal.predator.*;
import com.javarush.island.config.SimulationConfig;
import com.javarush.island.simulation.MultithreadedSimulation;
import com.javarush.island.simulation.SimpleSimulation;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class App {

    //private static final int SIMPLE_SIMULATION_TICKS = 10;

    public static void main(String[] args) {
        SimulationConfig config = SimulationConfig.builder()
                .islandHeight(5)
                .islandWidth(5)
                .initialAnimals(Map.ofEntries(
                        Map.entry(Buffalo.class, ThreadLocalRandom.current().nextInt(25, 25 * Buffalo.MAX_NUMBER_OF_ANIMALS_PER_CELL)), //todo подумать нужна ли такая автоматическая инициализация (поля нужно будет переделать на public)
                        Map.entry(Caterpillar.class, ThreadLocalRandom.current().nextInt(25, 25 * Caterpillar.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Goat.class, ThreadLocalRandom.current().nextInt(25, 25 * Goat.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Horse.class, ThreadLocalRandom.current().nextInt(25, 25 * Horse.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Sheep.class, ThreadLocalRandom.current().nextInt(25, 25 * Sheep.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Boar.class, ThreadLocalRandom.current().nextInt(25, 25 * Boar.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Duck.class, ThreadLocalRandom.current().nextInt(25, 25 * Duck.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Mouse.class, ThreadLocalRandom.current().nextInt(25, 25 * Mouse.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Bear.class, ThreadLocalRandom.current().nextInt(25, 25 * Bear.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Boa.class, ThreadLocalRandom.current().nextInt(25, 25 * Boa.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Eagle.class, ThreadLocalRandom.current().nextInt(25, 25 * Eagle.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Fox.class, ThreadLocalRandom.current().nextInt(25, 25 * Fox.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Wolf.class, ThreadLocalRandom.current().nextInt(25, 25 * Wolf.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Rabbit.class, ThreadLocalRandom.current().nextInt(25, 25 * Rabbit.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Deer.class, ThreadLocalRandom.current().nextInt(25, 25 * Deer.MAX_NUMBER_OF_ANIMALS_PER_CELL))))
                .plantsPerSell(5)
                .tickDurationMs(1000)
                .build();

        // Однопоточная симуляция
        /*SimpleSimulation simpleSimulation = new SimpleSimulation(config);
        simpleSimulation.initialize();
        try {
            simpleSimulation.run(SIMPLE_SIMULATION_TICKS);
        } catch (InterruptedException e) {
            log.error("Simulation interrupted");
            throw new RuntimeException(e);
        }*/

        // Многопоточная симуляция
        MultithreadedSimulation multithreadedSimulation = new MultithreadedSimulation(config);
        multithreadedSimulation.initialize();

        log.info("Начальное состояние:");
        multithreadedSimulation.printStatistics();

        multithreadedSimulation.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        multithreadedSimulation.stop();
    }
}
