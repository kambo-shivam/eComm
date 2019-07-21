package com.eComm.service.api.vo.common;


public class GeneralResponse<T> extends CommonResponse {

    T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}