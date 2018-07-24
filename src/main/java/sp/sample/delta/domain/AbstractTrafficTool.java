package sp.sample.delta.domain;

import sp.sample.delta.domain.enums.TrafficToolTypes;

import java.time.LocalDateTime;

/**
 * Created by hand on 2018/7/21.
 */
public abstract class AbstractTrafficTool {

    TrafficToolTypes toolType;
    private int totalFee;
    private LocalDateTime enterTime;
    private LocalDateTime leftTime;

    public TrafficToolTypes getToolType() {
        return toolType;
    }


    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public LocalDateTime getEnterTime() {
        return enterTime;
    }

    public LocalDateTime getLeftTime() {
        return leftTime;
    }


    public void parking() {
        this.enterTime = LocalDateTime.now();
        this.totalFee = 0;
    }

    public void leaving() {
        this.leftTime = LocalDateTime.now();
    }


}
