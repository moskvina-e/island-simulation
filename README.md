Island simulation project

com.javarush.island

1. Пакеты и классы:
1) animal - пакет с классами животных
 - Animal
   - Predator - хищник
     - Wolf - волк
     - Boa - удав
     - Fox - лиса
     - Bear - медведь
     - Eagle - орел
   - Herbivore - травоядное
     - Horse - лошадь
     - Deer - олень
     - Rabbit - кролик
     - Goat - коза
     - Sheep - овца
     - Buffalo - буйвол
     - Caterpillar - гусеница
   - Omnivorous - всеядные (могут есть каких-то животных и растения)
     - Boar - кабан
     - Mouse - мышь
     - Duck - утка
2) config - конфигурация всего приложения
 - SimulationConfig - конфигруация для однопоточного и многопоточного приложений
3) model - модельные классы
 - Island - остров (двумерный массив клеток - Location)
 - Location - клетка, содержит списки животных и растений
 - Plant - растение
4) simulation - логика симуляции
 - SimpleSimulation
5) App - главный класс для запуска (точка входа)

2. Подключение зависимостей в pom.xml
