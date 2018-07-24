package sp.sample.delta.application;

import sp.sample.delta.domain.AbstractTrafficTool;
import sp.sample.delta.domain.ParkingSlot;
import sp.sample.delta.domain.ParkingSpace;
import sp.sample.delta.domain.enums.TrafficToolTypes;
import sp.sample.delta.domain.services.ParkingService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParkingApplication {
    private ParkingSpace parkingSpace;
    private ParkingService parkingService;


    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public List<ParkingSlot> getAllSlots() {
        return this.parkingSpace.getSlots();
    }
    public ParkingApplication() {
        this.parkingSpace = new ParkingSpace();
        this.parkingService = new ParkingService();
    }

    public void parking(AbstractTrafficTool trafficTool) {
        try {
            ParkingSlot slot = this.parkingSpace.findOneSlot(trafficTool);
            trafficTool.parking();
            slot.parked(trafficTool);
            System.out.println(String.format("A %s has entered at %s and parked in parking space %s." +
                            " Remaining parking spaces for %s is %d",
                    trafficTool.getToolType().getDescription(),
                    trafficTool.getEnterTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                    slot.getId(),
                    trafficTool.getToolType().getDescription(),
                    this.parkingSpace.availableSlotAmount(trafficTool.getToolType())
            ));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void leaving(ParkingSlot slot) {
        AbstractTrafficTool leavingTool = slot.getTrafficTool();
        leavingTool.leaving();
        int fee = this.parkingService.calculateTotalFee(leavingTool);
        leavingTool.setTotalFee(fee);
        slot.leaved();


        // count total hours and minutes - start
        String parkingTime = "";
        String totalMins = String.valueOf(Duration.between(leavingTool.getEnterTime(), leavingTool.getLeftTime()).getSeconds());
        String basicCalculateAmount = "60";
        String var1 = new BigDecimal(Double.valueOf(totalMins) / Double.valueOf(basicCalculateAmount))
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .toString();

        String[] var2 = var1.split("\\.");
        String allHours = var2[0];
        if (var2[1].equals("00")) {
            parkingTime = allHours + ":00";
        } else {
            var2[1] = "0." + var2[1];
            String var3 = new BigDecimal(Double.valueOf(var2[1]) * Double.valueOf(basicCalculateAmount))
                    .setScale(0, BigDecimal.ROUND_HALF_UP)
                    .toString();

            parkingTime = allHours + ":" + var3;

        }
        // count total hours and minutes- end

        System.out.println(String.format("A %s parked at parking space %s has left at %s" +
                        ". Total time parked is %s, for a total parking fee of %d" +
                        ". Available parking spaces for %s is %d",
                slot.getToolType().getDescription(),
                slot.getId(),
                leavingTool.getLeftTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                parkingTime,
                fee,
                slot.getToolType().getDescription(),
                this.parkingSpace.availableSlotAmount(slot.getToolType())
        ));
    }

    public void calculateAvailableSlotAmount() {
        StringBuilder sb = new StringBuilder("Available parking slots");
        for (TrafficToolTypes toolType: TrafficToolTypes.values()) {
            sb.append(" for ");
//            switch (toolType) {
//                case MOTOR:
//                    sb.append("motorcycles");
//                    break;
//                case CAR:
//                    sb.append("cars");
//                case SMALL_BUS:
//                    sb.append("buses");
//                    break;
//            }
            sb.append(toolType.getDescription());
            sb.append(": ");
            sb.append(this.parkingSpace.availableSlotAmount(toolType));
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));

        System.out.println(sb.toString());
    }

}
