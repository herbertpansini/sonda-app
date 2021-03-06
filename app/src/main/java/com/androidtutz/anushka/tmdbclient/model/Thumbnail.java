package com.androidtutz.anushka.tmdbclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Thumbnail implements Serializable {
    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("extension")
    @Expose
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return this.path + "." + this.extension;
    }
}
