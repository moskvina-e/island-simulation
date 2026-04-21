package com.javarush.island;

import com.javarush.island.animal.herbivore.*;
import com.javarush.island.animal.omnivorous.*;
import com.javarush.island.animal.predator.*;
import com.javarush.island.config.SimulationConfig;
import com.javarush.island.simulation.MultithreadedSimulation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {

        SimulationConfig config = new SimulationConfig();

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

        // Однопоточная симуляция
        /* private static final int SIMPLE_SIMULATION_TICKS = 10;
        SimpleSimulation simpleSimulation = new SimpleSimulation(config);
        simpleSimulation.initialize();
        try {
            simpleSimulation.run(SIMPLE_SIMULATION_TICKS);
        } catch (InterruptedException e) {
            log.error("Simulation interrupted");
            throw new RuntimeException(e);
        }*/
    }
}
