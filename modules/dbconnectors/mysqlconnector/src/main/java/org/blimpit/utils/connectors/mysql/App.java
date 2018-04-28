package org.blimpit.utils.connectors.mysql;


import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args)  {

        ///////////////////////MySQLConnector con =new MySQLConnector();
        Map<String, String> rer = new HashMap<String, String>();

        rer.put("keyval","Hello");
        rer.put("val","Tharusha");

        Connector y = null;

        try {
            y = MySQLConnector.getInstance("localhost","3306","test","root","");

        } catch (ConnectorException e) {
            System.out.println(e.toString());
        }

        //System.out.println(y.insert("1test", rer));
        try {
            y.read("table_log");
        } catch (ConnectorException e) {
            e.toString();
        }

        //y.createTable();

//
//
//        rer.put("keyval","111");
//        rer.put("val","tt");
//
//        //boolean s = y.insert("test", rer);
//
//        //System.out.println("Insert Statues : " + s);
//        System.out.println("Delet Statues :"+y.delete("test","val","tt"));
//        y.read("test",null,null);








//        Map<String,String > map = new HashMap<>();
//
//        map.put("LogEntrery","Changed");
//
//        boolean s = false;
//        try {
//            s = y.update("table_log","id","1",map);
//        } catch (ConnectorException e) {
//            System.out.println("Error");
//        }
//        System.out.println("Update Statues :"+s);
//
//
    }

}
