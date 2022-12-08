package MyGenericTask.MyClasses.StarCluster;

import java.util.Objects;

public class Planet {
    private String nameOfPlanet;
    private String nameOfCentralStar;
    private int numberOfSatellite;

    private double massOfPlanet;

    public Planet(String nameOfPlanet, String nameOfCentralStar, int numberOfSatellite, double massOfPlanet) {
        this.nameOfPlanet = nameOfPlanet;
        this.nameOfCentralStar = nameOfCentralStar;
        this.numberOfSatellite = numberOfSatellite;
        this.massOfPlanet = massOfPlanet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return numberOfSatellite == planet.numberOfSatellite &&
               Double.compare(planet.massOfPlanet, massOfPlanet) == 0 &&
               Objects.equals(nameOfPlanet, planet.nameOfPlanet) &&
               Objects.equals(nameOfCentralStar, planet.nameOfCentralStar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfPlanet, nameOfCentralStar, numberOfSatellite, massOfPlanet);
    }

    @Override
    public String toString() {
        return "Planet: {" +
                "nameOfPlanet='" + nameOfPlanet + '\'' +
                ", nameOfCentralStar='" + nameOfCentralStar + '\'' +
                ", numberOfSatellite=" + numberOfSatellite +
                ", massOfPlanet=" + massOfPlanet +
                '}';
    }

    public String getNameOfPlanet() {
        return nameOfPlanet;
    }

    public String getNameOfCentralStar() {
        return nameOfCentralStar;
    }

    public int getNumberOfSatellite() {
        return numberOfSatellite;
    }

    public double getMassOfPlanet() {
        return massOfPlanet;
    }
}
