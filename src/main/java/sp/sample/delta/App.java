package sp.sample.delta;

import sp.sample.delta.domain.AbstractTrafficTool;
import sp.sample.delta.domain.Car;
import sp.sample.delta.domain.Motorcycle;
import sp.sample.delta.domain.ParkingSpaceAggregate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final ParkingSpaceAggregate parkingSpace = new ParkingSpaceAggregate();
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        List<AbstractTrafficTool> trafficTools = new ArrayList<>();

        AbstractTrafficTool trafficTool = new Car();
        trafficTool.setEnterTime(LocalTime.now().toNanoOfDay());
        trafficTool.setTotalFee(0);

        trafficTools.add(trafficTool);

        trafficTool = new Car();
        trafficTool.setEnterTime(LocalTime.now().toNanoOfDay());
        trafficTool.setTotalFee(0);

        trafficTools.add(trafficTool);

        trafficTool = new Motorcycle();
        trafficTool.setEnterTime(LocalTime.now().toNanoOfDay());
        trafficTool.setTotalFee(0);

        trafficTools.add(trafficTool);

        parkingSpace.parking(trafficTools);



    }
}
