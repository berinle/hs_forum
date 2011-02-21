package com.jrock.forum;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author berinle
 */
@Embeddable
public class LegacyCarPlantPK implements Serializable {

    @Column(name="PLANT_ID")
    private String plantId;
    @Column(name="CAR_ID")
    private String carId;

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegacyCarPlantPK that = (LegacyCarPlantPK) o;

        if (carId != null ? !carId.equals(that.carId) : that.carId != null) return false;
        if (plantId != null ? !plantId.equals(that.plantId) : that.plantId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = plantId != null ? plantId.hashCode() : 0;
        result = 31 * result + (carId != null ? carId.hashCode() : 0);
        return result;
    }
}
