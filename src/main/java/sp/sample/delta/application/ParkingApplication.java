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

    public Map<TrafficToolTypes, Integer> getAvailableSlots() {
        return this.parkingSpace.getAvailableSlots();
    }

    public ParkingApplication() {
        this.parkingSpace = new ParkingSpace();
        this.parkingService = new ParkingService();
    }

    public void parking(List<AbstractTrafficTool> trafficTools) {
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
    }

    public void leaving(List<ParkingSlot> slots) {
        for (ParkingSlot slot : slots) {
            AbstractTrafficTool leavingTool = slot.getTrafficTool();
            leavingTool.leaving();
            int fee = this.parkingService.calculateTotalFee(leavingTool);
            leavingTool.setTotalFee(fee);
            slot.leaved();

            System.out.println(String.format("A %s parked at parking space %s has left at %s" +
                            ". Total time parked is %s, for a total parking fee of %d" +
                            ". Available parking spaces for %s is %d",
                    slot.getToolType(),
                    slot.getId(),
                    leavingTool.getFormattedLeftTime(),
                    "xx:xx",
                    fee,
                    slot.getToolType(),
                    this.parkingSpace.availableSlotAmount(slot.getToolType())
            ));


        }
    }

}
