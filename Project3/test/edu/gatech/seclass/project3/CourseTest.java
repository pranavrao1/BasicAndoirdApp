package edu.gatech.seclass.project3;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.seclass.project3.Course;
import edu.gatech.seclass.project3.Grades;
import edu.gatech.seclass.project3.Student;
import edu.gatech.seclass.project3.Students;

public class CourseTest {

    Students students = null;
    Grades grades = null;
    Course course = null;
    static final String DB = "DB/CourseDatabase6300.xlsx";
    static final String DB_GOLDEN = "DB/CourseDatabase6300-golden.xlsx";

    @Before
    public void setUp() throws Exception {
        FileSystem fs = FileSystems.getDefault();
        Path dbfilegolden = fs.getPath(DB_GOLDEN);
        Path dbfile = fs.getPath(DB);
        Files.copy(dbfilegolden, dbfile, REPLACE_EXISTING);
        course = new Course(DB);
    }

    @After
    public void tearDown() throws Exception {
        students = null;
        grades = null;
        course = null;
    }

    @Test
    public void testGetNumStudents1() {
        int numStudents = course.getNumStudents();
        assertEquals(16, numStudents);
    }

    @Test
    public void testGetNumAssignments1() {
        int numAssignments = course.getNumAssignments();
        assertEquals(3, numAssignments);
    }

    @Test
    public void testGetNumProjects1() {
        int numProjects;
        numProjects = course.getNumProjects();
        assertEquals(3, numProjects);
    }

    @Test
    public void testGetStudents1() {
        HashSet<Student> studentsRoster = null;
        studentsRoster = course.getStudents();
        assertEquals(16, studentsRoster.size());
    }

    @Test
    public void testGetStudents2() {
        HashSet<Student> studentsRoster = null;
        studentsRoster = course.getStudents();
        Student student = null;
        for (Student s : studentsRoster) {
            if (s.getName().compareTo("Tendai Charpentier") == 0) {
                student = s;
                break;
            }
        }
        assertNotNull(student);
    }

    @Test
    public void testGetStudentsByName1() {
        Student student = null;
        student = course.getStudentByName("Rastus Kight");
        assertEquals(901234512, student.getGtid());
    }

    @Test
    public void testGetStudentsByName2() {
        Student student = null;
        student = course.getStudentByName("Coriander Alfsson");
        assertEquals(98, student.getAttendance());
    }

    @Test
    public void testGetStudentsByID1() {
        Student student = course.getStudentByID(901234504);
        assertEquals("Shevon Wise", student.getName());
    }

    @Test
    public void testGetTeam1() {
        Student student = course.getStudentByName("Genista Parrish");
        assertEquals("Team 2", student.getTeam());
    }

    @Test
    public void testGetTeam2() {
        Student student = course.getStudentByName("Freddie Catlay");
        assertEquals("Team 1", student.getTeam());
    }

    // New tests below

    @Test
    public void testAddAssignment() {
        course.addAssignment("Assignment: category partition");
        course.updateGrades(new Grades(DB));
        assertEquals(4, course.getNumAssignments());
        course.addAssignment("Assignment: white-box testing");
        course.updateGrades(new Grades(DB));
        assertEquals(5, course.getNumAssignments());
    }

    @Test
    public void testAddGradesForAssignment() {
        String assignmentName = "Assignment: category partition";
        Student student1 = new Student("Josepha Jube", 901234502, course);
        Student student2 = new Student("Christine Schaeffer", 901234508, course);
        Student student3 = new Student("Ernesta Anderson", 901234510, course);
        course.addAssignment(assignmentName);
        course.updateGrades(new Grades(DB));
        HashMap<Student, Integer> grades = new HashMap<Student, Integer>();
        grades.put(student1, 87);
        grades.put(student2, 94);
        grades.put(student3, 100);
        course.addGradesForAssignment(assignmentName, grades);
        course.updateGrades(new Grades(DB));
        assertEquals(90, course.getAverageAssignmentsGrade(student1));
        assertEquals(94, course.getAverageAssignmentsGrade(student2));
        assertEquals(93, course.getAverageAssignmentsGrade(student3));
    }

    @Test
    public void testGetAverageAssignmentsGrade() {
        // Rounded to the closest integer
        Student student = new Student("Ernesta Anderson", 901234510, course);
        assertEquals(90, course.getAverageAssignmentsGrade(student));
    }

    @Test
    public void testGetAverageProjectsGrade() {
        // Rounded to the closest integer
        Student student = new Student("Rastus Kight", 901234512, course);
        assertEquals(86, course.getAverageProjectsGrade(student));
    }

    @Test
    public void testAddIndividualContributions() {
        String projectName1 = "Project 2";
        Student student1 = new Student("Josepha Jube", 901234502, course);
        Student student2 = new Student("Grier Nehling", 901234503, course);
        HashMap<Student, Integer> contributions1 = new HashMap<Student, Integer>();
        contributions1.put(student1, 96);
        contributions1.put(student2, 87);
        course.addIndividualContributions(projectName1, contributions1);
        course.updateGrades(new Grades(DB));
        String projectName2 = "Project 3";
        HashMap<Student, Integer> contributions2 = new HashMap<Student, Integer>();
        contributions2.put(student1, 98);
        contributions2.put(student2, 100);
        course.addIndividualContributions(projectName2, contributions2);
        course.updateGrades(new Grades(DB));
        assertEquals(90, course.getAverageProjectsGrade(student1));
        assertEquals(84, course.getAverageProjectsGrade(student2));
    }
}
