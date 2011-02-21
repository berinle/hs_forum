package com.jrock.forum;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bayo Erinle
 */
@Entity
@Table(name="Z_LEG_CAR")
public class LegacyCar {
    @Id @DocumentId @Column(name="CAR_ID")
    private String id;
    @Field
    @Column(name="MODEL")
    private String model;

    @IndexedEmbedded
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<LegacyTire> tires = new HashSet<LegacyTire>();

    public void addToTires(LegacyTire tire){
        tires.add(tire);
        tire.setCar(this);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Set<LegacyTire> getTires() {
        return tires;
    }

    public void setTires(Set<LegacyTire> tires) {
        this.tires = tires;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
