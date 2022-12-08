package MyGenericTask.MyClasses.StarCluster;

import java.util.Objects;

public class PlanetarySystem {
    private String nameOfPlanetarySystem;
    private int numberOfPlanetInSystem;

    public PlanetarySystem(String nameOfPlanetarySystem, int numberOfPlanetInSystem) {
        this.nameOfPlanetarySystem = nameOfPlanetarySystem;
        this.numberOfPlanetInSystem = numberOfPlanetInSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetarySystem that = (PlanetarySystem) o;
        return numberOfPlanetInSystem == that.numberOfPlanetInSystem &&
               Objects.equals(nameOfPlanetarySystem, that.nameOfPlanetarySystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfPlanetarySystem, numberOfPlanetInSystem);
    }

    @Override
    public String toString() {
        return "PlanetarySystem: {" +
                "nameOfPlanetarySystem='" + nameOfPlanetarySystem + '\'' +
                ", numberOfPlanetInSystem=" + numberOfPlanetInSystem +
                '}';
    }

    public String getNameOfPlanetarySystem() {
        return nameOfPlanetarySystem;
    }

    public int getNumberOfPlanetInSystem() {
        return numberOfPlanetInSystem;
    }
}
