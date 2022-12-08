package MyGenericTask.MyClasses;

public class MagicBox <K, V1, V2>{
    private K keyForMagicBox;
    private V1 valueOne;
    private V2 valueTwo;

    public MagicBox(K keyForMagicBox, V1 valueOne, V2 valueTwo) {
        this.keyForMagicBox = keyForMagicBox;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    public K getKeyForMagicBox() {
        return keyForMagicBox;
    }

    public void setKeyForMagicBox(K keyForMagicBox) {
        this.keyForMagicBox = keyForMagicBox;
    }

    public V1 getValueOne() {
        return valueOne;
    }

    public void setValueOne(V1 valueOne) {
        this.valueOne = valueOne;
    }

    public V2 getValueTwo() {
        return valueTwo;
    }

    public void setValueTwo(V2 valueTwo) {
        this.valueTwo = valueTwo;
    }
}
