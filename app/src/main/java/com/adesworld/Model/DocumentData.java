package com.adesworld.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ssasa on 07-Jul-18.
 */

public class DocumentData implements Serializable {

    @SerializedName("AdesWorldName")
    private String name ;
    @SerializedName("AdesWorldDir")
    private String dir ;
    @SerializedName("created_at")
    private String created_at ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
