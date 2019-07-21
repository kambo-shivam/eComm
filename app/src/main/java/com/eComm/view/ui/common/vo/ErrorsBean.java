package com.eComm.view.ui.common.vo;

public class ErrorsBean {
    /**
     * fieldName : contact_no
     * message : Contact number is not valid.
     */

    private String fieldName;
    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}