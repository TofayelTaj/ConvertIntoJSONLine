package org.example;
//
//import org.example.services.ConvertFileService;
//import org.example.services.FileService;
//import org.example.services.JsonPrinter;
//
//import java.io.File;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        FileService fileService = new FileService();
//        ConvertFileService service = new ConvertFileService(new JsonPrinter());
//        service.setFileService(fileService);
//
//
////        int command = -1;
////        while(command != 0){
////            System.out.println("0 -> Exit.");
////            System.out.println("1 -> Enter File path.");
////            System.out.println("Enter Command...");
////            command = scanner.nextInt();
////            scanner.nextLine();
////
////            if(command == 1){
////                System.out.println("Enter file path...");
////                String filePath = scanner.nextLine();
////                System.out.println("Enter separator character...");
////                char separator = scanner.nextLine().charAt(0);
//        String filePath = "C:\\Users\\AireenPC\\IdeaProjects\\test\\text.txt";
//        char separator = ',';
//        fileService.setFilePath(filePath);
//        File selectedFile = fileService.getSelectedFile();
//        File convertedFile = service.convertFile(selectedFile, separator);
//
//
//    }
//}




import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        try {
            File dsvFile = new File("C:\\Users\\AireenPC\\IdeaProjects\\test\\test.txt");
            FileReader filereader = new FileReader(dsvFile);

            CSVReader dsvHeaderReader = new CSVReaderBuilder(filereader)
                    .build();
            List<String> headerList = Arrays.asList(dsvHeaderReader.readNext());
            dsvHeaderReader.close();

            filereader = new FileReader(dsvFile);
            CSVReader dsvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();

            String[] nextLine;
            while ((nextLine = dsvReader.readNext()) != null) {
                List<String> sequential = Arrays.stream(nextLine)
                        .sequential()
                        .collect(toList());
                StringBuilder stringBuilder = getJsonLine(headerList, sequential);
                System.out.println(new String(stringBuilder));
            }

            dsvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getJsonLine(List<String> headerList, List<String> sequential) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < headerList.size(); i++) {
            String value = sequential.get(i);
            if (headerList.get(i).contains("date")) {
                value = getFormattedDate(value);
            }

            if (StringUtils.isNotBlank(sequential.get(i))) {
                stringBuilder.append("\"" + headerList.get(i) + "\": ");

                stringBuilder.append(isNumeric(sequential.get(i))
                        ? value
                        : "\"" + value + "\"");

                if (i < headerList.size() - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        stringBuilder.append("}");
        return stringBuilder;
    }

    private static String getFormattedDate(String value) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
            //System.out.println(date);
        } catch (ParseException e) {

        }
        return value;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
