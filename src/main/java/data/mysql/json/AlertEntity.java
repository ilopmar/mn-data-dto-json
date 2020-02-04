package data.mysql.json;

import io.micronaut.data.annotation.TypeDef;
import io.micronaut.data.model.DataType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Entity
@Table(name = "ALERT")
public class AlertEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    private String sensorId;

    @NotNull
    @TypeDef(type = DataType.JSON)
    private Map<String, Double> gps;

    public AlertEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
