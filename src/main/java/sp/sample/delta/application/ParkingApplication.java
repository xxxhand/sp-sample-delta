package sp.sample.delta.application;

import sp.sample.delta.domain.AbstractTrafficTool;
import sp.sample.delta.domain.ParkingSlot;
import sp.sample.delta.domain.ParkingSpace;
import sp.sample.delta.domain.enums.TrafficToolTypes;
import sp.sample.delta.domain.services.ParkingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void parking(List<AbstractTrafficTool> trafficTools) {
        System.out.println("=============== Start parking ===============");
        for (AbstractTrafficTool trafficTool : trafficTools) {
            try {
                ParkingSlot slot = this.parkingSpace.findOneSlot(trafficTool);
                trafficTool.parking();
                slot.parked(trafficTool);
                System.out.println(String.format("A %s has entered at %s and parked in parking space %s." +
                                " Remaining parking spaces for %s is %d",
                        trafficTool.getToolType().getDescription(),
                        trafficTool.getFormattedEnterTime(),
                        slot.getId(),
                        trafficTool.getToolType().getDescription(),
                        this.parkingSpace.availableSlotAmount(trafficTool.getToolType())
                ));

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("=============== End parking ===============");
    }

    public void leaving(List<ParkingSlot> slots) {
        System.out.println("=============== Start leaving ===============");
        for (ParkingSlot slot : slots) {
            AbstractTrafficTool leavingTool = slot.getTrafficTool();
            leavingTool.leaving();
            int fee = this.parkingService.calculateTotalFee(leavingTool);
            leavingTool.setTotalFee(fee);
            slot.leaved();

            System.out.println(String.format("A %s parked at parking space %s has left at %s" +
                            ". Total time parked is %s, for a total parking fee of %d" +
                            ". Available parking spaces for %s is %d",
                    slot.getToolType().getDescription(),
                    slot.getId(),
                    leavingTool.getFormattedLeftTime(),
                    "xx:xx",
                    fee,
                    slot.getToolType(),
                    this.parkingSpace.availableSlotAmount(slot.getToolType())
            ));
        }
        System.out.println("=============== End leaving ===============");
    }

    public void calculateAvailableSlotAmount() {
        System.out.println("=============== Start counting ===============");
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
        System.out.println("=============== End counting ===============");
    }

}
