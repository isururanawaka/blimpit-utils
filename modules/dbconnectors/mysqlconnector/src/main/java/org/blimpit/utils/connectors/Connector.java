package org.blimpit.utils.connectors;



import java.util.Map;
import org.blimpit.utils.connectors.mysql.*;

/**
 * An interface which describes APIs for MySQL DB operations
 */
public interface Connector {


    /**
     * insert values to table
     *
     * @param collectionName Name of the table or collection
     * @param recordMap      filedName and value pairs
     * @return status of the operation
     */
    boolean insert(String collectionName, Map<String, String> recordMap) throws ConnectorException;


    /**
     *
     * @param table
     * @param key matching field name
     * @param value matching value for the field
     * @return matching record
     * @throws ConnectorException
     */
     Record[] read(String table, String key, String value) throws ConnectorException;

    /**
     * delete values in a table
     *
     * @param collectionName  Name of the table or collection
     * @param key             field name of the recode
     * @param val             field value of the recode
     * @return
     */
    boolean delete(String collectionName, String key, String val) throws ConnectorException;

    /**
     *
     * @return returns
     */
    boolean isOpen() throws ConnectorException;


    /**
     *
     * @param startTime
     * @param endTime
     * @param collectionName
     * @return
     * @throws ConnectorException
     */
    Record[] read(String selectValue,String startTime, String endTime, String collectionName,String returnValue) throws ConnectorException;

    /**
     *
     * @param collectionName  Name of the table to be updated
     * @param selectionKey    Name of the field
     * @param selectionVal    Value of the field
     * @param recodes         New record as pairs of fieldNAme and value
     * @return
     * @throws ConnectorException
     */
    boolean update(String collectionName,String selectionKey,String selectionVal,Map<String, String> recodes) throws ConnectorException;

    /**
     * Returns records of the table
     * @param table Table Name
     * @return Record
     */
    Record[] read(String table) throws ConnectorException;

    /**
     *
     * @return
     */
    void close() throws ConnectorException;

}
