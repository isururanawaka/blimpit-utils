package org.blimpit.utils.connectors.mysql;

import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;
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

        recodes.put("LogEntrery", "Test Log");
        recodes.put("Date", "HI");

        boolean stat = MYSQLConnectorTest().insert("table_log", recodes);
        assertEquals(true,stat);

    }

    @Test
    public void testDeleteOne() throws ConnectorException {
        boolean stat = MYSQLConnectorTest().delete("test", "val", "Hi");
        assertEquals(true,stat);

    }

    @Test
    public void testisOpen() throws ConnectorException {
        assertNotNull(MYSQLConnectorTest().isOpen());
    }

    @Test
    public void testRead() {

        Connector y = null;
        Record[] araList;

        try {
            y = MySQLConnector.getInstance("localhost", "3306", "test", "root", "");

            for (Record record : araList = y.read("test")) {

                assertNotNull("Recodes read", record.getRecordAttributes());

            }

        } catch (ConnectorException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    public void testUpdate() throws ConnectorException {
        Map<String, String> records = new HashMap<String, String>();
        records.put("LogEntrery","hello");

        boolean update = MYSQLConnectorTest().update("table_log", "id", "5", recodes);

        assertEquals(true,update);
    }

    @Test
    public void testReadBetween() throws ConnectorException {

        Connector y = null;
        Record[] araList;

        try {
            y = MySQLConnector.getInstance("localhost", "3306", "test", "root", "");

            for (Record record : araList = y.read(" 12.25", " 12.50", "table_log")) {

                assertNotNull("Log Entry Records", record.getRecordAttributes());

            }

        } catch (ConnectorException e) {
            System.out.println(e.toString());
        }
    }

}
