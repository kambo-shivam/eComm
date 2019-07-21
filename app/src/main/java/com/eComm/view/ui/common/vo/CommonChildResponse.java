package com.eComm.view.ui.common.vo;

import java.util.List;

public class CommonChildResponse {


    /**
     * errorCode : 0
     * message :
     * data : true
     * errors:{}
     */

    private int errorCode;
    private String message;
    private List<ErrorsBean> errors;

    public List<ErrorsBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorsBean> errors) {
        this.errors = errors;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

   }
