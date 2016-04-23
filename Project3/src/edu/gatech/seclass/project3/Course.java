package edu.gatech.seclass.project3;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 4/16/16
 * Time: 12:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Course {
    private String db;
    public Course(String db){
        this.db=db;
    }

    public Integer getNumStudents(){
        if(db==null){
            return 0;
        }

        try {
            FileInputStream file = new FileInputStream(new File(db));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Students students = new Students();
            List<Student> studentList = students.getAllStudentInfo(workbook);

            return studentList.size();
        } catch (FileNotFoundException e ){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getNumAssignments(){
        if(db!=null){
            try {
                Grades grades = new Grades(db);
                return grades.getNumOfAssignments();
            } catch (FileNotFoundException e ){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Integer getNumProjects(){if(db!=null){
        try {
            Grades grades = new Grades(db);
            return grades.getNumberOfProjects();
        } catch (FileNotFoundException e ){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        return null;}

    public HashSet<Student> getStudents(){
        if(db!=null){
            try {
                FileInputStream file = new FileInputStream(new File(db));
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                Students students = new Students();
                List<Student> studentList = students.getAllStudentInfo(workbook);
                return new HashSet<>(studentList);
            } catch (FileNotFoundException e ){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Student getStudentByName(String name){
        HashSet<Student> students = getStudents();
        for (Student student : students) {
            String studentName = student.getName();
            if (studentName.equals(name)) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentByID(int studentId){
        String id = String.valueOf(studentId);
        HashSet<Student> students = getStudents();
        for (Student student : students) {
            String studentGtid = String.valueOf(student.getGtid());
            if (studentGtid.equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void addAssignment(String assignmentName){

    }

    public void updateGrades(Grades grades){

    }

    public long getAverageAssignmentsGrade(Student student){
        String id  = String.valueOf(student.getGtid());
        long average = 0L;
        try {
            FileInputStream file = new FileInputStream(new File(db));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheetIndGrade = workbook.getSheetAt(3);

            int rowIndex = 0;
            Iterator<Row> rows = sheetIndGrade.rowIterator();
            rows.next();
            rowIndex++;

            // Find row of assignments
            while ( rows.hasNext()) {
                Row row = rows.next();
                Cell cell = row.getCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String studentId = cell.getStringCellValue();
                if (studentId.equals(id)) {
                    break;
                } else {
                    rowIndex++;
                }
            }

            int numberOfAssignments = 0;
            int sumOfAssignments = 0;
            Row row = sheetIndGrade.getRow(rowIndex);
            Iterator<Cell> cells = row.cellIterator();

            cells.next();

            while (cells.hasNext()) {
                Cell cellgrade = cells.next();
                cellgrade.setCellType(Cell.CELL_TYPE_STRING);
                String cellGrade = cellgrade.getStringCellValue();
                sumOfAssignments += Integer.parseInt(cellGrade);
                numberOfAssignments++;
            }

            average=sumOfAssignments/numberOfAssignments;
        } catch (Exception e) {

        }
        return average;
    }

    public void addGradesForAssignment(String assignment, Map<Student, Integer> map){

    }

    public int getAverageProjectsGrade(Student student){
        String studentid = String.valueOf(student.getGtid());
        String teamName = getStudentByID(Integer.parseInt(studentid)).getTeam();
        int average = 0;

        try {
            FileInputStream file = new FileInputStream(new File(db));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheetTeamGrade = workbook.getSheetAt(5);

            int teamRow = 0;
            Iterator<Row> rows = sheetTeamGrade.rowIterator();
            rows.next();
            teamRow++;

            // Get Row Team is in Team grades
            while ( rows.hasNext()) {
                Row row = rows.next();
                Cell cell = row.getCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String team = cell.getStringCellValue();
                if (team.equals(teamName)) {
                    break;
                } else {
                    teamRow++;
                }
            }

            // Get Row Student is in Contribution
            XSSFSheet sheetIndCon = workbook.getSheetAt(4);
            int studentRow = 0;
            Iterator<Row> rowsStudent = sheetIndCon.rowIterator();
            rowsStudent.next();
            studentRow++;

            while ( rowsStudent.hasNext()) {
                Row row = rowsStudent.next();
                Cell cell = row.getCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String id = cell.getStringCellValue();
                if (id.equals(studentid)) {
                    break;
                } else {
                    studentRow++;
                }
            }

            // Get Number of Projects
            Grades grades = new Grades(db);
            int numberOfProjects = grades.getNumberOfProjects();
            float projectSummary = 0f;

            // Iterate over all projects
            int currentProject = 1;
            while (currentProject<=numberOfProjects) {
                Row rowTeam = sheetTeamGrade.getRow(teamRow);
                Cell cellProject = rowTeam.getCell(currentProject);
                cellProject.setCellType(Cell.CELL_TYPE_STRING);
                Float projectGrade = Float.parseFloat(cellProject.getStringCellValue());

                Row individualRow = sheetIndCon.getRow(studentRow);
                Cell cellIndividual = individualRow.getCell(currentProject);
                cellIndividual.setCellType(Cell.CELL_TYPE_STRING);
                Float individualGrade = Float.parseFloat(cellIndividual.getStringCellValue());
                Float summary = (projectGrade*individualGrade/100f);
                projectSummary += summary;
                currentProject++;
            }
            average = Math.round(projectSummary/numberOfProjects);
        } catch (Exception e) {

        }

        return average;
    }

    public void addIndividualContributions( String projectName, Map<Student, Integer> contributionMap) {

    }
}
