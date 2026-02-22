package com.javarush.island.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimulationConfig {

    //Размеры острова
    private int islandWidth;
    private int islandHeight;

    //Популяции хищников
    private int initialBears;
    private int initialBoa;
    private int initialEagles;
    private int initialFoxes;
    private int initialWolves;

    //Популяции травоядных
    private int initialBuffalos;
    private int initialCaterpillars;
    private int initialDeer;
    private int initialGoats;
    private int initialHorses;
    private int initialRabbits;
    private int initialSheep;

    //Популяции всеядных
    private int initialBoars;
    private int initialDucks;
    private int initialMice;

    //Кол-во растений, которые будут добавляться за 1 такт в каждую клетку
    private int plantsPerSell;

    //Дюрация в мс
    private long tickDurationMs;

    //Вероятности
}
