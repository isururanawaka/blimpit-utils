package org.blimpit.utils.connectors.mysql;

//import org.blimpit.utils.connectors.Connection;

import org.blimpit.utils.connectors.Connection;
import org.blimpit.utils.connectors.ConnectorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Represents actual connection to the DB
 */
public class MySQLConnection extends Connection {

    private String ip;
    private String port;
    private String dbName;
    private String username;
    private String password;
    private java.sql.Connection connection;

    MySQLConnection(String ip, String port, String dbName,
                    String username, String password) {

        this.ip = ip;
        this.port = port;
        this.dbName = dbName;
        this.username = username;
        this.password = password;

    }

    MySQLConnection() {
        ip = "localhost";
        port = "3306";
        dbName = "test";
        username = "root";
        password = "";
    }

    private String maptoString(Map<String, String> recordMap) {

        int i = 1;

        StringBuilder bul = new StringBuilder();

        for (String str : recordMap.keySet()) {

            if (recordMap.size() != i) {
                bul.append(str).append(" = ?, ");
            } else {
                bul.append(str).append(" = ? ");
            }
            i++;
        }

        return bul.toString();
    }

    // Connect the MYSQL Server
    public void connect() throws ConnectorException {

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName + "?useSSL=false", "" + username, "" + password);
        } catch (SQLException e) {
            ConnectorException ex = new ConnectorException(e, "Error in the Connection");
            throw ex;
        }

    }


    public boolean isOpen() throws ConnectorException {
        try {
            return connection.isValid(10);
        } catch (SQLException e) {
            ConnectorException ex = new ConnectorException(e, "This might be due to no Valid connection");
            throw ex;
        }
    }

    @Override
    public boolean insert(String table, Map<String, String> recordMap) throws ConnectorException {

        PreparedStatement statement;
        int i = 1;

        StringBuilder bul = new StringBuilder();
        StringBuilder valind = new StringBuilder();

        for (String str : recordMap.keySet()) {

            if (recordMap.size() != i) {
                bul.append(str).append(",");
                valind.append("?").append(",");
            } else {
                bul.append(str);
                valind.append("?");
            }
            i++;
        }

        i = 1;
        try {
            statement = connection.prepareStatement("INSERT INTO " + table + "(" + bul.toString() + ") VALUES " + " (" + valind.toString() + ")" + " "); /// specify the number of entries
            for (String key : recordMap.keySet()) {
                statement.setString(i, recordMap.get(key));
                i++;
            }
            statement.execute();
            return true;

        } catch (SQLException e) {

            ConnectorException ex = new ConnectorException(e, "Error in connecting to the server\n Selected Table may not Exist\n Values may not tally with the table values");
            throw ex;

        }
    }

    @Override
    public boolean delete(String table, String key, String val) throws ConnectorException {

        PreparedStatement statement;

        try {
            statement = connection.prepareStatement("DELETE FROM " + table + " WHERE " + key + "= ?");
            statement.setString(1, val);
            statement.execute();
            return true;
        } catch (SQLException e) {
            ConnectorException ex = new ConnectorException(e, "Delete");
            throw ex;
        }
    }

    @Override
    protected boolean update(String table, String selectionKey, String selectionVal, Map<String, String> records) throws ConnectorException {
        PreparedStatement statement;
        String keyChange = maptoString(records);
        int i = 1;

        try {

            statement = connection.prepareStatement("UPDATE " + table + " SET " + keyChange + " WHERE " + selectionKey + " in (" + selectionVal + ")");

            for (String key : records.keySet()) {
                statement.setString(i, records.get(key));
                i++;
            }
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {

            ConnectorException ex = new ConnectorException(e, "Update");
            throw ex;

        }

    }

    @Override
    protected Record[] read(String table) throws ConnectorException {

        PreparedStatement statement;
        Record record;
        List<Record> arrayList = new ArrayList<Record>();

        try {

            statement = connection.prepareStatement("SELECT * FROM " + table);
            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                record = new Record(rs.getRow());

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    record.addRecordAttribute(rsmd.getColumnName(i), rs.getString(i));
                }

                arrayList.add(record);
            }

        } catch (SQLException e) {
            ConnectorException ex = new ConnectorException(e, "At read");
            throw ex;
        }

        return arrayList.toArray(new Record[arrayList.size()]);
    }

    @Override
    protected void close() throws ConnectorException {

        try {
            connection.close();
        } catch (SQLException e) {
            ConnectorException ex = new ConnectorException(e, "This Might Due to no connection open");
            throw ex;
        }

    }

    @Override
    public Record[] read(String selectValue, String startTime, String endTime, String table, String returnValue) throws ConnectorException {

        PreparedStatement statement;
        List<Record> arrayList = new ArrayList<Record>();
        Record record;

        try {
            if (returnValue.equals("*")) {
                statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + selectValue + " BETWEEN " + startTime + " AND " + endTime);
            } else {
                statement = connection.prepareStatement("SELECT " + returnValue + " FROM " + table + " WHERE " + selectValue + " BETWEEN " + startTime + " AND " + endTime);
            }

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {

                record = new Record(rs.getRow());
                record.addRecordAttribute(rsmd.getColumnName(1), rs.getString(1));
                arrayList.add(record);

            }
        } catch (SQLException e) {
            ConnectorException ex = new ConnectorException(e, "Read Specific");
            throw ex;
        }

        return arrayList.toArray(new Record[arrayList.size()]);
    }

}
