package org.blimpit.utils.loghandler;

import org.junit.Test;

public class LogHandlerImpltTest {

    LogHandlerImplt log = new LogHandlerImplt();

    @Test
    public void testLogMessage() {

        log.logMessage("Sirimal","Has Logged");
    }
    @Test
    public void testGetLogs(){

        log.getLogs(14.00,14.16,"Sirimal");

    }

}