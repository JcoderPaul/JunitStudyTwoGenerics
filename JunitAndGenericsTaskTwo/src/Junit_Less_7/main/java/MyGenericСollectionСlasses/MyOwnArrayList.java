package MyGenericСollectionСlasses;

import MyInterfaces.MyOwnList;

import java.util.Arrays;
import java.util.Iterator;
/*
Теперь наши коллекции стали более универсальными
в отличие от коллекций реализованных в разделе:
https://github.com/JcoderPaul/JunitStudy
*/
public class MyOwnArrayList<T> implements MyOwnList<T> {
    // Массив Object более универсален чем, скажем String[] или Integer[]
    private Object[] array = new Object[10];
    // Начальный размер ноль
    private int size = 0;
    /*
    Метод проверяет введенный индекс, он не может быть меньше нуля
    или больше размера списка иначе вылетит исключение.
    */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
    /*
    Получаем элемент списка по индексу теперь
    мы явно ожидаем некий объект класса T, чем
    бы он ни был.
    */
    @Override
    public T get(int index) {
        checkIndex(index);
        /*
        Возвращаем элемент массива под индексом index, но
        поскольку он Object мы кастомизируем его до <T>
        */
        return (T) array[index];
    }
    // Метод возвращает текущий размер списка
    @Override
    public int size() {
        return size;
    }
    // Убираем дублирование кода в методах *.add()
    private void increaseArray() {
        /*
        Если текущий размер списка больше или равен длине массива,
        то запускаем статический метод класса Arrays:
        static <T> T[] copyOf(T[] original, int newLength) - копируем
        указанный массив, усекая или дополняя нулями (при необходимости),
        чтобы копия имела указанную длину. В нашем случае увеличиваем
        текущий размер массива в 2-а раза.
        */
        if (size >= array.length) {
            /*
            Данный метод заменяет следующий код:
            ---------------------------------------------------
            Object[] newArray = new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            ---------------------------------------------------
            */
            array = Arrays.copyOf(array, array.length * 2);
        }
    }
    // Добавляем элемент в список
    @Override
    public boolean add(T elementOfList) {
        // При необходимости увеличиваем список
        increaseArray();
        boolean successfulAddition = false;
        /*
        Если размер списка меньше длины массива вставляем элемент в список
        */
        array[size] = elementOfList;
        if (array[size] != null){
            successfulAddition = true;
        }
        /*
        И увеличиваем его размер на +1 (нужно помнить, что у списка
        (у объекта коллекция) есть емкость capacity, а есть длина
        length и это разные вещи)
        */
        size++;
        return successfulAddition;
    }

    @Override
    public boolean add(T elementOfList, int index) {
        increaseArray();
        boolean successfulAddition = false;
        // Проверяем не ошиблись ли мы со значением index
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        /*
        Метод класса System, позволяет: static void arraycopy
        (Object src, int srcPos, Object dest, int destPos, int length) -
        копировать массив из указанного исходного массива 'src',
        начиная с указанной позиции 'srcPos', в указанную позицию
        целевого массива 'dest', т.е. мы указываем в какой массив
        хотим переместить элементы и на какую позицию 'destPos' и
        самое главное мы указываем сколько элементов нужно
        переместить 'length'.

        У нас массив источник является так же и массивом приемником,
        позиция с которой начинаем перемещать элементы полученный
        index, а т.к. мы текущий элемент смещаем вправо, то это
        позиция index + 1, ну и количество переносимых элементов,
        это наш размер списка минус значение индекса.

        В данном случае мы сдвигаем часть массива, вправо от
        вставляемого элемента.

        Данная строка заменяет код (двигаемся с конца):
        for(int i = size; i > index; i--){
            array[i] = array[i - 1];
        }
        */
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = elementOfList;
        if (array[index] != null){
            successfulAddition = true;
        }
        size++;
        return successfulAddition;
    }
    // Удаляем элемент коллекции по индексу
    @Override
    public boolean removeAt(int index) {
        // Проверяем, есть ли такой индекс
        checkIndex(index);
        // Перебираем список начиная с i-го элемента, и до size - 1
        for (int i = index; i < size - 1; i++) {
            /*
            Перемещаем на место текущего i - го элемента массива
            следующий за ним i + 1. Т.е. смещаем элементы массива
            влево. Если бы цикл работал от i до size, то мы вылетели
            бы за границы списка.
            */
            array[i] = array[i + 1];
        }
        // Уменьшаем размер списка на -1
        size--;
        // Возвращаем true
        return true;
    }

