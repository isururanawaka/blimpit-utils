package org.blimpit.utils.connectors.mysql;

import java.util.HashMap;
import java.util.Map;

/**
 * A  class represents a record of a table in DB
 */
public class Record {


    private int index;

    private Map<String, String> recordAttributes;


    public Record(int index) {
        this.index = index;
        this.recordAttributes = new HashMap<String, String>();
    }

    /**
     * poistion of the table
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }


    /**
     * Returns map of fieldnames and values
     *
     * @return recordAttributes
     */
    public Map<String, String> getRecordAttributes() {



        return recordAttributes;
    }

    /**
     * Add fieldname and value to the record
     *
     * @param fieldName
     * @param value
     */
    public void addRecordAttribute(String fieldName, String value) {
        this.recordAttributes.put(fieldName, value);
    }
}
