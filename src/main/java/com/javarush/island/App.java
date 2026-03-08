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
        int height = 5;
        int width = 5;
        int square = height * width;
        SimulationConfig config = SimulationConfig.builder()
                .islandHeight(height)
                .islandWidth(width)
                .initialAnimals(Map.ofEntries(
                        Map.entry(Buffalo.class, ThreadLocalRandom.current().nextInt(square, square * Buffalo.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Caterpillar.class, ThreadLocalRandom.current().nextInt(square, square * Caterpillar.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Goat.class, ThreadLocalRandom.current().nextInt(square, square * Goat.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Horse.class, ThreadLocalRandom.current().nextInt(square, square * Horse.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Sheep.class, ThreadLocalRandom.current().nextInt(square, square * Sheep.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Boar.class, ThreadLocalRandom.current().nextInt(square, square * Boar.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Duck.class, ThreadLocalRandom.current().nextInt(square, square * Duck.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Mouse.class, ThreadLocalRandom.current().nextInt(square, square * Mouse.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Bear.class, ThreadLocalRandom.current().nextInt(square, square * Bear.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Boa.class, ThreadLocalRandom.current().nextInt(square, square * Boa.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Eagle.class, ThreadLocalRandom.current().nextInt(square, square * Eagle.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Fox.class, ThreadLocalRandom.current().nextInt(square, square * Fox.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Wolf.class, ThreadLocalRandom.current().nextInt(square, square * Wolf.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Rabbit.class, ThreadLocalRandom.current().nextInt(square, square * Rabbit.MAX_NUMBER_OF_ANIMALS_PER_CELL)),
                        Map.entry(Deer.class, ThreadLocalRandom.current().nextInt(square, square * Deer.MAX_NUMBER_OF_ANIMALS_PER_CELL))))
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
