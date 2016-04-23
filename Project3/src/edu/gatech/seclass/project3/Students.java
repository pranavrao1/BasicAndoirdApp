package edu.gatech.seclass.project3;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 4/16/16
 * Time: 12:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Students {

      public Students(){}

      public List<Student> getAllStudentInfo(XSSFWorkbook workbook){
          List<Student> studentList = new ArrayList<>();
          Map<String,Student> studentMap = new HashMap<>();

          //Get Basic Student Info.
          XSSFSheet sheet0 = workbook.getSheetAt(0);
          Iterator<Row> rowIterator0 = sheet0.iterator();

          //Skipping Headers
          rowIterator0.next();

          while (rowIterator0.hasNext()){
              Row row = rowIterator0.next();
              Student student = new Student();
              Cell cell1 = row.getCell(0);
              String name = cell1.getStringCellValue();
              student.setName(name);

              Cell cell2 = row.getCell(1);
              cell2.setCellType(Cell.CELL_TYPE_STRING);
              String id = cell2.getStringCellValue();
              student.setGtid(id);

              Cell cell3 = row.getCell(2);
              cell3.setCellType(Cell.CELL_TYPE_STRING);
              String email = cell3.getStringCellValue();
              student.setEmail(email);

              studentMap.put(id,student);
          }

          //Get Team Info.
          XSSFSheet sheet1 = workbook.getSheetAt(1);
          Iterator<Row> rowIterator1 = sheet1.iterator();

          //Skipping Headers
          rowIterator1.next();

          while (rowIterator1.hasNext()) {
              Row row = rowIterator1.next();
              Iterator<Cell> cellIterator = row.cellIterator();

              if (cellIterator.hasNext()) {
                  Cell cellTeam = cellIterator.next();
                  String team = cellTeam.getStringCellValue();

                  while (cellIterator.hasNext()) {
                      Cell cellId = cellIterator.next();
                      cellId.setCellType(Cell.CELL_TYPE_STRING);
                      String id = cellId.getStringCellValue();
                      if(studentMap.containsKey(id)){
                          Student student = studentMap.get(id);
                          student.setTeam(team);
                          studentMap.put(id,student);
                      }
                  }
              }
          }

          //Get Attendence
          XSSFSheet sheet2 = workbook.getSheetAt(2);
          Iterator<Row> rowIterator2 = sheet2.iterator();

          //Skipping headers
          rowIterator2.next();

          while (rowIterator2.hasNext()) {
              Row row = rowIterator2.next();
              Cell cellId = row.getCell(0);
              cellId.setCellType(Cell.CELL_TYPE_STRING);
              String id = cellId.getStringCellValue();

              if(studentMap.containsKey(id)) {

                  Cell cellAtt = row.getCell(1);
                  cellAtt.setCellType(Cell.CELL_TYPE_STRING);
                  String att = cellAtt.getStringCellValue();

                  Student student = studentMap.get(id);
                  student.setAttendence(att);
                  studentMap.put(id,student);
              }
          }

          for ( String id: studentMap.keySet()) {
              Student student = studentMap.get(id);
              studentList.add(student);
          }

          return studentList;
      }
}
