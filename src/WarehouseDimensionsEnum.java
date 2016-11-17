/**
 * Created by Mitziu on 11/17/16.
 * Enum to be able to manipulate dimensions from one file
 */
public enum WarehouseDimensionsEnum {

    WIDTH(160), HEIGHT(100);

    private int value;

    WarehouseDimensionsEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
