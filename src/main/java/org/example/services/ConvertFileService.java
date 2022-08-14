package org.example.services;

import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.example.models.DataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertFileService {

    private Printer printer;

    private FileService fileService;


    public ConvertFileService(Printer printer) {
        this.printer = printer;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public File convertFile(File selectedFile, char separator) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
            DataModel dataModel = this.convertToLineData(bufferedReader, separator);
            List<String> data = this.printer.print(dataModel);
//            fileService.saveFile(data);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }





        return null;
    }

    private DataModel convertToLineData(BufferedReader bufferedReader, char separator) throws IOException {
        String[] keys = bufferedReader.readLine().split(separator + "");
        String text;
        for (int i = 0; i < keys.length; i++) {
            keys[i] = keys[i].trim();
        }
        List<String[]> data = new ArrayList<>();
        while ((text = bufferedReader.readLine()) != null) {
            String[] dataLine = text.split(separator + "");
            data.add(dataLine);
        }

        CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("C:\\Users\\AireenPC\\IdeaProjects\\test\\test.csv")));
        csvWriter.writeAll(data);
        DataModel dataModel = new DataModel();
        dataModel.setKeys(keys);
        dataModel.setData(data);
        return dataModel;
    }


}
