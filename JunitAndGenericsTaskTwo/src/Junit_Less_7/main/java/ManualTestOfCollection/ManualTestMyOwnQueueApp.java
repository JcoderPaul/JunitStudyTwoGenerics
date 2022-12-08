package ManualTestOfCollection;

import MyClasses.Planet;
import MyGenericСollectionСlasses.MyOwnSimpleQueue;

public class ManualTestMyOwnQueueApp {
    public static void main(String[] args) {
        MyOwnSimpleQueue<Planet> observatory = new MyOwnSimpleQueue<>();

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
        Object[] myQueueToArray = observatory.queueToSimpleArray();
        for (int i = 0; i < myQueueToArray.length; i++){
            System.out.println(myQueueToArray[i]);
        }
        System.out.println("--------- Возвращаем элемент из начала очереди без удаления ---------");
        System.out.println(observatory.peek());
        System.out.println("Размер коллекции -> " + observatory.size());
        System.out.println("--------- Извлекаем элемент из начала очереди с удалением ---------");
        System.out.println(observatory.poll());
        System.out.println("Размер коллекции -> " + observatory.size());
        System.out.println("--------- В очереди сейчас ---------");
        observatory.forEach(System.out::println);
    }
}
