package sp.sample.delta.domain;

import sp.sample.delta.domain.enums.TrafficToolTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public String getFormattedEnterTime() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        return this.enterTime.format(f);
    }

    public String getFormattedLeftTime() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        return this.leftTime.format(f);
    }

    public void parking() {
        this.enterTime = LocalDateTime.now();
        this.totalFee = 0;
    }

    public void leaving() {
        this.leftTime = LocalDateTime.now();
    }


}
