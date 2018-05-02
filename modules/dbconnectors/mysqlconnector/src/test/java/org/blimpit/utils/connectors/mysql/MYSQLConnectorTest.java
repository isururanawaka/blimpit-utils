package org.blimpit.utils.connectors.mysql;

import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MYSQLConnectorTest {


    Map<String, String> recodes = new HashMap<String, String>();

    public Connector MYSQLConnectorTest() throws ConnectorException {
        Connector testconnector = MySQLConnector.getInstance("localhost", "3306", "test", "root", "");
        return testconnector;
    }


    @Test
    public void testInsert() throws ConnectorException {

        Map<String, String> records = new HashMap<String, String>();
        int lengthofrecodes = 7;
        String temp = " ";

        String keyName = "LogEntrery";
        String keyValue = "Hel";
        records.put(keyName , keyValue);
        records.put("Date","14.07");


        MYSQLConnectorTest().insert("table_log",records);
        Record[] table_logs = MYSQLConnectorTest().read("id","1", "100", "table_log", "LogEntrery");

        lengthofrecodes = table_logs.length;

        for (String key : table_logs[lengthofrecodes - 1].getRecordAttributes().keySet()) {

            temp = table_logs[lengthofrecodes - 1].getRecordAttributes().get(key);

        }
        assertEquals(keyValue, temp);

    }

    ///
    @Test
    public void testDeleteOne() throws ConnectorException {
        int delet_id = 7;
        String table = "table_log";

        MYSQLConnectorTest().delete(table, "id", Integer.toString(delet_id));
        Record[] read = MYSQLConnectorTest().read(table);

        Assert.assertNotSame("100",read.length);

    }

    @Test
    public void testisOpen() throws ConnectorException {
        assertNotNull(MYSQLConnectorTest().isOpen());
    }

    @Test
    public void testRead() throws ConnectorException {

        String temp =" ";
        Connector y = null;

        Record[] table_logs = MYSQLConnectorTest().read("id","1", "100", "table_log", "LogEntrery");

        for (String key : table_logs[0].getRecordAttributes().keySet()) {

            temp = table_logs[0].getRecordAttributes().get(key);

        }
        assertEquals("Changed", temp);

    }

    @Test
    public void testUpdate() throws ConnectorException {
        Map<String, String> records = new HashMap<String, String>();
        int editRow = 7;
        String temp = " ";

        String keyName = "LogEntrery";
        String keyValue = "Hel";

        records.put(keyName , keyValue);

        MYSQLConnectorTest().update("table_log", "id", Integer.toString(editRow), records);
        Record[] table_logs = MYSQLConnectorTest().read("id","1", Integer.toString(editRow), "table_log", "LogEntrery");

        for (String key : table_logs[editRow - 1].getRecordAttributes().keySet()) {

            temp = table_logs[editRow - 1].getRecordAttributes().get(key);
            System.out.println(key);
            System.out.println(temp);


        }
        assertEquals(keyValue, temp);


        //assertEquals(true, update);
    }

    @Test
    public void testReadBetween() throws ConnectorException {

        Connector y = null;
        Record[] araList;

        try {
            y = MySQLConnector.getInstance("localhost", "3306", "test", "root", "");

            for (Record record : araList = y.read("Date"," 12.25", " 12.50", "table_log", "*")) {

                assertNotNull("Log Entry Records", record.getRecordAttributes());

            }

        } catch (ConnectorException e) {
            throw e;
        } finally {
            y.close();
        }
    }

}
