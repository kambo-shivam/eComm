package com.eComm.service.api.vo.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("Status")
    @Expose
    private Integer Status;

    @SerializedName("Message")
    @Expose
    private String Message;
    @SerializedName("Success")
    @Expose
    private Boolean Success;

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean Success) {
        this.Success = Success;
    }
}
