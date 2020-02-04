package data.mysql.json;

import io.micronaut.core.annotation.Introspected;

import java.util.Map;

@Introspected
public class Alert {

    private String sensorId;
    private Map<String, Double> gps;

    public Alert() {
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Map<String, Double> getGps() {
        return gps;
    }

    public void setGps(Map<String, Double> gps) {
        this.gps = gps;
    }

}
