package MyInterfaces;
/*
Параметризируем нашу очередь типом <T>
*/

// Интерфейс описывающий способности нашей очереди
public interface MyOwnQueue<T> extends MyOwnCollection<T> {
    boolean add(T elementOfQueue); // Добавить элемент в очередь
    T peek(); // Возвращает без удаления элемент из начала очереди
    T poll(); // Возвращает с удалением элемент из начала очереди.
    T get(int index); // Получить элемент очереди по индексу (неспецифический метод)
    boolean removeAt(int index); // Удалить элемент по индексу (неспецифический метод)
    int size(); // Определяем размер нашей очереди
    void clear(); // Полностью чистим нашу очередь
    T element(); // Возвращает, но не удаляет, элемент из начала очереди.
}
