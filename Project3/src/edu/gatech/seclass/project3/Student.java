package edu.gatech.seclass.project3;

/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 4/16/16
 * Time: 12:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Student {
    private String name;
    private String gtid;
    private String email;
    private String attendence;
    private String teamNumber;
    private Course course;

    public Course getCourse() {
        return course;
    }

    public Student () {

    }
    public Student(String name, int gtid, Course course) {
        this.name=name;
        this.gtid=String.valueOf(gtid);
        this.course=course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGtid() {
        return Long.parseLong(gtid);
    }

    public void setGtid(String gtid) {
        this.gtid = gtid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAttendance() {
        return Long.parseLong(attendence);
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }

    public String getTeam() {
        return teamNumber;
    }

    public void setTeam(String teamNumber) {
        this.teamNumber = teamNumber;
    }
}
