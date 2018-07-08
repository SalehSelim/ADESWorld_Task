package com.adesworld.Model;

/**
 * Created by ssasa on 07-Jul-18.
 */

public class Document {

    private Boolean Success ;
    private String Message;
    private DocumentData[] Data;

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public DocumentData[] getData() {
        return Data;
    }

    public void setData(DocumentData[] data) {
        Data = data;
    }
}
