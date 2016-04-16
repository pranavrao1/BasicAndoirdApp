package edu.gatech.seclass.project3;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 4/16/16
 * Time: 12:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Grades {

    public Grades() {
    }

    public Integer getNumberOfProjects(XSSFWorkbook workbook) {
        XSSFSheet sheet5 = workbook.getSheetAt(5);
        Row rowHeader = sheet5.getRow(0);
        Iterator<Cell> cells = rowHeader.cellIterator();
        Integer projects = 0;
        //Skipping Headers
        cells.next();

        while (cells.hasNext()) {
            cells.next();
            projects++;
        }
        return projects;
    }

    public Integer getNumOfAssignments(XSSFWorkbook workbook ) {
        XSSFSheet sheet5 = workbook.getSheetAt(3);
        Row rowHeader = sheet5.getRow(0);
        Iterator<Cell> cells = rowHeader.cellIterator();
        Integer assignments = 0;
        //Skipping Headers
        cells.next();

        while (cells.hasNext()) {
            cells.next();
            assignments++;
        }
        return assignments;
    }
}

