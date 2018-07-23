package sp.sample.delta;

import sp.sample.delta.application.ParkingApplication;
import sp.sample.delta.domain.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        System.out.println( "Hello World!" );

        ParkingApplication parkingApplication = new ParkingApplication();

        List<AbstractTrafficTool> trafficTools = new ArrayList<>();

        AbstractTrafficTool trafficTool = new Car();

        trafficTools.add(trafficTool);

        trafficTool = new Car();

        trafficTools.add(trafficTool);

        trafficTool = new Motorcycle();

        trafficTools.add(trafficTool);

        parkingApplication.parking(trafficTools);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return;
        }

        System.out.println("===================================================");



        List<ParkingSlot> leavingSlots = new ArrayList<>();
        parkingApplication.getParkingSpace().getSlots().stream()
                .filter(ParkingSlot::isBusy)
                .forEach(leavingSlots::add);

        parkingApplication.leaving(leavingSlots);





    }
}
