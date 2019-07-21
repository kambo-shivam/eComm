package com.eComm.service.api.vo.common;


import com.eComm.view.ui.common.vo.CommonChildResponse;

public class SimpleResponse<T> extends CommonChildResponse {
    T data;

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }

}
