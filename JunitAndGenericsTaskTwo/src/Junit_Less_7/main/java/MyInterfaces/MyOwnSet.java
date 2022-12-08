package MyInterfaces;

/*
Параметризируем нашу коллекцию типом <T>
*/

public interface MyOwnSet<T> extends MyOwnCollection<T> {
    // Добавляем элемент
    boolean add(T elementOfSet);
    // Удаляем элемент
    boolean remove(T elementOfSet);
    // Отображаем размер коллекции
    int size();
    // Удаляем все элементы коллекции
    void clear();
    // Возвращает true если переданный в аргументах элемент есть в коллекции
    boolean contains(T elementOfSet);
    // Возвращает true если наш SpaceSet пустой
    boolean isEmpty();
}
