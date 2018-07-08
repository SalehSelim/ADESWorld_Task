package com.adesworld.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ssasa on 07-Jul-18.
 */

public class CategoryData implements Serializable{
    @SerializedName("id")
    private String id ;
    @SerializedName("name")
    private String name ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
