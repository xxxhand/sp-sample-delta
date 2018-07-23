package sp.sample.delta;

import sp.sample.delta.application.ParkingApplication;
import sp.sample.delta.domain.AbstractTrafficTool;
import sp.sample.delta.domain.Car;
import sp.sample.delta.domain.Motorcycle;
import sp.sample.delta.domain.SmallBus;
import sp.sample.delta.domain.enums.TrafficToolTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

/**
 * Created by hand on 2018/7/23.
 */
public class SimulatorTask extends TimerTask {
    private final ParkingApplication PARKING_APP = new ParkingApplication();
    private long initialCounter = 1;
    @Override
    public void run() {
//        System.out.println("Timer run " + initialCounter);
        if (this.initialCounter % 20 == 0) {
            this._startLeaving();
        }
        if (this.initialCounter % 10 == 0) {
            this._startParking();
        }
        if (this.initialCounter % 15 == 0) {
            this.PARKING_APP.calculateAvailableSlotAmount();
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
        List<AbstractTrafficTool> waitTools = new ArrayList<>();
        AbstractTrafficTool trafficTool = null;
        for (int i = 0; i <= r; i++) {
            TrafficToolTypes toolType = TrafficToolTypes.randomType();
            switch (toolType) {
                case MOTOR:
                    trafficTool = new Motorcycle();
                    break;
                case CAR:
                    trafficTool = new Car();
                    break;
                case SMALL_BUS:
                    trafficTool = new SmallBus();
                    break;
            }
            waitTools.add(trafficTool);
        }
        this.PARKING_APP.parking(waitTools);
    }
    private void _startLeaving() {}
    private int _getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }
}
