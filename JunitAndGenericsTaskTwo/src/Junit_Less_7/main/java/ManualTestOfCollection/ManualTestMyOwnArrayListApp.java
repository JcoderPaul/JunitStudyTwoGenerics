package ManualTestOfCollection;

import MyClasses.Planet;
import MyGenericСollectionСlasses.MyOwnArrayList;

public class ManualTestMyOwnArrayListApp {
    public static void main(String[] args) {
        MyOwnArrayList<Planet> observatory = new MyOwnArrayList<>();

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

        observatory.add(mercury);
        observatory.add(venus);
        observatory.add(earth);
        observatory.add(mars);
        observatory.add(jupiter);
        observatory.add(saturn);
        observatory.add(uranus);

        System.out.println("Размер коллекции -> " + observatory.size());
        System.out.println("--------- Выводим содержимое коллекции встроенным методом ---------");
        Object[] arrayOfPlanet = observatory.listToSimpleArray();
        for (int i = 0; i < arrayOfPlanet.length; i++){
            System.out.println(arrayOfPlanet[i]);
        }
        System.out.println("--------- Удаляем элемент 'Земля' под индексом 2 и выводим остатки коллекции ---------");
        observatory.removeAt(2);
        observatory.forEach(System.out::println);
        System.out.println("--------- Вставляем элемент 'Земля' под индексом 2 обратно и выводим коллекцию ---------");
        observatory.add(earth,2);
        observatory.forEach(System.out::println);
        System.out.println("--------- Удаляем элемент 'Уран' по содержимому и выводим остатки коллекции ---------");
        observatory.remove(uranus);
        observatory.forEach(System.out::println);

    }
}
