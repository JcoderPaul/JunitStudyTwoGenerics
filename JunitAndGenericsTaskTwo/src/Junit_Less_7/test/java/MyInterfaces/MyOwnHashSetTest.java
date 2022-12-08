package MyInterfaces;

import MyClasses.Planet;
import MyGenericСollectionСlasses.MyOwnHashSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyOwnHashSetTest {
    // Объявляем нашу будущую тестовую коллекцию
    private MyOwnHashSet<Integer> myHashSetTest;
    private MyOwnHashSet<Planet> observatory;
    /*
    Описываем что нужно сделать перед каждым нашим тестом.
    В нашем случае мы заполняем коллекцию 50 объектами SpaceObject.
    */
    @Before
    public void setUp() throws Exception {
        myHashSetTest = new MyOwnHashSet<>();
        for (int i = 0; i < 50; i++) {
            myHashSetTest.add(i);
        }
        // *** Тестируем с собственными классами, предварительная настройка ***
        observatory = new MyOwnHashSet<>();
        for (int i = 0; i < 50; i++) {
            observatory.add(
                    new Planet("planet","Exoplanet" + i, i,0));
        }
    }
    /*
    Простой тест, на проверку метода *.size().
    Если в коллекцию залили 50 элементов, размер
    должен соответствовать.
    */
    @Test
    public void whenAdded50ElementsThenSizeMustBe50() {
        assertEquals(50, myHashSetTest.size());
        // *** Тестируем с собственными классами ***
        assertEquals(50, observatory.size());

    }
    /*
    Повторная проверка метода *.size(), зная исходный
    размер коллекции, мы можем изменять ее размер,
    добавляя и удаляя элементы из нее. Параллельно
    тестируя методы *.add() и *.remove(), которые лучше
    тестировать отдельно и в определенной, логической
    последовательности.
    */
    @Test
    public void size() {
        Integer elementAddedToSet = 415;
        myHashSetTest.add(elementAddedToSet);
        assertEquals(51, myHashSetTest.size());
        assertTrue(myHashSetTest.remove(8));
        assertTrue(myHashSetTest.remove(elementAddedToSet));
        assertEquals(49, myHashSetTest.size());
        // *** Тестируем с собственными классами ***
        Planet planetAddedToObservatory =
                new Planet("planet","Exoplanet120", 120,0);
        Planet planetExistInSet =
                new Planet("planet","Exoplanet38", 38,0);
        observatory.add(planetAddedToObservatory);
        assertEquals(51, observatory.size());
        assertTrue(observatory.remove(planetAddedToObservatory));
        assertTrue(observatory.remove(planetExistInSet));
        assertEquals(49, observatory.size());
    }
    /*
    Проверяем удаление не существующего объекта из коллекции.
    */
    @Test
    public void whenNonExistentElementRemovedThenReturnFalse() {
        Integer nonExistentListItem = 546;
        assertFalse(myHashSetTest.remove(nonExistentListItem));
        assertEquals(50, myHashSetTest.size());
        // *** Тестируем с собственными классами ***
        Planet nonExistentItem =
                new Planet("planet","Exoplanet120", 120,0);
        assertFalse(observatory.remove(nonExistentItem));
        assertEquals(50, observatory.size());
    }
    /* Проверяем работу метода *.add() */
    @Test
    public void whenElementAddThenSizeMustBeIncrease() {
        Integer elementAddedToTheList = 235;
        myHashSetTest.add(elementAddedToTheList);
        assertEquals(51, myHashSetTest.size());
        // *** Тестируем с собственными классами ***
        Planet nonExistentItemAdded =
                new Planet("planet","Exoplanet120", 120,0);
        assertTrue(observatory.add(nonExistentItemAdded));
        assertEquals(51, observatory.size());
    }
    /* Проверяем работу метода *.remove() */
    @Test
    public void whenElementRemovedThenSizeMustBeDecreased() {
        Integer elementToBeRemovedFromTheList = 23;
        assertTrue(myHashSetTest.remove(elementToBeRemovedFromTheList));
        assertEquals(49, myHashSetTest.size());
        // *** Тестируем с собственными классами ***
        Planet planeToDelete =
                new Planet("planet","Exoplanet20", 20,0);
        assertTrue(observatory.remove(planeToDelete));
        assertEquals(49, observatory.size());
    }
    /* Проверяем метод *.clear() */
    @Test
    public void whenListClearedThenSizeMustBe0() {
        myHashSetTest.clear();
        assertEquals(0, myHashSetTest.size());
        // *** Тестируем с собственными классами ***
        observatory.clear();
        assertEquals(0, observatory.size());
    }
    /* Проверяем метод *.isEmpty() */
    @Test
    public void testIsEmptyMethod() {
        /*
        Перед запуском теста, метод *.setUp() загрузит коллекцию,
        значит она будет не пуста, и метод *.isEmpty() вернет false,
        Далее мы чистим коллекцию и снова проверяем ее заполненность,
        на этот раз мы должны получить true из метода *.isEmpty()
         */
        assertFalse(myHashSetTest.isEmpty());
        myHashSetTest.clear();
        assertEquals(0, myHashSetTest.size());
        assertTrue(myHashSetTest.isEmpty());
        // *** Тестируем с собственными классами ***
        assertFalse(observatory.isEmpty());
        observatory.clear();
        assertEquals(0, observatory.size());
        assertTrue(observatory.isEmpty());
    }

    @Test
    public void testContainsMethod() {
        /*
        Метод *.contains() возвращает true если, элемент
        переданный в аргументах есть в коллекции и false,
        если таковой не найден.
        */
        assertTrue(myHashSetTest.contains(31));
        assertFalse(myHashSetTest.contains(4578));
        // *** Тестируем с собственными классами ***
        assertTrue(observatory.contains(
                new Planet("planet","Exoplanet20", 20,0)));
        assertFalse(observatory.contains(
                new Planet("planet","Exoplanet320", 320,4)));

    }
    /*
    Проверяем действительно ли мы создали Set из оригинальных
    объектов, которые в данной коллекции не повторяются.
    */
    @Test
    public void whenSameElementRemovedTwiceSizeDecreasedOne() {
        /*
        Помним, что при старте всех тестов мы инициализируем наш
        Set и там точно есть объект с описанными ниже характеристиками.
        Но мы добавим его еще раз, ожидая в случае правильной работы
        нашей коллекции неудачу.

        Далее удаляем объект два раза, каждый раз проверяя размер коллекции.
        В идеале:
        - добавляем существующий в коллекции объект - размер ее не изменится,
        т.к. объект не добавится.
        - удаляем существующий объект из коллекции - размер меняется на -1
        - повторно удаляем тот же объект (или возможную его копию) и ничего
        не происходит - размер коллекции не меняется.
        */
        assertFalse(myHashSetTest.add(5));
        assertEquals(50, myHashSetTest.size());
        assertTrue(myHashSetTest.remove(43));
        assertEquals(49, myHashSetTest.size());
        assertFalse(myHashSetTest.remove(43));
        assertEquals(49, myHashSetTest.size());
        // *** Тестируем с собственными классами ***
        assertFalse(observatory.add(
                new Planet("planet","Exoplanet20", 20,0)));
        assertEquals(50, observatory.size());
        assertTrue(observatory.remove(
                new Planet("planet","Exoplanet20", 20,0)));
        assertEquals(49, observatory.size());
        assertFalse(observatory.remove(
                new Planet("planet","Exoplanet20", 20,0)));
        assertEquals(49, observatory.size());

    }
}