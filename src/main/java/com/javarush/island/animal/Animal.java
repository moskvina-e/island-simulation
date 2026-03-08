package com.javarush.island.animal;

import com.javarush.island.model.Island;
import com.javarush.island.model.Location;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
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
    protected boolean male; // Пол животного, true - муж., false - жен.
    protected int speed; // Скорость перемещения
    protected int maxNumberOfAnimalsPerCell; // Максимальное кол-во животных одного вида на одной клетке
    protected volatile Location currentLocation; // Текущее положение животного
    private static final int CHANCE_OF_REPRODUCTION = 50; // Шанс размножения
    private static final double SATIETY_FOR_REPRODUCTION = 0.5; // Уровень сытости для размножения

    // Карта вероятности поедания других животных
    protected Map<Class<? extends Animal>, Integer> eatingProbabilities;

    public Animal(double weight, double maxSatiety, int speed, int maxNumberOfAnimalsPerCell) {
        this.weight = weight;
        this.maxSatiety = maxSatiety;
        this.currentSatiety = maxSatiety;
        this.male = ThreadLocalRandom.current().nextBoolean();
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
                newY = Math.min(island.getHeight() - 1, currentY + currentSpeed);
                break;
            case 3:
                // влево X
                newX = Math.max(0, currentX - currentSpeed);
                break;
        }

        // Перемещение животного, если это возможно
        if (island.getLocation(newX, newY).addAnimal(this)) {
            island.getLocation(currentX, currentY).removeAnimal(this);
            log.debug("{} переместился из {} в {}", this.getClass().getSimpleName(), island.getLocation(currentX, currentY), island.getLocation(newX, newY));
        }
    }

    public void reproduce(Location location) {
        if (!isAlive() || getCurrentSatiety() <= SATIETY_FOR_REPRODUCTION * getMaxSatiety())
            return;
        //Получаем кол-во особей в локации, подходящих для размножения
        long sameSpeciesCount = location.getAnimals().stream()
                .filter(animal ->
                        animal.getClass() == this.getClass()
                        && animal.isMale() != this.isMale()
                        && animal.isAlive()
                        && animal.getCurrentSatiety() >= SATIETY_FOR_REPRODUCTION * animal.getMaxSatiety())
                .count();
        if (sameSpeciesCount > 0  && ThreadLocalRandom.current().nextInt(100) >= CHANCE_OF_REPRODUCTION) {
            try {
                // Создание потомка через рефлексию (не требуется знание о конкретном классе животного во время компиляции)
                Animal baby = this.getClass().getDeclaredConstructor().newInstance();
                baby.setCurrentSatiety(baby.getMaxSatiety() / 2); //todo magic number
                if (location.addAnimal(baby))
                    log.debug("Родилось животное {}", baby.getClass().getSimpleName());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error("Ошибка создания нового животного");
                throw new RuntimeException(e);
            }
        }
    }

    public void die() {
        this.alive = false;
    }
}
