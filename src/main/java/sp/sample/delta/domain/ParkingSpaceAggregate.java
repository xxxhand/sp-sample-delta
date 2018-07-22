package sp.sample.delta.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hand on 2018/7/21.
 */
public class ParkingSpaceAggregate {
    private final int maxMotorcycleSlotsCount = 15;
    private final int maxCarSlotsCount = 20;
    private final int maxSmallBusSlotsCount = 5;
    private final List<String> usedIds = new ArrayList<>();

    private ParkingSlot[] motorcycleSlots;
    private ParkingSlot[] carSlots;
    private ParkingSlot[] smallBusSlots;


    public ParkingSpaceAggregate() {
        this._initialMotorcycleSlots();
        this._initialCarSlots();
        this._initialSmallBusSlots();
        this.usedIds.clear();
    }

    public void parking(List<AbstractTrafficTool> trafficTools) {
        for (AbstractTrafficTool trafficTool : trafficTools) {
            ParkingSlot slot = this._findOutEmptySlot(trafficTool.getToolType());
            if (slot == null) {
                System.out.println(String.format("A %s as entered at %s, but left because there are no " +
                                "vacant %s parking spaces",
                        trafficTool.getToolType().getDescription(),
                        trafficTool.getFormattedEnterTime(),
                        trafficTool.getToolType().getDescription()
                ));
                continue;
            }

            slot.parked(trafficTool);

            System.out.println(String.format("A %s has entered at %s and parked in parking space %s." +
                            " Remaining parking spaces for %s is %d",
                    trafficTool.getToolType().getDescription(),
                    trafficTool.getFormattedEnterTime(),
                    slot.getId(),
                    trafficTool.getToolType().getDescription(),
                    this._countEmptySlots(trafficTool.getToolType())
            ));
        }
    }

    public void leaving(List<AbstractTrafficTool> trafficTools) {

    }

    private void _initialMotorcycleSlots() {
        this.motorcycleSlots = new ParkingSlot[this.maxMotorcycleSlotsCount];
        for (int i = 0; i <= this.maxMotorcycleSlotsCount - 1; i++) {
            this.motorcycleSlots[i] = new ParkingSlot.Builder()
                    .setId(this._generateId())
                    .setBusy(false)
                    .build();
        }
    }

    private void _initialCarSlots() {
        this.carSlots = new ParkingSlot[this.maxCarSlotsCount];
        for (int i = 0; i <= this.maxCarSlotsCount - 1; i++) {
            this.carSlots[i] = new ParkingSlot.Builder()
                    .setId(this._generateId())
                    .setBusy(false)
                    .build();
        }
    }

    private void _initialSmallBusSlots() {
        this.smallBusSlots = new ParkingSlot[this.maxSmallBusSlotsCount];
        for (int i = 0; i <= this.maxSmallBusSlotsCount - 1; i++) {
            this.smallBusSlots[i] = new ParkingSlot.Builder()
                    .setId(this._generateId())
                    .setBusy(false)
                    .build();
        }
    }

    private String _generateId() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String returnId = buffer.toString();
        for (String usedId : this.usedIds) {
            if (returnId.equals(usedId)) {
                return _generateId();
            }
        }
        this.usedIds.add(returnId);
        return returnId;
    }

    private ParkingSlot _findOutEmptySlot(TrafficToolTypes toolType) {
        switch (toolType) {
            case MOTOR:
                for (ParkingSlot motorSlot: this.motorcycleSlots) {
                    if (!motorSlot.isBusy()) {
                        return motorSlot;
                    }
                }
                break;
            case CAR:
                for (ParkingSlot carSlot: this.carSlots) {
                    if (!carSlot.isBusy()) {
                        return carSlot;
                    }
                }
                break;
            case SMALL_BUS:
                for (ParkingSlot busSlot: this.smallBusSlots) {
                    if (!busSlot.isBusy()) {
                        return busSlot;
                    }
                }
                break;
        }

        return null;
    }

    private int _countEmptySlots(TrafficToolTypes toolType) {
        int least = 0;
        switch (toolType) {
            case MOTOR:
                for (ParkingSlot motorSlot : this.motorcycleSlots) {
                    if (!motorSlot.isBusy()) {
                        least++;
                    }
                }
                break;
            case CAR:
                for (ParkingSlot carSlot : this.carSlots) {
                    if (!carSlot.isBusy()) {
                        least++;
                    }
                }
                break;
            case SMALL_BUS:
                for (ParkingSlot busSlot : this.smallBusSlots) {
                    if (!busSlot.isBusy()) {
                        least++;
                    }
                }
                break;
        }

        return least;
    }
}
