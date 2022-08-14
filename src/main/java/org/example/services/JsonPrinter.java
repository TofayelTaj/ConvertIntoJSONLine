package org.example.services;

import org.example.models.DataModel;

import java.util.ArrayList;
import java.util.List;

public class JsonPrinter implements Printer {
    @Override
    public List<String> print(DataModel dataModel) {

        List<String> jsonList = new ArrayList<>();
        String[] keys = dataModel.getKeys();
        List<String[]> dataList = dataModel.getData();
        for (int i = 0; i < dataList.size(); i++) {
            String jsonTemplate = "{\"";
            String[] arr = dataList.get(i);
            for (int j = 0; j < arr.length; j++) {
                jsonTemplate += keys[j] + "\" : " + "\"" + arr[j] + "\", ";
            }
            jsonTemplate += "}";
            jsonList.add(jsonTemplate);
        }
        return jsonList;
    }
}
