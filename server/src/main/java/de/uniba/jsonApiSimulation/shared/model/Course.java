package de.uniba.jsonApiSimulation.shared.model;


import java.io.Serializable;

/**
 * Serves as the Model and DTO class for custom data type Course
 * Created by chandan on 01.05.17.
 */
public class Course implements Serializable {

    private int courseId;

    private String courseAbbreviation;
    private String courseName;
    private String termCourseOfferedIn;

    private FacultyGroup facultyGroup;

    public Course() {}

    public Course(int courseId, String courseAbbreviation, String courseName, String termCourseOfferedIn, FacultyGroup facultyGroup) {
        this.courseId = courseId;
        this.courseAbbreviation = courseAbbreviation;
        this.courseName = courseName;
        this.termCourseOfferedIn = termCourseOfferedIn;
        this.facultyGroup = facultyGroup;
    }

    public Course(String courseAbbreviation, String courseName, String termCourseOfferedIn) {
        this.courseAbbreviation = courseAbbreviation;
        this.courseName = courseName;
        this.termCourseOfferedIn = termCourseOfferedIn;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseAbbreviation() {
        return courseAbbreviation;
    }

    public void setCourseAbbreviation(String courseAbbreviation) {
        this.courseAbbreviation = courseAbbreviation;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTermCourseOfferedIn() {
        return termCourseOfferedIn;
    }

    public void setTermCourseOfferedIn(String termCourseOfferedIn) {
        this.termCourseOfferedIn = termCourseOfferedIn;
    }

    public FacultyGroup getFacultyGroup() {
        return facultyGroup;
    }

    public void setFacultyGroup(FacultyGroup facultyGroup) {
        this.facultyGroup = facultyGroup;
    }

    @Override
    public int hashCode() {

        final int prime = 17;
        int result = 1;
        result=prime*result+courseId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }else if(obj==null){
            return false;
        }else if(!(obj instanceof Course)){
            return false;
        }
        Course course = new Course();
        if(courseId!=course.courseId){
            return false;
        }
        return true;
    }
}
