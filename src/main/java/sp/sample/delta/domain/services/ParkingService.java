package sp.sample.delta.domain.services;

import sp.sample.delta.domain.AbstractTrafficTool;
import sp.sample.delta.domain.enums.TrafficToolTypes;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
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

        long parkingMinutes = Duration.between(trafficTool.getEnterTime(), trafficTool.getLeftTime()).toMinutes();
        double totalHours = new BigDecimal(parkingMinutes / 60)
                .setScale(1, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        if (totalHours == 0) {
            totalHours = 1;
        }



        return (int) (slotFee * totalHours);
    }
}
