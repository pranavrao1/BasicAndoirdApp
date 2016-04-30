package edu.gatech.seclass.project3;

import com.sun.deploy.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.examples.CellTypes;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 4/16/16
 * Time: 12:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Course {
    private static final String DEFAULT_FORMULA = "ATT * 0.2 + AVGA *0.4 +AVPG * 0.4";
    private String db;
    private String formula;
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
        Grades grades = new Grades(db);
        try {
            int numOfAssignments = grades.getNumOfAssignments();
            FileInputStream file = new FileInputStream(new File(db));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheetAssignments = workbook.getSheetAt(3);
            Row row = sheetAssignments.getRow(0);
            Cell cell = row.createCell(numOfAssignments + 1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(assignmentName);
            file.close();

            FileOutputStream fileOutputStream = new FileOutputStream(db);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void updateGrades(Grades grades){
        this.db = grades.getDb();
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
            float sumOfAssignments = 0;
            Row row = sheetIndGrade.getRow(rowIndex);
            Iterator<Cell> cells = row.cellIterator();

            cells.next();

            while (cells.hasNext()) {
                Cell cellgrade = cells.next();
                cellgrade.setCellType(Cell.CELL_TYPE_STRING);
                String cellGrade = cellgrade.getStringCellValue();
                sumOfAssignments += Float.parseFloat(cellGrade);
                numberOfAssignments++;
            }

            average=Math.round(sumOfAssignments/numberOfAssignments);
        } catch (Exception e) {

        }
        return average;
    }

    public void addGradesForAssignment(String assignment, Map<Student, Integer> map){
        Grades grades = new Grades(db);
        try {
            FileInputStream file = new FileInputStream(new File(db));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheetAssignments = workbook.getSheetAt(3);
            Iterator<Row> rowIterator = sheetAssignments.rowIterator();

            Row rowHeader = rowIterator.next();
            Iterator<Cell> iterator = rowHeader.cellIterator();
            int assignmentPosition = 0;
            while (iterator.hasNext()) {
                Cell cell = iterator.next();
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String value = cell.getStringCellValue();
                if( value.equals(assignment)) {
                    break;
                } else {
                    assignmentPosition++;
                }
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell cell = row.getCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String id = cell.getStringCellValue();
                Set<Student> studentSet = map.keySet();
                for (Student student : studentSet) {
                    String studentId = String.valueOf(student.getGtid());
                    if (studentId.equals(id)) {
                    Integer score =  map.get(student);
                    if (score!=null) {
                        Cell cellScore = row.createCell(assignmentPosition);
                        cellScore.setCellValue(score);
                    }
                    }
                }
            }
            file.close();

            FileOutputStream fileOutputStream = new FileOutputStream(db);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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

    public void addIndividualContributions( String projectName, Map<Student, Integer> contributionMap)  {
    Grades grades = new Grades(db);
    try {
        FileInputStream file = new FileInputStream(new File(db));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet indConProjects = workbook.getSheetAt(4);
        Iterator<Row> rowIterator = indConProjects.rowIterator();

        Row rowHeader = rowIterator.next();
        Iterator<Cell> iterator = rowHeader.cellIterator();
        int assignmentPosition = 0;
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String value = cell.getStringCellValue();
            if( value.equals(projectName)) {
                break;
            } else {
                assignmentPosition++;
            }
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell cell = row.getCell(0);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String id = cell.getStringCellValue();
            Set<Student> studentSet = contributionMap.keySet();
            for (Student student : studentSet) {
                String studentId = String.valueOf(student.getGtid());
                if (studentId.equals(id)) {
                    Integer score =  contributionMap.get(student);
                    if (score!=null) {
                        Cell cellScore = row.createCell(assignmentPosition);
                        cellScore.setCellValue(score);
                    }
                }
            }
        }
        file.close();

        FileOutputStream fileOutputStream = new FileOutputStream(db);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    } catch (IOException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    }

    public void addNewStudents(Student student) {

    }

    public void addNewTeam(String name) {

    }

    public void updateTeam(String name, Student student ) {

    }

    public void addNewProjects(String name) {}

    public void addGradesForProject(String name, Map<String, Integer> projectGrades) {}

    public int getAttendance(Student student) {

        String id = String.valueOf(student.getGtid());
        int attendence = 0;
        try {
            FileInputStream file = new FileInputStream(new File(db));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet indConProjects = workbook.getSheetAt(2);
            Iterator<Row> rowIterator = indConProjects.rowIterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row  = rowIterator.next();
                Cell celld = row.getCell(0);
                celld.setCellType(Cell.CELL_TYPE_STRING);
                String value = celld.getStringCellValue();
                if (id.equals(value)) {
                    Cell cellAtt = row.getCell(1);
                    cellAtt.setCellType(Cell.CELL_TYPE_STRING);
                    String att = cellAtt.getStringCellValue();
                    attendence = Integer.valueOf(att);
                } else {

                }
            }

            file.close();

            FileOutputStream fileOutputStream = new FileOutputStream(db);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return attendence;
    }

    public String getTeam(Student student) {
        return student.getTeam();
    }

    public String getFormula() {
        if (formula.isEmpty()) {
            return DEFAULT_FORMULA;
        } else {
            return formula;
        }
    }

    public void setFormula( String formula) {
        this.formula=formula;
    }

    public int getOverallGrade(Student student) throws GradeFormulaException{
        int ATT = getAttendance(student);
        long AVGA = getAverageAssignmentsGrade(student);
        long AVGP = getAverageProjectsGrade(student);

        String formulaLocale = getFormula();
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByExtension("js");
        engine.put("ATT",ATT);
        engine.put("AVGA",AVGA);
        engine.put("AVGP",AVGP);

        Double result = 0.0;
        try {
            result = (Double) engine.eval(formulaLocale);
        } catch (ScriptException e) {
            throw new GradeFormulaException(formulaLocale);
        }
        DecimalFormat f = new DecimalFormat("##");
        String finalValue = f.format(result);
        return Integer.parseInt(finalValue);
    }

    public String getEmail(Student student) {
        return student.getEmail();
    }
}
