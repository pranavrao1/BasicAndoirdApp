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
/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 4/23/16
 * Time: 3:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdditionalCourseTest {

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
    public void testCreateStudents() {
        Student student1 = new Student();
        student1.setGtid("901234517");
        student1.setEmail("tm@gatech.edu");
        student1.setName("Tom Mad");
        course.addNewStudents(student1);
        Student student = course.getStudentByName("Tom Mad");
        assertEquals(student1.getGtid(),student.getGtid());
    }

    @Test
    public void testCreateAndUpdateTeams() {
        course.addNewTeam("New Team 1");
        Student student1 = new Student();
        student1.setGtid("901234517");
        student1.setEmail("tm@gatech.edu");
        student1.setName("Tom Mad");

        course.updateTeam("New Team 1", student1);
        assertEquals("New Team 1",student1.getTeam());
    }


    @Test
    public void testCreateProjects() {
        course.addNewProjects("Experimental Project 1");
        int numOfProjects = course.getNumProjects();
        assertEquals(4,numOfProjects);
    }

    @Test
    public void testAddAndGradeProject() {
        course.addNewProjects("Experimental Project 2");
        HashMap<String, Integer> projectGrades = new HashMap<String, Integer>();
        projectGrades.put("Team 1", 87);
        projectGrades.put("Team 2", 94);
        projectGrades.put("Team 3", 100);
        course.addGradesForProject("Experimental Project 2",projectGrades);
        int numOfProjects = course.getNumProjects();
        assertEquals(4,numOfProjects);
    }
}
