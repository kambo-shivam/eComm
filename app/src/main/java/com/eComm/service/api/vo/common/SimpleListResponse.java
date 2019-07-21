package com.eComm.service.api.vo.common;

import com.eComm.view.ui.common.vo.CommonChildResponse;

import java.util.List;

public class SimpleListResponse<T> extends CommonChildResponse {

    List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
