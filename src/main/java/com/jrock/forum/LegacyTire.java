package com.jrock.forum;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;

import javax.persistence.*;

/**
 * @author Bayo Erinle
 */
@Entity
@Table(name="Z_LEG_TIRE")
public class LegacyTire {
    @Id
    @DocumentId
    @FieldBridge(impl = LegacyTirePKBridge.class)
    private LegacyTirePK id;
    @Field @Column(name="TIRE_SIZE")
    private int tireSize;

    @ManyToOne
    @JoinColumn(name="CAR_ID", insertable = false, updatable = false)
    private LegacyCar car;

    public int getTireSize() {
        return tireSize;
    }

    public void setTireSize(int tireSize) {
        this.tireSize = tireSize;
    }

    public LegacyCar getCar() {
        return car;
    }

    public void setCar(LegacyCar car) {
        this.car = car;
    }

    public LegacyTirePK getId() {
        return id;
    }

    public void setId(LegacyTirePK id) {
        this.id = id;
    }
}
