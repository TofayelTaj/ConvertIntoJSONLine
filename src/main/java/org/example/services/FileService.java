package org.example.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;

public class FileService {

    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getSelectedFile() {
        File selectedFile = null;
        try {
            selectedFile = new File(filePath);
            if (!selectedFile.exists()) {
                throw new FileNotFoundException("file not found");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return selectedFile;
    }

    public String saveFile(List<String> data) {
        File file = new File(filePath);
        String fileName = file.getName() + "JSON.txt";
        String savePath = file.getParent();
        File jsonFile = new File(savePath + File.separator + fileName);

        try {
            jsonFile.createNewFile();
            FileWriter writer = new FileWriter(jsonFile);
            for (String d : data) {
                writer.write(d);
                writer.append('\n');
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(fileName + " " + savePath);
        return jsonFile.getAbsolutePath();
    }


}