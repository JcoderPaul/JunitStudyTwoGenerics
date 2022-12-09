package GenericClasses;
/*
!!! Понимание это приходит не сразу !!!
Любой метод, что обычный, что тестовый
должны быть максимально сжатыми (короткими).

Можно ошибиться в основном коде и чем метод
длиннее, тем сложнее искать ошибку, есть
возможность разбить метод на подметоды,
лучше это сделать.

С тестами этот принцип просматривается
'на раз', т.к. чем длиннее тест, тем
выше вероятность еще и в нем совершить
ошибку... (ошибка в коде, ошибка в тесте)

Чем короче метод:
-> тем понятнее, что в нем происходит;
-> тем проще, отлавливать ошибки и править код;
*/

import MyCuteCats.Cat;
import MyCuteCats.Serval;
import MyCuteCats.Tiger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZooTest {

    Zoo<Cat> catZoo;
    Zoo<Tiger> tigerZoo;
    Zoo<Serval> servalZoo;

    @Before
    public void setUp() throws Exception {
        catZoo = new Zoo<>();
        tigerZoo = new Zoo<>();
        servalZoo = new Zoo<>();
        for (int i = 0; i < 10; i++) {
            servalZoo.add(new Serval());
            tigerZoo.add(new Tiger());
        }
    }

    @Test
    public void getWeightOfAllCatsInZoo() {
        System.out.println("------- Тест getWeightOfAllCatsInZoo() -------");
        float foolAsserWeightServalInZoo = 0;
        for (int i = 0; i < servalZoo.getCats().size(); i++){
            foolAsserWeightServalInZoo += servalZoo.getCats().get(i).getWeight();
        }
        System.out.println("Вес всех сервалов в Зоопарке: " + foolAsserWeightServalInZoo);
        /*
        !!! Применение метода *.assertEquals() с Float требует
        указания точности 'delta' - в нашем случае мы установили 0.001 !!!
        */
        assertEquals(foolAsserWeightServalInZoo, servalZoo.getWeightOfAllCatsInZoo(), 0.001);

        float foolAsserWeightTigerInZoo = 0;
        for (int i = 0; i < tigerZoo.getCats().size(); i++){
            foolAsserWeightTigerInZoo += tigerZoo.getCats().get(i).getWeight();
        }
        System.out.println("Вес всех тигров в Зоопарке: " + foolAsserWeightTigerInZoo);
        /*
        ЕЩЕ РАЗ НА ЗАМЕТКУ
        !!! Применение метода *.assertEquals() с Float требует
        указания точности 'delta' - в нашем случае мы установили 0.001 !!!
        */
        assertEquals(foolAsserWeightTigerInZoo, tigerZoo.getWeightOfAllCatsInZoo(), 0.001);
    }

    @Test
    public void add_IfAddingElementToArrayIncreasesItsSize() {
        System.out.println("------- Тест add_IfAddingElementToArrayIncreasesItsSize() -------");
        assertEquals(10, tigerZoo.getSizeOfCatsZoo());
        assertEquals(10, servalZoo.getSizeOfCatsZoo());
        tigerZoo.add(new Tiger());
        servalZoo.add(new Serval());
        assertEquals(11, tigerZoo.getSizeOfCatsZoo());
        assertEquals(11, servalZoo.getSizeOfCatsZoo());
    }

    @Test
    public void compare() {
        System.out.println("------- Тест compare() -------");
        /*
        Метод сравнение в нашем случае работает с весами
        котиков, причем с суммой всех весов в коллекции.
        Как и любое сравнение мы оперируем условно тремя
        значениями:
        '1' - если то, что сравнивают (объект на котором
            метод вызван), больше того с чем сравнивают
            (объект переданный в аргументе метода);
        '0' - объекты равны;
        '-1' - если то, что сравнивают (объект на котором
               метод вызван), меньше того с чем сравнивают
               (объект переданный в аргументе метода);

        Естественно в нашем случае тигры по определению тяжелее
        сервалов и на оборот. Ну и сравнение объекта самого с
        собой дает однозначный результат.
        */
        assertEquals(1, tigerZoo.compare(servalZoo));
        assertEquals(-1, servalZoo.compare(tigerZoo));
        assertEquals(0, tigerZoo.compare(tigerZoo));
        assertEquals(0, servalZoo.compare(servalZoo));
    }

    @Test
    public void transfer_TransferAllObjectsCatTypeToOneZoo() {
        System.out.println("------- Тест transfer_TransferAllObjectsCatTypeToOneZoo() -------");
        float foolAsserWeightInBothZoo = 0;
        for (int i = 0; i < servalZoo.getCats().size(); i++){
            foolAsserWeightInBothZoo += servalZoo.getCats().get(i).getWeight();
        }
        System.out.println("Вес всех сервалов: " + foolAsserWeightInBothZoo);
        for (int i = 0; i < tigerZoo.getCats().size(); i++){
            foolAsserWeightInBothZoo += tigerZoo.getCats().get(i).getWeight();
        }
        System.out.println("Вес всех сервалов и тигров вместе: " + foolAsserWeightInBothZoo);

        Zoo.transfer(servalZoo, catZoo);
        Zoo.transfer(tigerZoo, catZoo);
        assertEquals(foolAsserWeightInBothZoo, catZoo.getWeightOfAllCatsInZoo(), 0.001);
        assertEquals(0f, servalZoo.getWeightOfAllCatsInZoo(), 0.001);
        assertEquals(0f, tigerZoo.getWeightOfAllCatsInZoo(), 0.001);
    }

    @Test
    public void transfer_TransferAllTigerAndServalTypeObjectsToZoosOfOnlyTheirTypes() {
        System.out.println("------- Тест transfer_TransferAllTigerAndServalTypeObjectsToZoosOfOnlyTheirTypes() -------");
        float foolAsserWeightInFirstServalZoo = 0;
        for (int i = 0; i < servalZoo.getCats().size(); i++){
            foolAsserWeightInFirstServalZoo += servalZoo.getCats().get(i).getWeight();
        }
        System.out.println("Вес всех сервалов в первом зоопарке: " + foolAsserWeightInFirstServalZoo);

        float foolAsserWeightInFirstTigerZoo = 0;
        for (int i = 0; i < tigerZoo.getCats().size(); i++){
            foolAsserWeightInFirstTigerZoo += tigerZoo.getCats().get(i).getWeight();
        }
        System.out.println("Вес всех тигров в первом зоопарке: " + foolAsserWeightInFirstTigerZoo);

        /* Создадим два дополнительных однотипных зоопарка */
        Zoo<Serval> servalZooTwo = new Zoo<>();
        Zoo<Tiger> tigerZooTwo = new Zoo<>();
        float foolAsserWeightInSecondServalZoo = 0;
        float foolAsserWeightInSecondTigerZoo = 0;
        for (int i = 0; i < 10; i++) {
            Serval serval = new Serval();
            foolAsserWeightInSecondServalZoo += serval.getWeight();
            servalZooTwo.add(serval);
            Tiger tiger = new Tiger();
            foolAsserWeightInSecondTigerZoo += tiger.getWeight();
            tigerZooTwo.add(tiger);
        }
        System.out.println("Вес всех сервалов во втором зоопарке: " + foolAsserWeightInSecondServalZoo);
        System.out.println("Вес всех тигров во втором зоопарке: " + foolAsserWeightInSecondTigerZoo);
        Zoo.transfer(servalZooTwo, servalZoo);
        Zoo.transfer(tigerZooTwo, tigerZoo);
        float foolAsserWeightInBothServalZoo = foolAsserWeightInFirstServalZoo +
                                               foolAsserWeightInSecondServalZoo;
        float foolAsserWeightInBothTigerZoo = foolAsserWeightInFirstTigerZoo +
                                              foolAsserWeightInSecondTigerZoo;
        assertEquals(foolAsserWeightInBothServalZoo, servalZoo.getWeightOfAllCatsInZoo(), 0.001);
        assertEquals(foolAsserWeightInBothTigerZoo, tigerZoo.getWeightOfAllCatsInZoo(), 0.001);
        assertEquals(0f, servalZooTwo.getWeightOfAllCatsInZoo(), 0.001);
        assertEquals(0f, tigerZooTwo.getWeightOfAllCatsInZoo(), 0.001);
    }
}