package com.javarush.island.simulation;

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.*;
import com.javarush.island.animal.omnivorous.*;
import com.javarush.island.animal.predator.*;
import com.javarush.island.config.SimulationConfig;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;
import com.javarush.island.model.Plant;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс для создания простой однопоточной симуляции
 */

@Slf4j
public class SimpleSimulation {
    private final Island island;
    private final SimulationConfig config;

    public SimpleSimulation(SimulationConfig config) {
        this.config = config;
        this.island = new Island(config.getIslandHeight(), config.getIslandWidth());
    }

    public void initialize() {
        //Размещение всех животных по клеткам
        for (Map.Entry<Class<? extends Animal>, Integer> entry : config.getInitialAnimals().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                int x = ThreadLocalRandom.current().nextInt(config.getIslandWidth());
                int y = ThreadLocalRandom.current().nextInt(config.getIslandHeight());
                try {
                    island.getLocation(x, y).addAnimal(entry.getKey().getConstructor().newInstance());
                } catch (Exception e) {
                    log.error("Не могу создать {}", entry.getKey().getSimpleName(), e);
                    continue;
                }
            }
        }
        //Размещаем растения
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);
                for (int p = 0; p < 5; p++) {  //todo заменить магическое число
                    location.addPlant(new Plant());
                }
            }
        }
        log.info("Инициализация завершена. Животные и растения размещены.");
    }

    public void tick() {
        // 1) Рост растений
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);
                for (int p = 0; p < config.getPlantsPerSell(); p++) {
                    location.addPlant(new Plant());
                }
            }
        }
        // 2) Обработка животных (пройти по всем клеткам)
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);
                List<Animal> animals = List.copyOf(location.getAnimals());
                for (Animal animal : animals) {
                    if (!animal.isAlive())
                        continue;
                    animal.eat(location);
                    animal.move(island, x, y);
                    animal.reproduce(location);
                    // Уменьшаем сытость
                    animal.setCurrentSatiety(animal.getCurrentSatiety() - 1); //todo заменить магическое число
                    if (animal.getCurrentSatiety() <= 0) {
                        animal.die();
                        location.removeAnimal(animal);
                    }
                }
            }
        }
        printStatistics();
    }

    public void printStatistics() {
        //todo статистку по всем животным
        int wolves = 0;
        int rabbits = 0;
        int deer = 0;
        int plants = 0;
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);
                for (Animal animal : location.getAnimals()) {
                    if (animal instanceof Wolf) {
                        wolves++;
                    }
                    if (animal instanceof Rabbit) {
                        rabbits++;
                    }
                    if (animal instanceof Deer) {
                        deer++;
                    }
                    plants += location.getPlants().size();
                }
            }
        }
        log.info("Статистика: Волки={}, Кролики={}, Олени={}, Растения={}", wolves, rabbits, deer, plants);

    }

    public void run(int tiks) throws InterruptedException {
        for (int i = 0; i < tiks; i++) {
            log.info("Такт {}", i + 1);
            tick();
            Thread.sleep(1000);// todo заменить магическое число
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimulationConfig config = SimulationConfig.builder()
                .islandHeight(5)
                .islandWidth(5)
                .initialAnimals(Map.ofEntries(
                        Map.entry(Buffalo.class, 5),
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
                .build();
        SimpleSimulation simpleSimulation = new SimpleSimulation(config);
        simpleSimulation.initialize();
        simpleSimulation.run(10);
    }
}
