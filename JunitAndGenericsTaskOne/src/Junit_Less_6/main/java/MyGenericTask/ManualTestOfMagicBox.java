package MyGenericTask;

import MyGenericTask.MyClasses.MagicBox;
import MyGenericTask.MyClasses.StarCluster.Planet;
import MyGenericTask.MyClasses.StarCluster.PlanetarySystem;
import MyGenericTask.MyClasses.StarCluster.StarSystem;

public class ManualTestOfMagicBox {
    public static void main(String[] args) {
        StarSystem kaliam =
                new StarSystem("Kaliam 12-234G", 2, 6);
        PlanetarySystem alphaKaliam =
                new PlanetarySystem("Alpha Kaliam", 6);
        Planet panirTreeOfKaliam =
                new Planet("Panir","Alpha Kaliam",2,4.23);
        MagicBox<StarSystem, PlanetarySystem, Planet> starCluster =
                new MagicBox<>(kaliam, alphaKaliam, panirTreeOfKaliam);

        System.out.println(starCluster.getKeyForMagicBox());
        System.out.println(starCluster.getValueOne());
        System.out.println(starCluster.getValueTwo());

        System.out.println(starCluster.getKeyForMagicBox().getNumberOfStarInSystem());
        System.out.println(starCluster.getValueOne().getNumberOfPlanetInSystem());
        System.out.println(starCluster.getValueTwo().getNumberOfSatellite());
    }
}
