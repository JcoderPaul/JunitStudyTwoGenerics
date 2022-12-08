package MyGenericTask.MyClasses.StarCluster;

import java.util.Objects;

public class StarSystem {
    private String nameOfStarSystem;
    private int numberOfStarInSystem;
    private int numberOfPlanetInSystem;

    public StarSystem(String nameOfStarSystem, int numberOfStarInSystem, int numberOfPlanetInSystem) {
        this.nameOfStarSystem = nameOfStarSystem;
        this.numberOfStarInSystem = numberOfStarInSystem;
        this.numberOfPlanetInSystem = numberOfPlanetInSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarSystem that = (StarSystem) o;
        return numberOfStarInSystem == that.numberOfStarInSystem &&
               numberOfPlanetInSystem == that.numberOfPlanetInSystem &&
               Objects.equals(nameOfStarSystem, that.nameOfStarSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfStarSystem, numberOfStarInSystem, numberOfPlanetInSystem);
    }

    @Override
    public String toString() {
        return "StarSystem: {" +
                "nameOfStarSystem='" + nameOfStarSystem + '\'' +
                ", numberOfStarInSystem=" + numberOfStarInSystem +
                ", numberOfPlanetInSystem=" + numberOfPlanetInSystem +
                '}';
    }

    public String getNameOfStarSystem() {
        return nameOfStarSystem;
    }

    public int getNumberOfStarInSystem() {
        return numberOfStarInSystem;
    }

    public int getNumberOfPlanetInSystem() {
        return numberOfPlanetInSystem;
    }
}
