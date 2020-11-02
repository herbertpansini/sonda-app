package com.androidtutz.anushka.tmdbclient.model;

public class Wiki {
    private String label;
    private String value;

    public Wiki() {
    }

    public Wiki(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
