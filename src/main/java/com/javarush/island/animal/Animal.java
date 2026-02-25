package com.javarush.island.animal;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@Slf4j
public abstract class Animal {
    protected double weight;
    protected double maxSatiety; // Максимальная сытость
    protected double currentSatiety; // Текущая сытость
    protected boolean alive = true; // Животное живое
    protected boolean isMale; // Пол животного, true - муж., false - жен.
    protected int speed; // Скорость перемещения
    protected int maxNumberOfAnimalsPerCell; // Максимальное кол-во животных одного вида на одной клетке
    protected volatile Location currentLocation; // Текущее положение животного

    // Карта вероятности поедания других животных
    protected Map<Class<? extends Animal>, Integer> eatingProbabilities;

    public Animal(double weight, double maxSatiety, int speed, int maxNumberOfAnimalsPerCell) {
        this.weight = weight;
        this.maxSatiety = maxSatiety;
        this.currentSatiety = maxSatiety;
        this.isMale = ThreadLocalRandom.current().nextBoolean();
        this.speed = speed;
        this.maxNumberOfAnimalsPerCell = maxNumberOfAnimalsPerCell;
    }

    // eat, move, reproduce
    public abstract void eat(Location location);

    public void move(Island island, int currentX, int currentY) {
        if (!isAlive())
            return;
        if (currentLocation == null) {
            log.warn("Животное {} не имеет текущей локации. Передвижение не возможно!", this.getClass().getSimpleName());
            return;
        }
        // Выбираем направление движения и кол-во шагов
        int diction = ThreadLocalRandom.current().nextInt(4);
        int currentSpeed = ThreadLocalRandom.current().nextInt(this.getSpeed() + 1);
        int newX = currentX;
        int newY = currentY;

        switch (diction) {
            case 0:
                // вверх Y
                newY = Math.max(0, currentY - currentSpeed);
                break;
            case 1:
                // вправо X
                newX = Math.min(island.getWidth() - 1, currentX + currentSpeed);
                break;
            case 2:
                // вниз Y
                newX = Math.min(island.getHeight() - 1, currentY + currentSpeed);
                break;
            case 3:
                // влево X
                newY = Math.max(0, currentX - currentSpeed);
                break;
        }
        // Перемещение животного
        island.getLocation(currentX, currentY).removeAnimal(this);
        island.getLocation(newX, newY).addAnimal(this);
    }

    public abstract void reproduce(Location location);

    public void die() {
        this.alive = false;
    }
}
