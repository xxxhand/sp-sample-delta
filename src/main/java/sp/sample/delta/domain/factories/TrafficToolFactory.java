package sp.sample.delta.domain.factories;

import sp.sample.delta.domain.AbstractTrafficTool;
import sp.sample.delta.domain.Car;
import sp.sample.delta.domain.Motorcycle;
import sp.sample.delta.domain.SmallBus;
import sp.sample.delta.domain.enums.TrafficToolTypes;

public class TrafficToolFactory {
    public AbstractTrafficTool create(TrafficToolTypes toolType) {
        switch (toolType) {
            case MOTOR:
                return new Motorcycle();
            case CAR:
                return new Car();
                default:
                    return new SmallBus();
        }
    }
}
