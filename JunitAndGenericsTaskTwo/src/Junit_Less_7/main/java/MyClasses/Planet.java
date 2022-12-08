package MyClasses;

import java.util.Objects;

public class Planet {
    private String kindOfSpaceObject;
    private String nameOfSpaceObject;
    private double radius;
    private int countOfSatellite;

    public Planet(String kindOfSpaceObject, String nameOfSpaceObject, double radius, int countOfSatellite) {
        this.kindOfSpaceObject = kindOfSpaceObject;
        this.nameOfSpaceObject = nameOfSpaceObject;
        this.radius = radius;
        this.countOfSatellite = countOfSatellite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Double.compare(planet.radius, radius) == 0 &&
               countOfSatellite == planet.countOfSatellite &&
               Objects.equals(kindOfSpaceObject, planet.kindOfSpaceObject) &&
               Objects.equals(nameOfSpaceObject, planet.nameOfSpaceObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kindOfSpaceObject, nameOfSpaceObject, radius, countOfSatellite);
    }

    @Override
    public String toString() {
        return "Planet: {" +
                "kindOfSpaceObject= '" + kindOfSpaceObject + '\'' +
                ", nameOfSpaceObject= '" + nameOfSpaceObject + '\'' +
                ", radius= " + radius +
                ", countOfSatellite= " + countOfSatellite +
                '}';
    }

    public String getKindOfSpaceObject() {
        return kindOfSpaceObject;
    }

    public void setKindOfSpaceObject(String kindOfSpaceObject) {
        this.kindOfSpaceObject = kindOfSpaceObject;
    }

    public String getNameOfSpaceObject() {
        return nameOfSpaceObject;
    }

    public void setNameOfSpaceObject(String nameOfSpaceObject) {
        this.nameOfSpaceObject = nameOfSpaceObject;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getCountOfSatellite() {
        return countOfSatellite;
    }

    public void setCountOfSatellite(int countOfSatellite) {
        this.countOfSatellite = countOfSatellite;
    }
}
