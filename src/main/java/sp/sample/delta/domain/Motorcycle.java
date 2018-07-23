package sp.sample.delta.domain;

import sp.sample.delta.domain.enums.TrafficToolTypes;

/**
 * Created by hand on 2018/7/21.
 */
public class Motorcycle extends AbstractTrafficTool {

    public Motorcycle() {
        this.toolType = TrafficToolTypes.MOTOR;
    }
}
