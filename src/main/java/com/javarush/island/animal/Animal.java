package com.javarush.island.animal;

import java.util.Map;

public abstract class Animal {
    protected double weight;
    protected double maxSatiety; // Максимальная сытость
    protected double currentSatiety; // Текущая сытость
    protected boolean alive = true; // Животное живое
    protected int speed = 1; // Скорость перемещения

    // Карта вероятности поедания других животных
    protected Map<Class<? extends Animal>, Integer> eatingProbabilities;

    public Animal(double weight, double maxSatiety, double currentSatiety) {
        this.weight = weight;
        this.maxSatiety = maxSatiety;
        this.currentSatiety = currentSatiety;
    }

    // eat, move, reproduce
    public abstract void eat(Location location);

    public abstract void move(Island island, int currentX, int currentY);

    public abstract void reproduce(Location location);

    public void die() {
        this.alive = false;
    }
}
