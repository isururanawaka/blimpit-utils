package org.blimpit.utils.loghandler;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LogHandlerImpltTest {

    LogHandlerImplt log = new LogHandlerImplt();

    @Test
    public void testLogMessage() {

        log.logMessage("Sirimal","Has Logged");
    }
    @Test
    public void testGetLogs() throws Exception {

        assertNotNull(log.getLogs(10.00,10.50,"Sirimal"));

    }

}