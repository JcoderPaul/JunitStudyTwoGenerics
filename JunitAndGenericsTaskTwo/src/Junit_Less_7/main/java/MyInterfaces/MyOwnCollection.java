package MyInterfaces;
/*
В данном примере мы пытаемся создать свою
собственную универсальную коллекцию,
вернее интерфейс позволяющий в дальнейшем
создать класс, реализующий универсальную
коллекцию способную хранить не только
объекты 'космическое тело', как это было
в прошлых примерах, а любые из возможных.
*/

/*
Добавление синтаксической конструкции <T>,
как в предыдущем уроке, намекает среде
разработки, что наш интерфейс в качестве
'рабочего класса' может принимать любой
объект класса Object, с этим же классом
будет работать и интерфейс Iterable.
*/
public interface MyOwnCollection<T> extends Iterable<T>{
        /*
        Поскольку наша коллекция теперь может принимать в качестве
        объектов любой из доступных, то наши методы должны принимать
        объекты именно этого типа.

        Т.е. Java на место T подставит и Integer и String и т.д.,
        см. предыдущий пример или раздел про Generics в
        https://github.com/JcoderPaul/JavaExtended-1-6
        */
        boolean add(T elementOfCollection);

        boolean remove(T elementOfCollection);

        int size();

        void clear();

        boolean contains(T elementOfCollection);
}