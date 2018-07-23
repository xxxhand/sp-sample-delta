package sp.sample.delta;

import sp.sample.delta.application.ParkingApplication;
import sp.sample.delta.domain.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );

        runTesting();


    }

    private static void runTesting() {
        ParkingApplication parkingApplication = new ParkingApplication();

        List<AbstractTrafficTool> trafficTools = new ArrayList<>();

        AbstractTrafficTool trafficTool = new Car();

        trafficTools.add(trafficTool);

        trafficTool = new Car();

        trafficTools.add(trafficTool);

        trafficTool = new Motorcycle();

        trafficTools.add(trafficTool);

        parkingApplication.parking(trafficTools);

        parkingApplication.calculateAvailableSlotAmount();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return;
        }

        List<ParkingSlot> leavingSlots = new ArrayList<>();
        parkingApplication.getAllSlots().stream()
                .filter(ParkingSlot::isBusy)
                .forEach(leavingSlots::add);

        parkingApplication.leaving(leavingSlots);

        parkingApplication.calculateAvailableSlotAmount();
    }
}
