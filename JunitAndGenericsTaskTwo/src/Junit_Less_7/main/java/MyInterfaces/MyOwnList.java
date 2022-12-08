package MyInterfaces;
/*
Параметризируем нашу коллекцию типом <T>
*/

// Интерфейс описывающий способности нашего списка
public interface MyOwnList<T> extends MyOwnCollection<T> {
    T get(int index); // Получить элемент списка по индексу
    boolean add(T elementOfList, int index); // Добавить элемент в определенное место списка
    boolean removeAt(int index); // Удалить элемент по индексу
    boolean add(T elementOfList); // Добавить элемент в определенное место списка
    boolean remove(T elementOfList); // Удалить элемент
    int size(); // Определяем размер нашего списка
    void clear(); // Полностью чистим наш список
}
