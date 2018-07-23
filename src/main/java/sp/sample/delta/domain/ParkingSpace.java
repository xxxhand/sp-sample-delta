package sp.sample.delta.domain;

import sp.sample.delta.domain.enums.TrafficToolTypes;

import java.util.*;

public class ParkingSpace {
    private final List<String> USED_IDS = new ArrayList<>();
    private List<ParkingSlot> slots;
    private Map<TrafficToolTypes, Integer> availableSlots;

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public Map<TrafficToolTypes, Integer> getAvailableSlots() {
        return availableSlots;
    }

    public ParkingSpace() {
        this._initial();
        this.USED_IDS.clear();
    }

    public long availableSlotAmount(TrafficToolTypes toolType) {
        return this.slots.stream()
                .filter(x -> x.getToolType().equals(toolType))
                .filter(x -> !x.isBusy())
                .count();
    }

    public ParkingSlot findOneSlot(AbstractTrafficTool trafficTool) throws Exception {
        return this.slots.stream()
                .filter(x -> x.getToolType().equals(trafficTool.getToolType()))
                .filter(x -> !x.isBusy())
                .findAny()
                .orElseThrow(() -> new Exception(String.format("A %s as entered at %s, but left because there are no " +
                                "vacant %s parking spaces",
                        trafficTool.getToolType().getDescription(),
                        trafficTool.getFormattedEnterTime(),
                        trafficTool.getToolType().getDescription())));
    }

    private void _initial() {
        this.availableSlots = new HashMap<>();
        availableSlots.put(TrafficToolTypes.MOTOR, 15);
        availableSlots.put(TrafficToolTypes.CAR, 20);
        availableSlots.put(TrafficToolTypes.SMALL_BUS, 12);

        this.slots = new ArrayList<>();
        int j = 1;
        for (Map.Entry<TrafficToolTypes, Integer> slotMap: availableSlots.entrySet()) {
            for (int i = 0; i <= slotMap.getValue() - 1; i++) {
                this.slots.add(new ParkingSlot.Builder()
                        .setId(String.valueOf(j))
                        .setToolType(slotMap.getKey())
                        .setBusy(false)
                        .build()
                );

                j++;
            }
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
        for (String usedId : this.USED_IDS) {
            if (returnId.equals(usedId)) {
                return _generateId();
            }
        }
        this.USED_IDS.add(returnId);
        return returnId;
    }


}
