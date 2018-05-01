package org.blimpit.utils.loghandler;

import org.junit.Test;

public class LogHandlerImpltTest {

    LogHandlerImplt log = new LogHandlerImplt();

    @Test
    public void testLogMessage() {

        log.logMessage("Sirimal","Has Logged");
    }
    @Test
    public void testGetLogs() throws Exception {

        System.out.println(log.getLogs(10.00,10.50,"Sirimal"));

    }

}