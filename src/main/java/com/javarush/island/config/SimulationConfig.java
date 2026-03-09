package com.javarush.island.config;

import com.javarush.island.animal.Animal;
import com.javarush.island.animal.herbivore.*;
import com.javarush.island.animal.omnivorous.*;
import com.javarush.island.animal.predator.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Slf4j
public class SimulationConfig {

    //Размеры острова
    private int islandWidth;
    private int islandHeight;
    private int square;

    //Популяции животных
    private Map<Class<? extends Animal>, Integer> initialAnimals;

    //Кол-во растений, которые будут добавляться за 1 такт в каждую клетку
    private int plantsPerCell;

    //Дюрация в мс
    private long tickDurationMs;

    private Properties properties;

    public SimulationConfig() {
        this.properties = new Properties();
        if(loadPropertiesFile())
            extractConfigValues();
        square = islandWidth * islandHeight;
        initialAnimals = initializeAnimals();
    }

    private boolean loadPropertiesFile() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
            return true;
        } catch (NullPointerException | IOException e) {
            log.error("Ошибка загрузки файла: " + e.getMessage());
            setDefaultValues();
            return false;
        }
    }

    private void extractConfigValues() {
        String heightStr = properties.getProperty("island.height", "5");
        String widthStr = properties.getProperty("island.width", "5");
        String plantsStr = properties.getProperty("island.plants.per.cell", "5");
        String tickStr = properties.getProperty("simulation.tick.duration.ms", "1000");

        try {
            islandHeight = Integer.parseInt(heightStr.trim());
            islandWidth = Integer.parseInt(widthStr.trim());
            plantsPerCell = Integer.parseInt(plantsStr.trim());
            tickDurationMs = Long.parseLong(tickStr.trim());
        }  catch (NumberFormatException e) {
            log.error("Ошибка преобразования чисел, использую значения по умолчанию");
            setDefaultValues();
        }
    }

    private void setDefaultValues() {
        islandWidth = 5;
        islandHeight = 5;
        plantsPerCell = 5;
        tickDurationMs = 1000;
    }

    private Map<Class<? extends Animal>, Integer> initializeAnimals() {
        Map<Class<? extends Animal>, Integer> animals = new HashMap<>();

        // Словарь с максимальными кол-вами животного на 1 клетку для каждого вида животного
        Map<Class<? extends Animal>, Integer> maxValues = new HashMap<>();
        maxValues.put(Buffalo.class, Buffalo.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Caterpillar.class, Caterpillar.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Goat.class, Goat.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Horse.class, Horse.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Sheep.class, Sheep.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Boar.class, Boar.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Duck.class, Duck.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Mouse.class, Mouse.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Bear.class, Bear.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Boa.class, Boa.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Eagle.class, Eagle.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Fox.class, Fox.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Wolf.class, Wolf.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Rabbit.class, Rabbit.MAX_NUMBER_OF_ANIMALS_PER_CELL);
        maxValues.put(Deer.class, Deer.MAX_NUMBER_OF_ANIMALS_PER_CELL);

        for (Map.Entry<Class<? extends Animal>, Integer> entry : maxValues.entrySet()) {
            int count = ThreadLocalRandom.current().nextInt(square, square * entry.getValue());
            animals.put(entry.getKey(), count);
        }
        return animals;
    }
}
