package net.sectorbob.fantasy;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author Kyle Heide
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);
        app.run(args);
    }

    public static void printTable(List<Map<String, String>> tableData, String... columns) {
        String columnDelimiter = " | ";


        List<Integer> columnWidths = new ArrayList<>();
        for(String column : columns) {
            int maxValueLength = column.length();
            for(Map<String,String> rowData : tableData) {
                String temp = rowData.get(column);
                if(temp != null)
                    maxValueLength = (maxValueLength < rowData.get(column).length())
                            ? rowData.get(column).length() : maxValueLength;
            }
            columnWidths.add(maxValueLength);
        }
        StringBuilder headerRow = new StringBuilder();
        headerRow.append(columnDelimiter);
        for(int i = 0; i < columns.length; i++) {
            String columnName = columns[i];
            int columnWidth = columnWidths.get(i);
            headerRow.append(String.format("%-" + columnWidth + "s" + columnDelimiter, columnName));
        }
        StringBuilder topBorder = new StringBuilder();
        topBorder.append(" +");
        for(int i = 0; i < headerRow.length()-4; i++) topBorder.append('-');
        topBorder.append("+ ");

        System.out.println(topBorder.toString());
        System.out.println(headerRow.toString());
        System.out.println(topBorder.toString());
        for(Map<String,String> data : tableData) {
            System.out.print(columnDelimiter);
            for (int i = 0; i < columns.length; i++) {
                String columnName = columns[i];
                int columnWidth = columnWidths.get(i);
                String temp = (data.get(columnName) == null) ? "" : data.get(columnName);
                System.out.printf("%-" + columnWidth + "s" + columnDelimiter, temp);
            }
            System.out.println();
        }

        System.out.println(topBorder.toString());
    }

}