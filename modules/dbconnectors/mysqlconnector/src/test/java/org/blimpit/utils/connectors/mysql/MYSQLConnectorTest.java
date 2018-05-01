package org.blimpit.utils.connectors.mysql;

import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MYSQLConnectorTest {


    Map<String, String> recodes = new HashMap<String, String>();

    public Connector MYSQLConnectorTest() throws ConnectorException {
        Connector testconnector = MySQLConnector.getInstance("localhost", "3306", "test", "root", "");
        return testconnector;
    }


    @Before
    public void befor() throws ConnectorException {
        System.out.println("Connection is: " + MYSQLConnectorTest().isOpen());
    }

    @Test
    public void testConnectMYSQL() throws ConnectorException {
        MySQLConnector.getInstance("localhost", "3306", "test", "root", "");
    }

    @Test
    public void testInsert() throws ConnectorException {

        recodes.put("keyval", "Hello");
        recodes.put("val", "World");


        System.out.println("Insert Query Statues: " + MYSQLConnectorTest().insert("test", recodes));
    }

    @Test
    public void testDeleteOne() throws ConnectorException {
        System.out.println("Delete Querry Statues: " + MYSQLConnectorTest().delete("test", "val", "Hi"));
    }


    @Test
    public void testRead() {

        Connector y = null;
        Record[] araList;

        try {
            y = MySQLConnector.getInstance("localhost", "3306", "test", "root", "");

            for (Record record : araList = y.read("test")) {

                System.out.println(record.getRecordAttributes());

            }

        } catch (ConnectorException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    public void testReadBetween() throws ConnectorException {

        Connector y = null;
        Record[] araList;

        try {
            y = MySQLConnector.getInstance("localhost", "3306", "test", "root", "");

            for (Record record : araList = y.read(" 12.25", " 12.50", "table_log")) {

                System.out.println(record.getRecordAttributes());

            }

        } catch (ConnectorException e) {
            System.out.println(e.toString());
        }
    }

}
