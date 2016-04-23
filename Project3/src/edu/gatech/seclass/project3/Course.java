package edu.gatech.seclass.project3;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

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

    public Integer getNumAssignments(){
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
        return null;
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
}

