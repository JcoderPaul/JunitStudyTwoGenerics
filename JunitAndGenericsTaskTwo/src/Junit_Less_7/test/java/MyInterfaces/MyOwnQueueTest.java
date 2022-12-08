package MyInterfaces;

import MyClasses.Planet;
import MyGenericСollectionСlasses.MyOwnSimpleQueue;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/*
Тестируем методы интерфейса SpaceObjectQueue.
В данной реализации класса коллекции, мы оставили
много методов из коллекции LinkedList и дополнили
функционал методами интерфейса Queue:
- *.peek();
- *.poll();
- *.element()();
*/
public class MyOwnQueueTest {
    // Создаем ссылки на первоначальные коллекции
    private MyOwnSimpleQueue<Integer> myOwnSimpleQueueTest;
    private MyOwnSimpleQueue<Planet> myObservatory;
    // Метод предварительной настройки, грузится перед каждым тестовым методом
    @Before
    public void setUp() throws Exception {
        // Инициализировали коллекции
        myOwnSimpleQueueTest = new MyOwnSimpleQueue<>();
        myObservatory = new MyOwnSimpleQueue<>();
        // Загрузили коллекции элементами по 10 шт. в каждую
        for (int i = 0; i < 100; i++) {
            myOwnSimpleQueueTest.add(i);
        }
        // *** Тестируем с собственными классами, предварительная настройка ***
        for (int i = 0; i < 100; i++) {
            myObservatory.add(
                    new Planet("planet","Exoplanet" + i, i,0));
        }
    }
    /*
    Загружаем в коллекцию сто элементов и проверяем
    их количество простым поэлементным перебором,
    через forEach.
    */
    @Test
    public void testForeachForMySpaceObjectQueue() {
        int index = 0;
        for (Integer elementOfQueue: myOwnSimpleQueueTest) {
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

    /*
    Загружаем в коллекцию сто элементов, далее используя
    фильтр и удаляем из них 'четные элементы' методом
    *.remove(), предоставленным Iterator - ом.

    Далее в цикле forEach подсчитываем количество оставшихся
    элементов в коллекции.
    */
    @Test
    public void testForeachWithRemoveByIteratorMethodInMyQueue() {
        int index = 0;
        // Перебираем элементы списка и удаляем все кратные 200 без остатка
        Iterator<Integer> testIterator = myOwnSimpleQueueTest.iterator();
        while (testIterator.hasNext()){
            if(testIterator.next() % 2 == 0){
                testIterator.remove();
            }
        }
        // Их должно остаться 50
        for (Integer elementOfQueue: myOwnSimpleQueueTest) {
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
    public void testSize_If_100_ElementsArePlacedInTheCollectionsThenSize_100() {
        /*
        Первичная загрузка 100 элементов, должна
        совпадать с ожидаемым аргументом - 'expected'.
        */
        assertEquals(100, myOwnSimpleQueueTest.size());
        // *** Тестируем с собственными классами ***
        assertEquals(100, myObservatory.size());
    }
    // Проверка метода *.add()
    @Test
    public void testAdd_IfElementIsPlacedInTheCollectionsThenSizeShouldIncrease() {
        Integer elementAddedToCollection = 435;
        /*
        В примере Junit_Less_1 мы не возвращали true/false
        в методах типа *.add(), в данном случае это реализовано
        и кроме факта добавления мы проверяем возвращаемый флаг.
        */
        assertTrue(myOwnSimpleQueueTest.add(elementAddedToCollection));
        /*
        Тестируем добавление элементов в каждую коллекцию по 1 - ой
        шт., и тут же проверяем изменение значения size на такую же
        величину.
         */
        assertEquals(101, myOwnSimpleQueueTest.size());
        // *** Тестируем с собственными классами ***
        Planet addPlanet =
                new Planet("planet","Exoplanet438", 438,0);
        assertTrue(myObservatory.add(addPlanet));
        assertEquals(101, myObservatory.size());
    }
    // Тестируем метод *.remove() на true
    @Test
    public void testRemove_IfRemoveOneExistingElementFromCollectionsThenSizeShouldDecrease() {
        // При удачном удалении элемента из коллекции, метод должен вернуть true
        assertTrue(myOwnSimpleQueueTest.remove(4));
        // При удачном удалении одно элемента из коллекции, размер должен уменьшиться на 1
        assertEquals(99, myOwnSimpleQueueTest.size());
        // *** Тестируем с собственными классами ***
        assertTrue(myObservatory.remove(
                new Planet("planet","Exoplanet38", 38,0)));
        // При удачном удалении одно элемента из коллекции, размер должен уменьшиться на 1
        assertEquals(99, myObservatory.size());
    }

    /*
    Тестируем метод *.remove() на true. Данного метода
    в такой конфигурации в стандартной коллекции Queue
    нет. Есть метод *.remove(), без аргументов на входе,
    который возвращает с удалением элемент из начала очереди.
    Если очередь пуста, генерирует исключение NoSuchElementException.
    Мы не стали реализовывать данный код, оставив старый от
    LinkedList
    */
    @Test
    public void testRemove_IfTryDeleteNonExistentElementFromCollectionsThenSizeWillNotChange() {
        // При не удачном удалении элемента из коллекции, метод должен вернуть false
        assertFalse(myOwnSimpleQueueTest.remove(542));
        // При не удачном удалении одно элемента из коллекции, ее размер не должен измениться
        assertEquals(100, myOwnSimpleQueueTest.size());
        // *** Тестируем с собственными классами ***
        assertFalse(myObservatory.remove(
                new Planet("planet","Exoplanet138", 138,0)));
        // При не удачном удалении одно элемента из коллекции, ее размер не должен измениться
        assertEquals(100, myObservatory.size());
    }
    // Тестируем метод *.clear()
    @Test
    public void testClear_whenQueueClearedThenSizeMustBeZero() {
        // Применяем очистку ко всем доступным тестовым коллекциям
        myOwnSimpleQueueTest.clear();
        // Проверяем соответствие нулю после очистки
        assertEquals(0, myOwnSimpleQueueTest.size());
        // *** Тестируем с собственными классами ***
        myObservatory.clear();
        // Проверяем соответствие нулю после очистки
        assertEquals(0, myObservatory.size());
    }
    // Если искомый элемент в коллекции есть вернется true
    @Test
    public void testContains_IfDesiredElementIsInCollectionsThenTruthWillReturn() {
        // Передаем в метод объект, который точно в коллекции есть
        assertTrue(myOwnSimpleQueueTest.contains(54));
        // *** Тестируем с собственными классами ***
        assertTrue(myObservatory.contains(
                new Planet("planet","Exoplanet18", 18,0)));
    }
    // Если искомого элемента в коллекции нет вернется false
    @Test
    public void testContains_IfDesiredElementIsNotInCollectionsThenFalseWillReturn() {
        // Передаем в метод объект, которого точно в коллекции нет
        assertFalse(myOwnSimpleQueueTest.contains(657));
        // *** Тестируем с собственными классами ***
        assertFalse(myObservatory.contains(
                new Planet("planet","Exoplanet888", 888,4)));
    }
    // Проверяем метод *.poll() - возвращаем первый элемент очереди и удаляем его.
    @Test
    public void testPoll_IfRemoveElementFromFrontOfQueueSizeOfCollectionWillDecreaseByOne() {
        // Определяем элемент из начала очереди
        Integer testObject = 0;
        // Запускаем метод *.pool() и проверяем соответствие
        assertEquals(testObject, myOwnSimpleQueueTest.poll());
        // Элемент возвращен и удален
        assertEquals(99, myOwnSimpleQueueTest.size());
        // Переназначаем элемент и повторяем операции
        testObject = 1;
        assertEquals(testObject, myOwnSimpleQueueTest.poll());
        assertEquals(98, myOwnSimpleQueueTest.size());

        // *** Тестируем с собственными классами ***
        // Определяем элемент из начала очереди
        Planet headPlanet =
                new Planet("planet","Exoplanet0", 0,0);
        // Запускаем метод *.pool() и проверяем соответствие
        assertEquals(headPlanet, myObservatory.poll());
        // Элемент возвращен и удален
        assertEquals(99, myObservatory.size());
        // Переназначаем элемент и повторяем операции
        headPlanet =
                new Planet("planet","Exoplanet1", 1,0);
        assertEquals(headPlanet, myObservatory.poll());
        assertEquals(98, myObservatory.size());
    }
    // Тестируем метод *.poll() на возврат null
    @Test
    public void testPoll_IfQueueIsEmptyThePollMethodWillReturnNull() {
        // Чисти коллекцию
        myOwnSimpleQueueTest.clear();
        // Запускаем метод *.pool() и проверяем соответствие
        assertEquals(null, myOwnSimpleQueueTest.poll());
        // *** Тестируем с собственными классами ***
        myObservatory.clear();
        // Запускаем метод *.pool() и проверяем соответствие
        assertEquals(null, myObservatory.poll());
    }
    // Проверяем метод *.peek(), возвращаем элемент из головы очереди без удаления
    @Test
    public void testPeek_GetElementFromHeadOfQueueWithoutDeletingItSizeOfCollectionHasNotChanged() {
        // Определяем элемент из начала очереди
        Integer testObject = 0;
        // Запускаем метод и проверяем соответствие
        assertEquals(testObject, myOwnSimpleQueueTest.peek());
        assertEquals(100, myOwnSimpleQueueTest.size());
        // *** Тестируем с собственными классами ***
        Planet headPlanet =
                new Planet("planet","Exoplanet0", 0,0);
        assertEquals(headPlanet, myObservatory.peek());
        assertEquals(100, myObservatory.size());
    }
    /*
    Проверяем метод *.peek(), возвращаем элемент из головы
    очереди без удаления, если очередь пуста возвращаем null
    */
    @Test
    public void testPeek_IfQueueIsEmptyMethodPeekWillReturnNull() {
        // Чистим очередь
        myOwnSimpleQueueTest.clear();
        assertEquals(null, myOwnSimpleQueueTest.peek());
        // *** Тестируем с собственными классами ***
        myObservatory.clear();
        assertEquals(null, myObservatory.peek());
    }
    /*
    Тестируем метод *.element(), который возвращает элемент
    из головы очереди, но если коллекция пуста метод выбрасывает
    исключение.
    */
    @Test(expected = NoSuchElementException.class)
    public void testElement_IfQueueIsEmptyElementMethodThrowsAnException() {
        // Очистим коллекцию
        myOwnSimpleQueueTest.clear();
        // Пытаемся получить первый элемент очереди
        myOwnSimpleQueueTest.element();
    }
    @Test(expected = NoSuchElementException.class)
    public void testElement_IfQueueOfMyClassesIsEmptyElementMethodThrowsAnException() {
        // *** Тестируем с собственными классами ***
        myObservatory.clear();
        myObservatory.element();
    }
    /*
    Тестируем метод *.element(), который возвращает элемент
    из головы очереди, на этот раз очередь не пуста и мы
    должны получить элемент буз удаления его из головы очереди.
    */
    @Test
    public void testElement_IfQueueIsNotEmptyElementMethodReturnHeadOfQueue() {
        Integer testObject = 0;
        // Пытаемся получить первый элемент очереди
        assertEquals(testObject, myOwnSimpleQueueTest.element());
        // Элемент возвращен и не удален
        assertEquals(100, myOwnSimpleQueueTest.size());
        // *** Тестируем с собственными классами ***
        Planet headPlanet =
                new Planet("planet","Exoplanet0", 0,0);
        assertEquals(headPlanet, myObservatory.element());
        assertEquals(100, myObservatory.size());
    }
}