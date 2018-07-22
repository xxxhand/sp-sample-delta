package sp.sample.delta.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by hand on 2018/7/21.
 */
public abstract class AbstractTrafficTool {

    TrafficToolTypes toolType;
    private int totalFee;
    private long enterTime;

    public TrafficToolTypes getToolType() {
        return toolType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(long enterTime) {
        this.enterTime = enterTime;
    }

    public String getFormattedEnterTime() {
        LocalTime time = LocalTime.ofNanoOfDay(this.enterTime);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(f);
    }
}
