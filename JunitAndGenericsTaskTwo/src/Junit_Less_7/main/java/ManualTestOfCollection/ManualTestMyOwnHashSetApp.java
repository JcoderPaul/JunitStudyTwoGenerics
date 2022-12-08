package ManualTestOfCollection;

import MyClasses.Planet;
import MyGenericСollectionСlasses.MyOwnHashSet;

public class ManualTestMyOwnHashSetApp {
    public static void main(String[] args) {
        MyOwnHashSet<Planet> observatory = new MyOwnHashSet<>();

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
        System.out.println("--------- Выводим содержимое коллекции: ---------");
        Object[] mySetInArray = observatory.allHashSetElementToSimpleArray();
        for (int i = 0; i < mySetInArray.length; i++){
            System.out.println(mySetInArray[i]);
        }
        System.out.println("--------- Удаляем элемент 'Марс' по содержимому и выводим остатки коллекции ---------");
        observatory.remove(mars);
        observatory.forEach(System.out::println);
        System.out.println("--------- Извлекаем элемент из коллекции по если он есть ---------");
        System.out.println(observatory.contains(venus));
        System.out.println(observatory.contains(
                new Planet("планета","Баливклид", 25779.5, 17)));

    }
}
