package MyCuteCats;

import java.util.Random;

public class Tiger extends Cat{
    private float tigerWeight;

    public Tiger() {
        Random rnd = new Random();
        tigerWeight = (float)(rnd.nextInt(210)) + 110.5f;
    }
    @Override
    public float getWeight() {
        return tigerWeight;
    }

}
