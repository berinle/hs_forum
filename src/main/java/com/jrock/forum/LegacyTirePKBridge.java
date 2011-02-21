package com.jrock.forum;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

/**
 * @author Bayo Erinle
 */
public class LegacyTirePKBridge implements TwoWayFieldBridge {
    private static final String CAR_ID = ".carId";
    private static final String TIRE_ID = ".tireId";

    public Object get(String name, Document document) {
        LegacyTirePK id = new LegacyTirePK();
        Field field = document.getField(name + CAR_ID);
        id.setCarId(field.stringValue());

        field = document.getField(name + TIRE_ID);
        id.setTireId(field.stringValue());
        return id;
    }

    public String objectToString(Object o) {
        LegacyTirePK id = (LegacyTirePK) o;
        StringBuilder sb = new StringBuilder();
        sb.append(id.getCarId())
        .append("-")
        .append(id.getTireId());
        return sb.toString();
    }

    public void set(String name, Object o, Document document, LuceneOptions luceneOptions) {
        LegacyTirePK id = (LegacyTirePK) o;
        Field.Store store = luceneOptions.getStore();
        Field.Index index = luceneOptions.getIndex();
        Field.TermVector termVector = luceneOptions.getTermVector();
        Float boost = luceneOptions.getBoost();

        Field field =
                new Field(name + CAR_ID, id.getCarId(), store, index, termVector);
        field.setBoost(boost);
        document.add(field);

        field =
                new Field(name + TIRE_ID, id.getTireId(), store, index, termVector);
        field.setBoost(boost);
        document.add(field);
    }
}

