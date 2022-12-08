package MyClasses;

import java.util.Objects;

/*
Класс первооткрыватель будет ключом в нашей Map.
Основным требованием к ключам коллекций Map идет
их иммутабильность (immutable). Т.е. полная или
максимально затрудненная для пользователя
возможность изменения значения key, т.к. это
затруднит или полностью сведет 'на нет' потерю
значения под ключом - 'value'.
*/
public class Discoverer {
    /*
    Делаем поля final, что позволит ограничить
    изменение hash и сделает наши ключи
    неизменяемыми (условно надежными).
    */
    private final int idOfSpaceObject;
    private final String firstNameOfDiscoverer;
    private final String lastNameOfDiscoverer;

    public Discoverer(int idOfSpaceObject, String firstNameOfDiscoverer, String lastNameOfDiscoverer) {
        this.idOfSpaceObject = idOfSpaceObject;
        this.firstNameOfDiscoverer = firstNameOfDiscoverer;
        this.lastNameOfDiscoverer = lastNameOfDiscoverer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discoverer that = (Discoverer) o;
        return idOfSpaceObject == that.idOfSpaceObject &&
               Objects.equals(firstNameOfDiscoverer, that.firstNameOfDiscoverer) &&
               Objects.equals(lastNameOfDiscoverer, that.lastNameOfDiscoverer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfSpaceObject, firstNameOfDiscoverer, lastNameOfDiscoverer);
    }

    @Override
    public String toString() {
        return "Discoverer: {" +
                "idOfSpaceObject=" + idOfSpaceObject +
                ", firstNameOfDiscoverer='" + firstNameOfDiscoverer + '\'' +
                ", lastNameOfDiscoverer='" + lastNameOfDiscoverer + '\'' +
                '}';
    }

    public int getIdOfSpaceObject() {
        return idOfSpaceObject;
    }
    public String getFirstNameOfDiscoverer() {
        return firstNameOfDiscoverer;
    }
    public String getLastNameOfDiscoverer() {
        return lastNameOfDiscoverer;
    }
}
