package sp.sample.delta;

import sp.sample.delta.application.ParkingApplication;
import sp.sample.delta.domain.*;

import java.util.Timer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );

//        runTesting();

        Timer timer = new Timer();
        timer.schedule(new SimulatorTask(), 1000, 1000);


    }

    private static void runTesting() {
        ParkingApplication parkingApplication = new ParkingApplication();


        AbstractTrafficTool trafficTool = new Car();
        parkingApplication.parking(trafficTool);

        trafficTool = new SmallBus();
        parkingApplication.parking(trafficTool);

        trafficTool = new SmallBus();
        parkingApplication.parking(trafficTool);

        parkingApplication.calculateAvailableSlotAmount();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return;
        }

        parkingApplication.getAllSlots().stream()
                .filter(ParkingSlot::isBusy)
                .forEach(parkingApplication::leaving);


        parkingApplication.calculateAvailableSlotAmount();
    }
}
