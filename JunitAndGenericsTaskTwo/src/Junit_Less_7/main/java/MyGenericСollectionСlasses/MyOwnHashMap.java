package MyGenericСollectionСlasses;
/*
Наш класс подписанный на интерфейс SpaceObjectMap
и реализующий его методы. Естественно это упрощенная
версия уже реализованной в Java коллекции Map.
*/

import MyInterfaces.MyOwnMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyOwnHashMap<K, V> implements MyOwnMap<K, V> {
    // Начальная емкость коллекции Map
    private static final int INITIAL_CAPACITY = 16;
    // Коэффициент загрузки, при котором будет удвоение внутренней коллекции Entry
    private static final double LOAD_FACTOR = 0.75;
    // Основная коллекция Entry объектов для хранения пары <ключ, значение>
    private Object[] array = new Object[INITIAL_CAPACITY];
    // Состояние счетчика элементов коллекции на старте
    private int size = 0;

    /*
    Метод помещающий пару <key, vol> в коллекцию.
    Ключом в нашей коллекции будет объект класса
    Discoverer, а содержимым под ключом объект
    класса SpaceObject. Public метод доступный
    пользователю для добавления элементов в
    коллекцию Map.
    */
    @Override
    public void put(K key, V value) {
        /*
        Если текущий размер коллекции Map больше или
        равен значению 'длина массива Entry' * 0.75,
        то запускаем метод увеличивающий размер текущей
        коллекции в два раза.
        */
        if (size >= (array.length * LOAD_FACTOR)) {
            increaseArray();
        }
        /*
        Применяем private метод *.put() в котором
        кроме загрузки элемента в коллекцию Map
        происходит перерасчет позиций в случае
        изменения размеров внутреннего массива.
        */
        boolean put = put(key, value, array);
        if (put) {
            /*
            Если элемент, пара <ключ, значение>
            размещены в текущей коллекции Map
            увеличиваем размер коллекции +1
            */
            size++;
        }
    }

    // Закрытый метод для заполнения данной коллекции Map элементами
    private boolean put(K key, V value, Object[] dst) {
        /*
        Рассчитываем значение ключа исходя из хэша
        key (или объекта Discoverer)
        */
        int position = getElementPosition(key, dst.length);
        /*
        Создаем переменную existedElement и передаем в нее
        элемент из внутренней коллекции Entry[в позиции - position]
        */
        Entry existedElement = (Entry) dst[position];
        // Если текущая ячейка массива null то ...
        if (existedElement == null) {
            /*
            ... создаем новый объект Entry и передаем в него
            значения key, value и ссылку на следующий объект
            Entry, пока нулевой.
            */
            Entry entry = new Entry(key, value, null);
            // Помещаем созданный объект в пустую ячейку внутреннего массива Entry
            dst[position] = entry;
            // Объект помешен успешно, возвращаем true
            return true;
        } else {
            /*
            Если текущая ячейка массива не null, то начинаем
            перебирать элементы односвязного списка в данной
            ячейке массива Entry.

            Данный список в ячейке массива коллекции Map или
            набор элементов, называют бакет.
            */
            while (true) {
                // Проверяем значение key, если оно совпадает с текущим ...
                if (existedElement.key.equals(key)) {
                    /*
                    Заменяем значение value под данным ключом,
                    т.е. ключ не изменился, а вот значение под
                    ним да (перезаписалось).
                    */
                    existedElement.value = value;
                    /*
                    Возвращаем false, т.к. в коллекции не появилась
                    новая элементная пара Entry <key, value> и выходим
                    из цикла.
                    */
                    return false;
                }
                // Если следующий элемент бакета null, т.е. его нет.
                if (existedElement.next == null) {
                    /*
                    Помещаем туда вновь созданный объект Entry
                    с парой <key, value> и ссылкой на следующий
                    пустой элемент односвязного списка.
                    */
                    existedElement.next = new Entry(key, value, null);
                    // Элемент удачно добавлен - возвращаем true
                    return true;
                }
                /*
                Переназначаем ссылку на следующий элемент
                односвязного списка, до тех пор, пока мы
                не добавим новый элемент в коллекцию или
                не перезапишем старый (т.е. ключ останется
                старым, а значение value новым).
                */
                existedElement = existedElement.next;
            }
        }
    }

    // Метод возвращающий значение value под ключом key
    @Override
    public V get(K key) {
        // Вычисляем позицию искомого ключа
        int position = getElementPosition(key, array.length);
        /*
        Извлекаем из внутреннего Entry массива
        элемент по значению полученной position.
        По факту мы сразу получили ссылку на
        головной элемент односвязного списка - бакета.
        */
        Entry existedElement = (Entry) array[position];
        /*
        Если в текущей ячейке массива есть элемент, то...
        */
        while (existedElement != null) {
            //... сравниваем полученный ключ с ключом текущего элемента
            if (existedElement.key.equals(key)) {
                // Значение ключа совпало - возвращаем объект value
                return existedElement.value;
            }
            /*
            Значение ключа не совпало, переназначаем ссылку
            временного объекта Entry на следующий объект бакета
            и продолжаем поиск.
            */
            existedElement = existedElement.next;
        }
        // Если ничего не нашли возвращаем null
        return null;
    }

    /*
    Т.к. ключи по определению оригинальные, т.е. не
    повторяются, то мы можем их загрузить в HashSet.
    Как и в следующем методе нашей коллекции используем,
    для простоты, готовую в Java реализацию коллекции
    HashSet/
    */
    @Override
    public Set<K> keySet() {
        // Создаем результирующую коллекцию всех ключей
        Set<K> result = new HashSet<>();
        // Перебираем элементы внутренней коллекции
        for (Object object : array) {
            // Через временную переменную ищем не пустые элементы коллекции
            Entry existedElement = (Entry) object;
            while (existedElement != null) {
                // Элемент Entry не пустой извлекаем его key и помещаем в коллекцию Set
                result.add(existedElement.key);
                // Переназначаем ссылку на следующий элемент нашей текущей коллекции
                existedElement = existedElement.next;
            }
        }
        // Возвращаем коллекцию всех найденных ключей
        return result;
    }

    /*
    Получаем все значения value в отдельную
    коллекцию. Используем готовую реализацию
    интерфейса List из библиотеки Java.
    */
    @Override
    public List<V> values() {
        // Создаем список
        List<V> result = new ArrayList<>();
        // Перебираем элементы внутреннего массива Entry
        for (Object object : array) {
            // Загружаем временную переменную очередным элементом массива
            Entry existedElement = (Entry) object;
            // Перебираем в цикле непустые элементы
            while (existedElement != null) {
                // Помещаем значения value в ArrayList
                result.add(existedElement.value);
                // Переназначаем ссылку на следующий объект
                existedElement = existedElement.next;
            }
        }
        // Возвращаем полученный список значений value
        return result;
    }

    // Удаляем элемент коллекции по ключу
    @Override
    public boolean remove(K key) {
        // Вычисляем позицию в массиве
        int position = getElementPosition(key, array.length);
        /*
        Извлекаем элемент из массива по номеру позиции,
        т.е. голову односвязного списка или бакета.
        */
        Entry existedElement = (Entry) array[position];
        // Если он не нулевой и если текущее значение ключа совпало с полученным
        if (existedElement != null && existedElement.key.equals(key)) {
            // Помещаем в текущую позицию, следующий в цепочке элемент
            array[position] = existedElement.next;
            // Уменьшаем размер коллекции
            size--;
            // Возвращаем true - объект удален
            return true;
        } else {
            // Если значение ключа не совпало с искомым, ...
            while (existedElement != null) {
                // ... то в цикле перебираем элементы односвязного списка
                Entry nextElement = existedElement.next;
                // Извлекаем ссылку на следующий элемент списка и если она null
                if (nextElement == null) {
                    // Возвращаем false, т.к. элемент не удален и выходим из цикла
                    return false;
                }
                // Если значение искомого ключа совпало с текущим то...
                if (nextElement.key.equals(key)) {
                    /*
                    Переназначаем ссылку на следующий элемент,
                    как бы вырезая удаленный элемент из Map.
                    */
                    existedElement.next = nextElement.next;
                    size--; // Уменьшаем размер коллекции
                    return true; // Возвращаем true и выходим из цикла
                }
                /*
                Если текущий элемент не null и его ключ
                не совпадает с искомым ключом, то
                переназначаем ссылку на следующий объект
                Entry и продолжаем поиск до успеха или
                провала поиска по ключу.
                */
                existedElement = existedElement.next;
            }
        }
        /*
        Если первый элемент бакета и последующие
        его элементы не совпали по значению ключа,
        значит удалить элемент не удалось и
        возвращаем false, а так же не изменяем
        значение size.
        */
        return false;
    }

    // Получаем размер коллекции
    @Override
    public int size() {
        return size;
    }

    // Обнуляем коллекцию
    @Override
    public void clear() {
        array = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /*
    Метод рассчитывает позицию элемента в
    коллекции Map на основе hashCode ключа.
    */
    private int getElementPosition(K key, int arrayLength) {
        return Math.abs(key.hashCode() % arrayLength);
    }

    /*
    Метод увеличивающий размер массива,
    на базе которого построена наша Map.
    */
    private void increaseArray() {
        // Создаем новый массив Entry с размером вдвое больше чем был
        Object[] newArray = new Object[array.length * 2];
        // Перебираем в цикле наш текущий массив, для переноса текущих данных
        for (Object object : array) {
            // Создаем промежуточную переменную 'существующий элемент'
            Entry existedElement = (Entry) object;
            // Проверяем на null текущий элемент Entry
            while (existedElement != null) {
                /*
                Если объект Entry не нулевой, то размещаем
                его в новый увеличенный массив.
                */
                put(existedElement.key, existedElement.value, newArray);
                // Переназначаем текущий элемент на следующий в бакете
                existedElement = existedElement.next;
                /*
                Так шаг за шагом мы перебираем весь массив и
                все элементы в односвязных списках - бакетах из
                каждой ячейки.
                */
            }
        }
        // Перезаписываем старый массив новым.
        array = newArray;
    }

    /*
    Объект Entry применяемы для хранения пары <key, value>
    во внутреннем массиве (hash таблице) коллекции Map.

    Делаем его nonstatic, в отличие от коллекции заточенной
    исключительно под 'космические объекты', как в примере:
    https://github.com/JcoderPaul/JunitStudy/tree/master/JunitWithCollectionTaskFour
    */
    private class Entry {
        private K key; // Значение ключа
        private V value; // Объект для хранения
        private Entry next; // Ссылка на следующий объект в односвязном списке (бакете)

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}