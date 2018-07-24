package sp.sample.delta.domain.services;

import sp.sample.delta.domain.AbstractTrafficTool;
import sp.sample.delta.domain.enums.TrafficToolTypes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ParkingService {
    private final Map<TrafficToolTypes, Integer> FEE_MAP = new HashMap<>();

    public ParkingService() {
        this.FEE_MAP.put(TrafficToolTypes.MOTOR, 10);
        this.FEE_MAP.put(TrafficToolTypes.CAR, 30);
        this.FEE_MAP.put(TrafficToolTypes.SMALL_BUS, 40);
    }

    public int calculateTotalFee(AbstractTrafficTool trafficTool) {
        Integer slotFee = this.FEE_MAP.get(trafficTool.getToolType());

        String parkingMinutes = String.valueOf(Duration.between(trafficTool.getEnterTime(), trafficTool.getLeftTime()).getSeconds());
        String basicCalculateAmount = "60";


        double totalHours = new BigDecimal(Double.valueOf(parkingMinutes) / Double.valueOf(basicCalculateAmount))
                .setScale(0, RoundingMode.CEILING)
                .doubleValue();

        if (totalHours == 0) {
            totalHours = 1;
        }


        return (int) (slotFee * totalHours);
    }
}
