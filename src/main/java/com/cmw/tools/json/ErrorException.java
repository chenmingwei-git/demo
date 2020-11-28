package com.cmw.tools.json;

import java.util.Map;

/**
 * @Author: cmw
 * @Description:
 * @Date:
 */
public class ErrorException extends Exception {
    private static final long serialVersionUID = 1L;
    private String erroCode = null;
    private Map<String, Object> returndata;

    public ErrorException(String msg) {
        super(msg);
    }

    public ErrorException(String message, Map<String, Object> returndata) {
        super(message);
        this.returndata = returndata;
    }

    public ErrorException(String erroCode, String msg) {
        super(msg);
        this.erroCode = erroCode;
    }

    public String getErroCode() {
        return this.erroCode;
    }

    public Map<String, Object> getReturndata() {
        return this.returndata;
    }
}
