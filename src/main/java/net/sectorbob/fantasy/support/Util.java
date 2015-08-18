package net.sectorbob.fantasy.support;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kyle Heide
 *
 */
public class Util {

    public static void printTable(Logger logger, Priority level, Table data, String... columns) {
        String columnDelimiter = " | ";


        List<Integer> columnWidths = new ArrayList<>();
        for(String column : columns) {
            int maxValueLength = column.length();
            for(Row row : data) {
                TableCellData temp = row.get(column);
                if(cellHasData(temp))
                    maxValueLength = (maxValueLength < row.get(column).getData().length())
                            ? row.get(column).getData().length() : maxValueLength;
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

        logger.log(level, topBorder.toString());
        logger.log(level, headerRow.toString());
        logger.log(level, topBorder.toString());
        for(Row row : data) {
            StringBuilder s = new StringBuilder();

            s.append(columnDelimiter);
            for (int i = 0; i < columns.length; i++) {
                String columnName = columns[i];
                int columnWidth = columnWidths.get(i);
                String temp = ( !cellHasData(row.get(columnName))) ? "" : row.get(columnName).getData();
                s.append(String.format("%-" + columnWidth + "s" + columnDelimiter, temp));
            }
            logger.log(level, s.toString());
        }

        logger.log(level, topBorder.toString());
    }

    private static boolean cellHasData(TableCellData data) {
        return (data != null && data.getData() != null && data.getData().length() != 0);
    }
}
