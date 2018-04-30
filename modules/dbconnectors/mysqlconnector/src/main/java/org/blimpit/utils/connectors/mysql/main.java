package org.blimpit.utils.connectors.mysql;

import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;


public class main {

    public static void main(String[] args) {
        Connector y = null;

        try {
            y = MySQLConnector.getInstance("localhost","3306","test","root","");
            y.read("test");

        } catch (ConnectorException e) {
            System.out.println(e.toString());
        }
    }

}
