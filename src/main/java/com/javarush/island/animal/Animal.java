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
        if (currentLocation == null) { //todo где-то инициализируется это поле currentLocation?
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
        if (checkAnimalsOnLocation(island.getLocation(newX, newY))) {
            island.getLocation(currentX, currentY).removeAnimal(this);
            island.getLocation(newX, newY).addAnimal(this);
        }
    }

    public void reproduce(Location location) {
        if (!isAlive() || getCurrentSatiety() <= 0.5 * getMaxSatiety())
            return;
        //Получаем кол-во особей в локации, подходящих для размножения
        long sameSpeciesCount = location.getAnimals().stream()
                .filter(animal ->
                        animal.getClass() == this.getClass()
                        && animal.isMale() != this.isMale()
                        && animal.isAlive()
                        && animal.getCurrentSatiety() >= 0.5 * animal.getMaxSatiety())
                .count();
        if (sameSpeciesCount > 0  && ThreadLocalRandom.current().nextInt(100) >= CHANCE_OF_REPRODUCTION && checkAnimalsOnLocation(location)) {
            try {
                // Создание потомка через рефлексию (не требуется знание о конкретном классе животного во время компиляции)
                Animal baby = this.getClass().getDeclaredConstructor().newInstance();
                baby.setCurrentSatiety(baby.getMaxSatiety() / 2);
                location.addAnimal(baby);
                log.debug("Родилось животное {}", baby.getClass().getSimpleName());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error("Ошибка создания нового животного");
                throw new RuntimeException(e);
            }
        }
    }

    //Возвращает true, если в локации есть место для нового животного этого вида (для move() или reproduce())
    public boolean checkAnimalsOnLocation (Location location) {
        long count = location.getAnimals()
                .stream()
                .filter(animal -> this.getClass().equals(animal.getClass()))
                .count();
        return count < this.maxNumberOfAnimalsPerCell;

    }

    public void die() {
        this.alive = false;
    }
}
