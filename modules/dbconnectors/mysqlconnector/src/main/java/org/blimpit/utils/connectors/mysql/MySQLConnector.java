package org.blimpit.utils.connectors.mysql;

import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;

import java.util.Map;

/**
 * A Class which handles MySQL operations
 */
public class MySQLConnector implements Connector {

    private static volatile MySQLConnector mySQLConnector;
    private static MySQLConnection mySQLConnection;

    private MySQLConnector(String ip, String port, String dbName,
                           String username, String password) throws ConnectorException {

        mySQLConnection = new MySQLConnection(ip, port, dbName, username, password) {
            @Override
            protected void close() throws ConnectorException {

            }
        };
        mySQLConnection.connect();

    }

    /**
     * Returns MySQLConnector connector object
     *
     * @param ip       ip of the DB Server
     * @param port     port of the DB Server
     * @param dbName   Database name that wants to connect
     * @param username username of the DB
     * @param password password of the DB
     * @return MySQLConnector
     */
    public static Connector getInstance(String ip, String port, String dbName,
                                        String username, String password) throws ConnectorException {
        if (mySQLConnector == null) {
            synchronized (MySQLConnector.class) {
                if (mySQLConnector == null) {
                    mySQLConnector = new MySQLConnector(ip, port, dbName, username, password);
                }
            }
        }
        return mySQLConnector;
    }


    public boolean insert(String collectionName, Map<String, String> recordMap) throws ConnectorException {

        return mySQLConnection.insert(collectionName, recordMap);
    }


    public boolean delete(String collectionName, String key, String val) throws ConnectorException {
        return mySQLConnection.delete(collectionName, key, val);
    }


    public boolean isOpen() throws ConnectorException {
        return mySQLConnection.isOpen();
    }


    public Record[] read(String selectValue,String startTime, String endTime, String collectionName,String returnValue) throws ConnectorException {
        return mySQLConnection.read(selectValue,startTime, endTime, collectionName,returnValue);
    }


    public boolean update(String collectionName, String selectionKey, String selectionVal, Map<String, String> recodes) throws ConnectorException {
        return mySQLConnection.update(collectionName, selectionKey, selectionVal, recodes);
    }


    public Record[] read(String table) throws ConnectorException {
        return mySQLConnection.read(table);
    }

    @Override
    public void close() throws ConnectorException {
        mySQLConnection.close();

    }


}