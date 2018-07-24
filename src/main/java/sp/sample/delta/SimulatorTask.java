package sp.sample.delta;

import sp.sample.delta.application.ParkingApplication;
import sp.sample.delta.domain.*;
import sp.sample.delta.domain.enums.TrafficToolTypes;
import sp.sample.delta.domain.factories.TrafficToolFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * Created by hand on 2018/7/23.
 */
public class SimulatorTask extends TimerTask {
    private final ParkingApplication PARKING_APP = new ParkingApplication();
    private final TrafficToolFactory factory = new TrafficToolFactory();
    private long initialCounter = 1;
    @Override
    public void run() {
//        System.out.println("Timer run " + initialCounter);
        if (this.initialCounter % 20 == 0) {
            System.out.println("------------------ Start leaving ------------------");
            this._getRandomSlotInRange(0, 2).forEach(this.PARKING_APP::leaving);
            System.out.println("------------------ End leaving --------------------");
        }
        if (this.initialCounter % 10 == 0) {
            System.out.println("------------------ Start parking ------------------");
            this._startParking();
            System.out.println("------------------ End parking --------------------");
        }
        if (this.initialCounter % 15 == 0) {
            System.out.println("------------------ Start counting ------------------");
            this.PARKING_APP.calculateAvailableSlotAmount();
            System.out.println("------------------ End counting --------------------");
        }
        this.initialCounter++;
        if (this.initialCounter > 60) {
            this.initialCounter = 1;
        }
    }

    private void _startParking() {
        int r = this._getRandomNumberInRange(0, 2);
        if (r == 0) {
            return;
        }
        for (int i = 0; i <= r - 1; i++) {
            this.PARKING_APP.parking(factory.create(TrafficToolTypes.randomType()));
        }
    }

    private int _getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }

    private List<ParkingSlot> _getRandomSlotInRange(int min, int max) {
        List<ParkingSlot> leavingSlots = new ArrayList<>();
        int r = this._getRandomNumberInRange(min, max);
        if (r == 0) {
            return leavingSlots;
        }
        List<ParkingSlot> busySlots = this.PARKING_APP.getAllSlots().stream()
                .filter(ParkingSlot::isBusy)
                .collect(Collectors.toList());
        if (busySlots == null || busySlots.isEmpty()) {
            return leavingSlots;
        }

        ParkingSlot slot;
        Random random = new Random();
        for (int i = 0; i <= r - 1; i++) {
            int randomIndex = random.nextInt(busySlots.size());
            slot = busySlots.get(randomIndex);
            leavingSlots.add(slot);
            busySlots.remove(randomIndex);
        }

        return leavingSlots;
    }
}
