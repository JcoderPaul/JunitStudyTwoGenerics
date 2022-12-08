package MyInterfaces;

import MyClasses.Discoverer;
import MyClasses.Planet;
import MyGenericСollectionСlasses.MyOwnHashMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyOwnHashMapTest {
    private MyOwnHashMap<Integer, String> myOwnHashMapTest;
    private MyOwnHashMap<Discoverer, Planet> observatory;

    @Before
    public void setUp() throws Exception {
        myOwnHashMapTest = new MyOwnHashMap<>();
        observatory = new MyOwnHashMap<>();
    }

    @Test
    public void whenPut100ElementsThenSizeBecome100() {
        // *** Тестируем с обертками для примитивов ***
        for (int i = 0; i < 100; i++) {
            myOwnHashMapTest.put(i, "элемент" + i);
        }
        assertEquals(100, myOwnHashMapTest.size());

        // *** Тестируем с собственными классами ***

        for (int i = 0; i < 100; i++) {
            observatory.put(
                    new Discoverer(i, "Name" + i, "LastName"),
                    new Planet("planet","Exoplanet" + i, i,0));
        }
        assertEquals(100, observatory.size());
    }

    @Test
    public void whenPut100ElementsWith10DifferentKeysThenSize10() {
        for (int i = 0; i < 100; i++) {
            int index = i % 10;
            myOwnHashMapTest.put(index, "элемент" + index);
        }
        assertEquals(10, myOwnHashMapTest.size());

        // *** Тестируем с собственными классами ***

        for (int i = 0; i < 100; i++) {
            int index = i % 10;
            observatory.put(
                    new Discoverer(index, "Name", "LastName"),
                    new Planet("planet","Exoplanet" + i, i,0));
        }
        assertEquals(10, observatory.size());
    }

    @Test
    public void removeReturnTrueOnlyOnce() {
        for (int i = 0; i < 10; i++) {
            myOwnHashMapTest.put(i, "элемент" + i);
        }
        assertEquals(10, myOwnHashMapTest.size());

        Integer elementForDeleting = 5;
        assertTrue(myOwnHashMapTest.remove(elementForDeleting));
        assertEquals(9, myOwnHashMapTest.size());
        assertFalse(myOwnHashMapTest.remove(elementForDeleting));
        assertEquals(9, myOwnHashMapTest.size());

        // *** Тестируем с собственными классами ***
        for (int i = 0; i < 10; i++) {
            observatory.put(
                    new Discoverer(i, "Name" + i, "LastName"),
                    new Planet("planet","Exoplanet" + i, i,0));
        }
        assertEquals(10, observatory.size());

        Discoverer elementForDelete =
                new Discoverer(5, "Name5", "LastName");
        assertTrue(observatory.remove(elementForDelete));
        assertEquals(9, observatory.size());
        assertFalse(observatory.remove(elementForDelete));
        assertEquals(9, observatory.size());
    }

    @Test
    public void countOfKeysMustBeEqualsToCountOfValues() {
        for (int i = 0; i < 100; i++) {
            myOwnHashMapTest.put(i, "элемент" + i);
        }
        assertEquals(100, myOwnHashMapTest.size());
        assertEquals(100, myOwnHashMapTest.keySet().size());
        assertEquals(100, myOwnHashMapTest.values().size());

        // *** Тестируем с собственными классами ***
        for (int i = 0; i < 10; i++) {
            observatory.put(
                    new Discoverer(i, "Name" + i, "LastName"),
                    new Planet("planet","Exoplanet" + i, i,0));
        }
        assertEquals(10, observatory.size());
        assertEquals(10, observatory.keySet().size());
        assertEquals(10, observatory.values().size());
    }

    @Test
    public void methodGetMustReturnRightValue() {
        for (int i = 0; i < 100; i++) {
            myOwnHashMapTest.put(i, "элемент" + i);
        }
        Integer keyForFind = 50;
        String findValue = myOwnHashMapTest.get(keyForFind);
        String expectedSpaceObjectName = "элемент50";
        assertEquals(expectedSpaceObjectName, findValue);

        // *** Тестируем с собственными классами ***
        for (int i = 0; i < 50; i++) {
            observatory.put(
                    new Discoverer(i, "Name" + i, "LastName"),
                    new Planet("planet","Exoplanet" + i, i,0));
        }

        Discoverer elementForFind =
                new Discoverer(25, "Name25", "LastName");
        Planet findPlanet = observatory.get(elementForFind);
        String expectedPlanetName = "Exoplanet25";
        String expectedKindOfObject = "planet";
        Double expectedRadius = 25.0;
        Integer expectedCountOfSatellite = 0;
        assertEquals(expectedPlanetName, findPlanet.getNameOfSpaceObject());
        assertEquals(expectedKindOfObject, findPlanet.getKindOfSpaceObject());
        assertEquals(expectedRadius,(Double) findPlanet.getRadius());
        assertEquals(expectedCountOfSatellite, (Integer) findPlanet.getCountOfSatellite());
    }
}