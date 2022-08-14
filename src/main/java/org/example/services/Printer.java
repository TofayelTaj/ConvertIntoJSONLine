package org.example.services;

import org.example.models.DataModel;

import java.util.List;

public interface Printer {

    List<String> print(DataModel dataModel);
}
