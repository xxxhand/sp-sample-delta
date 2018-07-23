package sp.sample.delta.domain.enums;

import java.util.Random;

/**
 * Created by hand on 2018/7/21.
 */
public enum TrafficToolTypes {
    MOTOR(1, "Motorcycle"),
    CAR(2, "Car"),
    SMALL_BUS(3, "Small bus")
    ;

    private int code;
    private String description;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private TrafficToolTypes(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static TrafficToolTypes randomType() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }
}
