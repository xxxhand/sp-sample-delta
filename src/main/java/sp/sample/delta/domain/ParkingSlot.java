package sp.sample.delta.domain;


import java.util.Date;

/**
 * Created by hand on 2018/7/21.
 */
public class ParkingSlot {
    private String id;
    private AbstractTrafficTool trafficTool;
    private boolean isBusy;

    public String getId() {
        return id;
    }

    public AbstractTrafficTool getTrafficTool() {
        return trafficTool;
    }


    public boolean isBusy() {
        return isBusy;
    }


    private ParkingSlot(Builder builder) {
        this.id = builder.id;
        this.trafficTool = builder.trafficTool;
        this.isBusy = builder.isBusy;
    }

    public void parked(AbstractTrafficTool trafficTool) {
        this.trafficTool = trafficTool;
        this.isBusy = true;

    }

    public void leaved() {
        this.trafficTool = null;
        this.isBusy = false;
    }


    public static class Builder {
        private String id;
        private AbstractTrafficTool trafficTool;
        private boolean isBusy;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setTrafficTool(AbstractTrafficTool trafficTool) {
            this.trafficTool = trafficTool;
            return this;
        }

        public Builder setBusy(boolean busy) {
            isBusy = busy;
            return this;
        }

        public ParkingSlot build() {
            return new ParkingSlot(this);
        }
    }
}
