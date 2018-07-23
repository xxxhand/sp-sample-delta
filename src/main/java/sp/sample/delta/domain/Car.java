package sp.sample.delta.domain;

import sp.sample.delta.domain.enums.TrafficToolTypes;

/**
 * Created by hand on 2018/7/21.
 */
public class Car extends AbstractTrafficTool {
    public Car() {
        this.toolType = TrafficToolTypes.CAR;
    }
}
