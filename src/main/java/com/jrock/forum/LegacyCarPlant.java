package com.jrock.forum;

import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;

/**
 * @author Bayo Erinle
 */
@Entity
@Table(name="Z_LEG_CAR_PLANT")
@Indexed
public class LegacyCarPlant {
    @EmbeddedId @DocumentId
    @FieldBridge(impl = LegacyCarPlantPKBridge.class)
   private LegacyCarPlantPK id;

    @Column(name="PLANT_NAME")
    private String name;

    @IndexedEmbedded
    @ManyToOne//(cascade = CascadeType.ALL) //cascade "all" as tempoary fix for jira issue HSEARCH-689
    @Cascade(org.hibernate.annotations.CascadeType.LOCK) //cascade "all" as tempoary fix for jira issue HSEARCH-689
    @JoinColumn(name="CAR_ID", insertable = false, updatable = false)
    private LegacyCar car;

    public LegacyCarPlantPK getId() {
        return id;
    }

    public void setId(LegacyCarPlantPK id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LegacyCar getCar() {
        return car;
    }

    public void setCar(LegacyCar car) {
        this.car = car;
    }
}
