package ManualTestOfCollection;

import MyClasses.Discoverer;
import MyClasses.Planet;
import MyGenericСollectionСlasses.MyOwnHashMap;

import java.util.List;
import java.util.Set;

public class ManualTestMyOwnHashMapApp {
    public static void main(String[] args) {
        MyOwnHashMap<Discoverer, Planet> observatory = new MyOwnHashMap<>();

        Discoverer astronomer1 =
                new Discoverer(134,"Вася", "Вукин");
        Discoverer astronomer2 =
                new Discoverer(2543,"Даша", "Дукина");
        Discoverer astronomer3 =
                new Discoverer(4134,"Вася", "Вукин");
        Discoverer astronomer4 =
                new Discoverer(231,"Даша", "Дукина");
        Discoverer astronomer5 =
                new Discoverer(13433,"Вася", "Вукин");
        Discoverer astronomer6 =
                new Discoverer(2983,"Даша", "Дукина");
        Discoverer astronomer7 =
                new Discoverer(134223,"Вася", "Вукин");

        Planet mercury =
                new Planet("планета","Меркурий", 2439.7, 0);
        Planet venus =
                new Planet("планета","Венера", 6051.8,0);
        Planet earth =
                new Planet("планета","Земля", 6378.1,1);
        Planet mars =
                new Planet("планета","Марс", 3396.2, 2);
        Planet jupiter =
                new Planet("планета","Юпитер", 71492.1, 80);
        Planet saturn =
                new Planet("планета","Сатурн", 60268.4, 83);
        Planet uranus =
                new Planet("планета","Уран", 25559.5, 27);

        observatory.put(astronomer1, mercury);
        observatory.put(astronomer2,venus);
        observatory.put(astronomer3, earth);
        observatory.put(astronomer4,mars);
        observatory.put(astronomer5,jupiter);
        observatory.put(astronomer6,saturn);
        observatory.put(astronomer7,uranus);

        System.out.println("Размер коллекции -> " + observatory.size());
        System.out.println("--------- Выводим содержимое коллекции: ---------");
        System.out.println("Ключи (астрономы первооткрыватели): ");
        Set<Discoverer> keyOfMySet = observatory.keySet();
        keyOfMySet.forEach(System.out::println);
        System.out.println("Значения под ключами (планеты): ");
        List<Planet> valueOfMySet = observatory.values();
        keyOfMySet.forEach(System.out::println);
        System.out.println("--------- Удаляем элемент с ключом и выводим остатки коллекции ---------");
        observatory.remove(astronomer3);
        List<Planet> newListOfPlanet = observatory.values();
        newListOfPlanet.forEach(System.out::println);
        System.out.println("--------- Извлекаем элемент из коллекции по ключу ---------");
        System.out.println(observatory.get(astronomer4));

    }
}
