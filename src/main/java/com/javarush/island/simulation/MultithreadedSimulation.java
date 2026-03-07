package com.javarush.island.simulation;

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.Deer;
import com.javarush.island.animal.herbivore.Rabbit;
import com.javarush.island.animal.predator.Wolf;
import com.javarush.island.config.SimulationConfig;
import com.javarush.island.model.Island;
import com.javarush.island.model.Location;
import com.javarush.island.model.Plant;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
public class MultithreadedSimulation {
    private static final int CORE_POOL_SIZE = 1;
    private static final int THREADS = 10;

    private final Island island;
    private final SimulationConfig config;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
    private final ExecutorService workerPool = Executors.newFixedThreadPool(THREADS);
    private volatile boolean running = true;
    private final double SATIETY_PER_TICK = 0.01;

    public MultithreadedSimulation(SimulationConfig config) {
        this.island = new Island(config.getIslandWidth(), config.getIslandHeight());
        this.config = config;
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
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);
                int finalX = x;
                int finalY = y;
                for (Animal animal : location.getAnimals()) {
                    if (!animal.isAlive())
                        continue;
                    tasks.add(() -> {
                        animal.eat(animal.getCurrentLocation());
                        animal.move(island, finalX, finalY);
                        animal.reproduce(animal.getCurrentLocation());
                        // Уменьшаем сытость
                        animal.setCurrentSatiety(animal.getCurrentSatiety() - SATIETY_PER_TICK);
                        if (animal.getCurrentSatiety() <= 0) {
                            animal.die();
                            animal.getCurrentLocation().removeAnimal(animal);
                        }
                        return null;
                    });

                }
            }
        }
        try {
            List<Future<Void>>  futures = workerPool.invokeAll(tasks);
            for (Future<Void> f : futures) {
                f.get();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Такт прерван");
        } catch (ExecutionException e) {
            log.error("Ошибка при выполнении задачи животного", e.getCause());
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
                    else if (animal instanceof Rabbit) {
                        rabbits++;
                    }
                    else if (animal instanceof Deer) {
                        deer++;
                    }
                    plants += location.getPlants().size();
                }
            }
        }
        log.info("Статистика: Волки={}, Кролики={}, Олени={}, Растения={}.", wolves, rabbits, deer, plants);

    }

    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            if (running) tick();
        }, 0, config.getTickDurationMs(), TimeUnit.MILLISECONDS);
        log.info("Симуляция запущена с тактом {} мс.",  config.getTickDurationMs());
    }

    public void stop() {
        running = false;
        scheduler.shutdown();
        workerPool.shutdown();
        log.info("Симуляция остановлена.");
    }

}
