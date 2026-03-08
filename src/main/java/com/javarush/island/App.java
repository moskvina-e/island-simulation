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
                        Map.entry(Buffalo.class, ThreadLocalRandom.current().nextInt(Buffalo.MAX_NUMBER_OF_ANIMALS_PER_CELL)), //todo подумать нужна ли такая автоматическая инициализация (поля нужно будет переделать на public)
                        Map.entry(Caterpillar.class, 5),
                        Map.entry(Goat.class, 5),
                        Map.entry(Horse.class, 5),
                        Map.entry(Sheep.class, 5),
                        Map.entry(Boar.class, 5),
                        Map.entry(Duck.class, 5),
                        Map.entry(Mouse.class, 5),
                        Map.entry(Bear.class, 5),
                        Map.entry(Boa.class, 5),
                        Map.entry(Eagle.class, 5),
                        Map.entry(Fox.class, 5),
                        Map.entry(Wolf.class, 5),
                        Map.entry(Rabbit.class, 5),
                        Map.entry(Deer.class, 5)))
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
