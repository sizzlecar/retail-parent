package com.voyageone.retail.common.exception;


/**
 * RetailException
 */
public class RetailException extends RuntimeException{


    private String code;

    private String msg;


    public RetailException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public RetailException(String msg) {
        this.msg = msg;
    }

    public RetailException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetailException(Throwable cause) {
        super(cause);
    }
}
