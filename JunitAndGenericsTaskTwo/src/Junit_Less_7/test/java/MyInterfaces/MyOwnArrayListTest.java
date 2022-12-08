package MyInterfaces;

import MyClasses.Planet;
import MyGenericСollectionСlasses.MyOwnArrayList;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyOwnArrayListTest extends TestCase {
    // Создаем ссылки на первоначальные коллекции
    private MyOwnCollection <Integer> myOwnListTest;
    private MyOwnCollection <Planet> observatory;
    // Метод предварительной настройки, грузится перед каждым тестовым методом
    @Before
    public void setUp() throws Exception {
        // Инициализировали коллекции
        myOwnListTest = new MyOwnArrayList<>();
        observatory = new MyOwnArrayList<>();
        // Загрузили коллекции элементами по 10 шт. в каждую
        for (int i = 0; i < 100; i++) {
            myOwnListTest.add(i);
            observatory.add(
                    new Planet("planet","Exoplanet" + i, i * 1000,0));

        }
    }

    @Test
    public void testForeachForMySpaceObjectList() {
        // *** Тестируем обычный Integer ***
        int index = 0;
        for (Integer testArrayList: myOwnListTest) {
            index++;
        }
        assertEquals(100, index);
        // *** Тестируем с объектами Planet ***
        index = 0;
        for (Planet testArrayList: observatory) {
            index++;
        }
        assertEquals(100, index);
    }

    @Test
    public void testForeachWithRemoveByIteratorMethodInMyArrayList() {
        // *** Тестируем с обычными Integer ***
        int index = 0;
        // Перебираем элементы списка и удаляем все кратные 2 без остатка
        Iterator<Integer> testIterator = myOwnListTest.iterator();
        while (testIterator.hasNext()){
            if(testIterator.next() % 2 == 0){
                testIterator.remove();
            }
        }
        // Их должно остаться 50
        for (Integer integerTest: myOwnListTest) {
            index++;
        }
        // Проверяем соответствие
        assertEquals(50, index);
        // *** Тестируем с объектами Planet ***
        index = 0;
        // Перебираем элементы списка и удаляем все кратные 2 без остатка
        Iterator<Planet> testPlanetIterator = observatory.iterator();
        while (testPlanetIterator.hasNext()){
            if(testPlanetIterator.next().getRadius() % 2000 == 0){
                testPlanetIterator.remove();
            }
        }
        // Их должно остаться 50
        for (Planet spaceObject: observatory) {
            index++;
        }
        // Проверяем соответствие
        assertEquals(50, index);
    }

    // Проверяем метод *.size()
    @Test
    public void testSize_If_10_ElementsArePlacedInTheCollectionsThenSize_10() {
        /*
        Первичная загрузка каждой коллекции 10 элементов,
        должно совпадать с ожидаемым аргументом - 'expected'.
        */
        assertEquals(100, myOwnListTest.size());
        assertEquals(100, observatory.size());
    }
    // Проверка метода *.add()
    @Test
    public void testAdd_IfElementIsPlacedInTheCollectionsThenSizeShouldIncrease() {
        Integer elementAddedToCollection = 233;
        assertTrue(myOwnListTest.add(elementAddedToCollection));
        assertEquals(101, myOwnListTest.size());

        Planet mercury =
                new Planet("планета","Меркурий", 2439.7, 0);
        assertTrue(observatory.add(mercury));
        assertEquals(101, observatory.size());
       }
    // Тестируем метод *.remove() на true
    @Test
    public void testRemove_IfRemoveOneElementFromCollectionsThenSizeShouldDecrease() {
        // При удачном удалении элемента из коллекции, метод должен вернуть true
        assertTrue(myOwnListTest.remove(4));
        // При удачном удалении одно элемента из коллекции, размер должен уменьшиться на 1
        assertEquals(99, myOwnListTest.size());

        // При удачном удалении элемента из коллекции, метод должен вернуть true
        assertTrue(observatory.remove(
                new Planet("planet","Exoplanet5", 5000,0)));
        // При удачном удалении одно элемента из коллекции, размер должен уменьшиться на 1
        assertEquals(99, observatory.size());

    }

    // Тестируем метод *.remove() на true
    @Test
    public void testRemove_IfTryDeleteNonExistentElementFromCollectionsThenSizeWillNotChange() {
        // При не удачном удалении элемента из коллекции, метод должен вернуть false
        assertFalse(myOwnListTest.remove(200));
        // При не удачном удалении одно элемента из коллекции, ее размер не должен измениться
        assertEquals(100, myOwnListTest.size());

        // При не удачном удалении элемента из коллекции, метод должен вернуть false
        assertFalse(observatory.remove(
                new Planet("planet","Diplitor12", 34500,3)));
        // При не удачном удалении одно элемента из коллекции, ее размер не должен измениться
        assertEquals(100, observatory.size());
    }
    // Тестируем метод *.clear()
    @Test
    public void testClear_whenListClearedThenSizeMustBeZero() {
        // Применяем очистку ко всем доступным тестовым коллекциям
        myOwnListTest.clear();
        // Проверяем соответствие нулю после очистки
        assertEquals(0, myOwnListTest.size());

        // Применяем очистку ко всем доступным тестовым коллекциям
        observatory.clear();
        // Проверяем соответствие нулю после очистки
        assertEquals(0, observatory.size());
    }
    // Если искомый элемент в коллекции есть вернется true
    @Test
    public void testContains_IfDesiredElementIsInCollectionsThenTruthWillReturn() {
        // Передаем в метод объект, который точно в коллекции есть
        assertTrue(myOwnListTest.contains(5));

        // Передаем в метод объект, который точно в коллекции есть
        assertTrue(observatory.contains(
                new Planet("planet","Exoplanet18", 18000,0)));
    }
    // Если искомого элемента в коллекции нет вернется false
    @Test
    public void testContains_IfDesiredElementIsNotInCollectionsThenFalseWillReturn() {
        // Передаем в метод объект, которого точно в коллекции нет
        assertFalse(myOwnListTest.contains(300));

        // Передаем в метод объект, которого точно в коллекции нет
        assertFalse(observatory.contains(
                new Planet("planet","AplionQ", 23450,0)));
    }
}