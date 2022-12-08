package MyInterfaces;

import MyClasses.Planet;
import MyGenericСollectionСlasses.MyOwnLinkedList;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyOwnLinkedListTest extends TestCase {
    // Создаем ссылки на первоначальные коллекции
    private MyOwnCollection <Integer> myOwnLinkedListTest;
    private MyOwnCollection <Planet> myObservatory;
    // Метод предварительной настройки, грузится перед каждым тестовым методом
    @Before
    public void setUp() throws Exception {
        // Инициализировали коллекции
        myOwnLinkedListTest = new MyOwnLinkedList<>();
        myObservatory = new MyOwnLinkedList<>();
        // Загрузили коллекции элементами по 10 шт. в каждую
        for (int i = 0; i < 100; i++) {
            myOwnLinkedListTest.add(i);
        }
        // *** Тестируем с собственными классами, предварительная настройка ***
        for (int i = 0; i < 100; i++) {
            myObservatory.add(
                    new Planet("planet","Exoplanet" + i, i,0));
        }
    }

    @Test
    public void testForeachForMySpaceObjectList() {
        int index = 0;
        for (Integer testArrayList: myOwnLinkedListTest) {
            index++;
        }
        assertEquals(100, index);
        // *** Тестируем с собственными классами ***
        index = 0;
        for (Planet testArrayList: myObservatory) {
            index++;
        }
        assertEquals(100, index);
    }

    @Test
    public void testForeachWithRemoveByIteratorMethodInMyArrayList() {
        int index = 0;
        // Перебираем элементы списка и удаляем все кратные 200 без остатка
        Iterator<Integer> testIterator = myOwnLinkedListTest.iterator();
        while (testIterator.hasNext()){
            if(testIterator.next() % 2 == 0){
                testIterator.remove();
            }
        }
        // Их должно остаться 50
        for (Integer spaceObject: myOwnLinkedListTest) {
            index++;
        }
        // Проверяем соответствие
        assertEquals(50, index);
        // *** Тестируем с собственными классами ***
        index = 0;
        // Перебираем элементы списка и удаляем все кратные 200 без остатка
        Iterator<Planet> testObservatoryIterator = myObservatory.iterator();
        while (testObservatoryIterator.hasNext()){
            if(testObservatoryIterator.next().getRadius() % 2 == 0){
                testObservatoryIterator.remove();
            }
        }
        // Их должно остаться 50
        for (Planet spaceObject: myObservatory) {
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
        assertEquals(100, myOwnLinkedListTest.size());
        // *** Тестируем с собственными классами ***
        assertEquals(100, myObservatory.size());
    }
    // Проверка метода *.add()
    @Test
    public void testAdd_IfElementIsPlacedInTheCollectionsThenSizeShouldIncrease() {
        Integer elementAddedToCollection = 233;
        assertTrue(myOwnLinkedListTest.add(elementAddedToCollection));
        assertEquals(101, myOwnLinkedListTest.size());
        // *** Тестируем с собственными классами ***
        Planet addPlanet =
                new Planet("planet","Exoplanet1238", 1238,0);
        assertTrue(myObservatory.add(addPlanet));
        assertEquals(101, myObservatory.size());

       }
    // Тестируем метод *.remove() на true
    @Test
    public void testRemove_IfRemoveOneElementFromCollectionsThenSizeShouldDecrease() {
        // При удачном удалении элемента из коллекции, метод должен вернуть true
        assertTrue(myOwnLinkedListTest.remove(4));
        // При удачном удалении одно элемента из коллекции, размер должен уменьшиться на 1
        assertEquals(99, myOwnLinkedListTest.size());
        // *** Тестируем с собственными классами ***
        assertTrue(myObservatory.remove(
                new Planet("planet","Exoplanet38", 38,0)));
        // При удачном удалении одно элемента из коллекции, размер должен уменьшиться на 1
        assertEquals(99, myObservatory.size());
    }

    // Тестируем метод *.remove() на true
    @Test
    public void testRemove_IfTryDeleteNonExistentElementFromCollectionsThenSizeWillNotChange() {
        // При не удачном удалении элемента из коллекции, метод должен вернуть false
        assertFalse(myOwnLinkedListTest.remove(200));
        // При не удачном удалении одно элемента из коллекции, ее размер не должен измениться
        assertEquals(100, myOwnLinkedListTest.size());
        // *** Тестируем с собственными классами ***
        assertFalse(myObservatory.remove(
                new Planet("planet","Exoplanet138", 138,0)));
        // При не удачном удалении одно элемента из коллекции, ее размер не должен измениться
        assertEquals(100, myObservatory.size());
    }
    // Тестируем метод *.clear()
    @Test
    public void testClear_whenListClearedThenSizeMustBeZero() {
        // Применяем очистку ко всем доступным тестовым коллекциям
        myOwnLinkedListTest.clear();
        // Проверяем соответствие нулю после очистки
        assertEquals(0, myOwnLinkedListTest.size());
        // *** Тестируем с собственными классами ***
        myObservatory.clear();
        // Проверяем соответствие нулю после очистки
        assertEquals(0, myObservatory.size());
    }
    // Если искомый элемент в коллекции есть вернется true
    @Test
    public void testContains_IfDesiredElementIsInCollectionsThenTruthWillReturn() {
        // Передаем в метод объект, который точно в коллекции есть
        assertTrue(myOwnLinkedListTest.contains(5));
        // *** Тестируем с собственными классами ***
        assertTrue(myObservatory.contains(
                new Planet("planet","Exoplanet18", 18,0)));
    }
    // Если искомого элемента в коллекции нет вернется false
    @Test
    public void testContains_IfDesiredElementIsNotInCollectionsThenFalseWillReturn() {
        // Передаем в метод объект, которого точно в коллекции нет
        assertFalse(myOwnLinkedListTest.contains(300));
        // *** Тестируем с собственными классами ***
        assertFalse(myObservatory.contains(
                new Planet("planet","Exoplanet188", 218,3)));
    }
}