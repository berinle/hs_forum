package com.jrock.forum;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

/**
 * @author berinle
 */
public class LegacyCarPlantPKBridge implements TwoWayFieldBridge {
    private static final String PLANT_ID = ".plantId";
    private static final String CAR_ID = ".carId";

    public Object get(String name, Document document) {
        LegacyCarPlantPK id = new LegacyCarPlantPK();
        Field field = document.getField(name + PLANT_ID);
        id.setPlantId(field.stringValue());

        field = document.getField(name + CAR_ID);
        id.setCarId(field.stringValue());
        return id;
    }

    public String objectToString(Object o) {
        LegacyCarPlantPK id = (LegacyCarPlantPK) o;
        StringBuilder sb = new StringBuilder();
        sb.append(id.getPlantId())
        .append("-")
        .append(id.getCarId());
        return sb.toString();
    }

    public void set(String name, Object o, Document document, LuceneOptions luceneOptions) {
        LegacyCarPlantPK id = (LegacyCarPlantPK) o;
        Field.Store store = luceneOptions.getStore();
        Field.Index index = luceneOptions.getIndex();
        Field.TermVector termVector = luceneOptions.getTermVector();
        Float boost = luceneOptions.getBoost();

        Field field =
                new Field(name + PLANT_ID, id.getPlantId(), store, index, termVector);
        field.setBoost(boost);
        document.add(field);

        field =
                new Field(name + CAR_ID, id.getCarId(), store, index, termVector);
        field.setBoost(boost);
        document.add(field);
    }
}
