package MyCuteCats;

import java.util.Random;

public class Serval extends Cat{
    private float servalWeight;
    public Serval() {
        Random rnd = new Random();
        servalWeight = (float)(rnd.nextInt(9)) + 3.5f;
    }
    @Override
    public float getWeight() {

        return servalWeight;
    }
}
