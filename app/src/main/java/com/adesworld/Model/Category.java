package com.adesworld.Model;

/**
 * Created by ssasa on 07-Jul-18.
 */

public class Category {

    private Boolean Success ;
    private String Message;
    private CategoryData [] Data;

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

    public CategoryData[] getData() {
        return Data;
    }

    public void setData(CategoryData[] data) {
        Data = data;
    }
}
