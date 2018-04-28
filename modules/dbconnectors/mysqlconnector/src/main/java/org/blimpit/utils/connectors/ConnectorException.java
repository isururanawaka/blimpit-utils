package org.blimpit.utils.connectors;

public class ConnectorException extends Exception {

    Throwable e;
    String errorCause;
    public ConnectorException(Throwable ex, String cause){
        this.e = ex;
        this.errorCause = cause;

    }


    @Override
    public String toString() {

        return "Error Returned : "+e.toString()+"\n Possible Cause: "+errorCause;
    }
}
