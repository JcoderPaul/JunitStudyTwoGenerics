package MyJunitTests;

import MyGenericTask.MyClasses.MagicBox;
import MyGenericTask.MyClasses.StarCluster.Planet;
import MyGenericTask.MyClasses.StarCluster.PlanetarySystem;
import MyGenericTask.MyClasses.StarCluster.StarSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MagicBoxTest {

    MagicBox<StarSystem, PlanetarySystem, Planet> starCluster;

    @Before
    public void setUp() throws Exception {
        StarSystem kaliam =
                new StarSystem("Kaliam 12-234G", 2, 6);
        PlanetarySystem alphaKaliam =
                new PlanetarySystem("Alpha Kaliam", 6);
        Planet panirTreeOfKaliam =
                new Planet("Panir","Alpha Kaliam",2,4.23);
        starCluster = new MagicBox<>(kaliam, alphaKaliam, panirTreeOfKaliam);
    }

    @Test
    public void testMagicBoxOnStandardJavaObject() {
        /*
        Создаем два параметризированных объекта 'Магическая Коробка'
        - первый параметр - String;
        - второй параметр - Integer;
        - третий параметр - Float;
        !!! Необходимо обратить внимание на то, что параметры могут быть
        только ссылочного типа, либо обертки для простого типа, как в примере !!!
        */
        MagicBox<String, Integer, Float> box1 = new MagicBox<>("Str", 2, 3.5f);
        MagicBox<String, Integer, Float> box2 = new MagicBox<>("Str", 5, 6.5f);
        // Делаем мат. расчет, используя геттеры.
        float result = box1.getValueOne() + box1.getValueTwo() + box2.getValueOne() + box2.getValueTwo();
        // Проверяем эквивалентность полученного с ожидаемым
        assertEquals(17, result, 0.001);
    }

    @Test
    public void testMagicBoxOnNonStandardObjects() {
        /*
        В методе *.setUp() создали тестовые объекты.
        */
        // Делаем мат. расчет, используя геттеры.
        int result = starCluster.getKeyForMagicBox().getNumberOfStarInSystem() +
                     starCluster.getValueOne().getNumberOfPlanetInSystem() +
                     starCluster.getValueTwo().getNumberOfSatellite();
        // Проверяем эквивалентность полученного с ожидаемым
        assertEquals(10, result);
    }
}
