package org.blimpit.utils.connectors;

import org.blimpit.utils.connectors.mysql.Record;

import java.util.Map;

/**
 * An interface which represents the APIs of connection
 */
public abstract class Connection {

    /**
     * returns true if connection is established
     *
     * @return
     */
    protected abstract boolean isOpen();

    /**
     * @param table
     * @param recordMap
     * @return
     * @throws ConnectorException
     */
    protected abstract boolean insert(String table, Map<String, String> recordMap) throws ConnectorException;

    /**
     * @param table
     * @param key
     * @param val
     * @return
     * @throws ConnectorException
     */
    protected abstract boolean delete(String table, String key, String val) throws ConnectorException;

      /**
     * @param startTime
     * @param endTime
     * @param table
     * @return
     * @throws ConnectorException
     */
    protected abstract Record[] read(String startTime, String endTime, String table) throws ConnectorException;

    /**
     * @param table
     * @param selectionKey
     * @param selectionVal
     * @param records
     * @return
     * @throws ConnectorException
     */
    protected abstract boolean update(String table, String selectionKey, String selectionVal, Map<String, String> records) throws ConnectorException;

    protected abstract Record[] read(String table) throws ConnectorException;
}