    // Удаляем элемент коллекции по содержимому
    @Override
    public boolean remove(T elementOfList) {
        // Перебираем элементы списка в цикле, от 0 до размера size
        for (int i = 0; i < size; i++) {
            /*
            Если i-ый элемент списка совпал с переданным
            в аргументах объектом, удаляем его используя
            метод удаления по полученному индексу.
            */
            if (array[i].equals(elementOfList)) {
                /*
                Если тут все прошло нормально и элемент нашелся,
                то он будет удален, а текущий метод *.remove()
                вернет true, вслед за методом *.removeAt(). Если
                нет, то текущий метод вернет false.
                */
                return removeAt(i);
            }
        }
        return false;
    }
    // Метод полностью зачищает наш список, создав новый массив
    @Override
    public void clear() {
        array = new Object[16];
        size = 0;
    }

    @Override
    public boolean contains(T elementOfList) {
        Object[] currentArray = this.array; // Обращаемся к текущему массиву
        T elementForFind = elementOfList; // Выделяем искомый объект
        boolean contains = false; // Определяем возвращаемое состояние по умолчанию
          for (int i = 0; i < this.array.length; i++) {
                if (currentArray[i] != null) {
                   if (elementForFind.equals(currentArray[i])){
                            // Искомый элемент найден, флаг - true, поиск окончен.
                            contains = true;
                            if (contains){
                                break;
                            }
                   }
                }
          }
        return contains; // Возвращаем результат
    }

    public Object[] listToSimpleArray() {
        Object[] arrayForView = new Object[size];
        for (int i = 0; i < size; i++){
            if(!array[i].equals(null)){
                arrayForView[i] = array[i];
            }
        }
        return arrayForView;
    }
    /*
    Для того чтобы у нас была возможность пройтись (перебрать)
    по всем элементам коллекции, наш супер-интерфейс
    SpaceObjectCollection подписался на интерфейс Iterable и
    теперь мы должны во всех наших самописных коллекциях
    реализовать метод *.iterator(), который в свою очередь
    должен вернуть объект Iterator реализующий три метода:
    - *.hasNext() - возвращает true при наличии следующего элемента в коллекции,
    - *.next() - возвращает текущий элемент коллекции (вызывать его можно только
                 после того как получили положительный ответ от *.hasNext() иначе
                 выброс исключения),
    - *.remove() - удаляет текущий элемент коллекции, при его наличии.
    */
    @Override
    public Iterator<T> iterator() {
        // Поскольку нужно вернуть Iterator, создаем его анонимный экземпляр
        return new Iterator<T>() {
            /*
            На старте счетчик на нуле, т.е. обращение
            к нулевому элементу коллекции при наличии.
            */
            int index = 0;
            /*
            Если значение index меньше размера
            коллекции, то в ней еще есть элементы
            и тогда возвращаем true.
            */
            @Override
            public boolean hasNext() {
                return index < size;
            }
            /*
            Короткий код хорошо, когда рука набита и глаз наметан.
            Длинный код для реализации метода *.next() выглядит так:
            --------------------------------------------------------
                SpaceObject currentSpaceObject = array[index];
                index++;
                return currentSpaceObject;
            --------------------------------------------------------
            Его короткий эквивалент в теле метода.
            */
            @Override
            public T next() {
                return (T) array[index++];
            }
            /*
            Основная хитрость в методе *.remove() это получение индекса
            удаляемого из текущей коллекции элемента. Сначала вызываем
            текущую коллекцию, затем ее метод 'удалить по индексу' (в
            текущей коллекции он есть), а вот в качестве аргумента
            поступает предекрементированный индекс.

            Метод *.next() сразу после возвращения текущего элемента
            коллекции постинкрементирует индекс и значит, чтобы обратиться
            к текущему элементу, сразу после метода *.next() мы должны,
            откатить значение запрашиваемого индекса на шаг назад.
            */
            @Override
            public void remove() {
                MyOwnArrayList.this.removeAt(--index);
            }
        };
    }
}