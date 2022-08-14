package org.example.models;

import java.util.List;

public class DataModel {
    private String[] keys;
    private List<String[]> data;

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }
}
