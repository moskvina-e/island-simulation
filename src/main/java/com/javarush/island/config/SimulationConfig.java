package com.javarush.island.config;

import com.javarush.island.animal.Animal;
import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class SimulationConfig {

    //Размеры острова
    private int islandWidth;
    private int islandHeight;

    //Популяции животных
    private Map<Class<? extends Animal>, Integer> initialAnimals;

    //Кол-во растений, которые будут добавляться за 1 такт в каждую клетку
    private int plantsPerSell;

    //Дюрация в мс
    private long tickDurationMs;

    //Вероятности
}
