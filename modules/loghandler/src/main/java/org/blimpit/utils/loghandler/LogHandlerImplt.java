/*

* */

package org.blimpit.utils.loghandler;


import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LogHandlerImplt implements LogHandler {

    private static Logger logger = LoggerFactory.getLogger("BlimpIt");
    private FileAppender fa = new FileAppender();


    private void setConfig(String fname) {
        //PropertyConfigurator.configure("log4j.properties");
        fa.setName("Blimpit");
        fa.setFile(fname);
        fa.setLayout(new PatternLayout("%d{yyyy-MM-dd HH.mm} %-5p [%c{1}] %m%n"));
        fa.setThreshold(Level.INFO);
        fa.setAppend(true);
        fa.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(fa);
    }

    public void logMessage(String userName, String activity) {
        setConfig("bmp/" + userName + ".log");
        logger.info(activity);
    }

    public void storeLogInDB(String username, String activity) {



    }

    public String getLogs(double beginTimeStamp, double endTimeStamp, String userName) throws Exception {

        String concatString = "";
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("bmp/" + userName + ".log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            /* read log line by line */
            while ((strLine = br.readLine()) != null) {
                /* parse strLine to obtain what you want */

                Double temp = Double.parseDouble(strLine.substring(11, 16));

                if (beginTimeStamp <= temp) {
                    if (endTimeStamp >= temp) {
                        //concatString = concatString + strLine.substring(17) + "\n";
                        concatString = concatString + strLine + "\n";
                    }
                }

            }
            //fstream.close();
        } catch (Exception e) {
            throw e;
        } finally {

            fstream.close();

        }


        return concatString;
    }

    public String getLogsFromDB(double beginTimeStamp, double endTimeStamp, String userName) {return "Not Implemented";    }
}
