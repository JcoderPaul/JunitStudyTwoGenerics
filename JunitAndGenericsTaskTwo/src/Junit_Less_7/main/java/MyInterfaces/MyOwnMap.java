package MyInterfaces;
/*
Параметризируем нашу Map типами <K,V>
*/

import java.util.List;
import java.util.Set;

// Интерфейс описывающий способности нашего списка
public interface MyOwnMap<K, V> {
    void put(K ketOfElement, V value); // Метод загрузки данных в Map (ключ + объект)
    V get(K ketOfElement); // Метод извлечения объекта из коллекции по ключу
    Set<K> keySet(); // Метод возвращающий коллекцию ключей (не могут повторяться)
    List<V> values(); // Метод возвращающий список всех объектов под ключами (могут повторяться)
    boolean remove(K ketOfElement); // Метод удаляющий элемент из Map по ключу
    int size(); // Размер Map
    void clear(); // Полная очистка коллекции Map
}
