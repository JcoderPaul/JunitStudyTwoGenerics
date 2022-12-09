package GenericClasses;

import MyCuteCats.Cat;

import java.util.ArrayList;
import java.util.List;
// Класс зоопарк параметризирован <T типом наследником Cat>
public class Zoo <T extends Cat> {
        // List параметризирован тем же типом T
        private List<T> cats;
        public Zoo() {
            cats = new ArrayList<>();
        }
        public float getWeightOfAllCatsInZoo() {
            float result = 0;
            for (T cat : cats) {
                result += cat.getWeight();
            }
            return result;
        }

        public void add(T cat) {
            cats.add(cat);
        }
        /*
        А вот тут интересный момент, если сам класс Zoo параметризирован <T типом наследником Cat>,
        то в данном случае используется конструкция <?> 'дикие карты' - wildcard (джокер, неожиданный),
        т.е. в данной ситуации совершенно любой тип и необязательно <T> и его наследники. Подробные уроки
        см. https://github.com/JcoderPaul/JavaExtended-1-6/tree/master/Less_6_OOP_GenericsWildCard
        */
        public int compare(Zoo<?> another) {
            return Float.compare(getWeightOfAllCatsInZoo(), another.getWeightOfAllCatsInZoo());
        }
        /*
        Данный метод параметризирован <U наследником Cat>, а вот его аргументы параметризированы
        двумя разновидностями wildcard:
        - <? extends U> - любой тип наследник U;
        - <? super U> - любой тип родитель U;

        Если мы укажем Zoo<Integer> source - то тут явно указан тип Integer, и данный класс может
        работать только с Integer, если укажем Zoo<Number> source, то класс может работать только
        с Number, хотя Integer наследник Number. Но если мы укажем Zoo<? extends Number>, то класс
        сможет использовать в работе всех наследников Number. Если же применить конструкцию
        Zoo<? super Number>, то всех родителей Number.
        !!! И естественно родитель не обязан знать о структуре наследника, в то время как наследник
        точно знает о содержимом родителя !!!
        Отсюда вытекает особенность передачи данных в <параметризированных типах> от источника к приемнику,
        в разрезе коллекций это просматривается явно, наследник может стать источником и отдавать данные,
        родитель может принимать данные от наследников.
        */
        public static <U extends Cat> void transfer(Zoo<? extends U> source, Zoo<? super U> destination) {
            /*
            Вызываем метод *.addAll() - 'добавить все',
            у коллекции <родителя U>, в параметрах
            передаем коллекцию <наследника U>
            */
            destination.cats.addAll(source.cats);
            /*
            Очистили коллекцию источник
            */
            source.cats.clear();
        }

        public List<T> getCats() {
            return cats;
        }

        public int getSizeOfCatsZoo() {
            return cats.size();
        }
}
